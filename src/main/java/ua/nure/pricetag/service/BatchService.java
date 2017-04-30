package ua.nure.pricetag.service;

import com.google.common.collect.Lists;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.pricetag.dto.BarcodeType;
import ua.nure.pricetag.dto.ProductInfoDto;
import ua.nure.pricetag.entity.PriceChanges;
import ua.nure.pricetag.entity.PriceChangesType;
import ua.nure.pricetag.entity.PriceTag;
import ua.nure.pricetag.entity.PriceTagBatch;
import ua.nure.pricetag.entity.Product;
import ua.nure.pricetag.entity.Stock;
import ua.nure.pricetag.entity.Store;
import ua.nure.pricetag.repository.PriceChangesRepository;
import ua.nure.pricetag.repository.PriceTagBatchRepository;
import ua.nure.pricetag.repository.PriceTagRepository;
import ua.nure.pricetag.repository.ProductRepository;
import ua.nure.pricetag.repository.StockRepository;
import ua.nure.pricetag.repository.StoreRepository;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BatchService {

    @Autowired
    private PriceTagBatchRepository repository;

    @Autowired
    private PriceTagRepository priceTagRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PriceChangesRepository priceChangesRepository;

    @Autowired
    private StoreRepository storeRepository;

    public BigInteger createBatch(PriceTagBatch priceTagBatch) {
        PriceTagBatch saved = repository.save(priceTagBatch);
        return saved.getId();
    }

    public void deleteBatch(BigInteger batchId) {
        repository.delete(batchId);
    }

    public PriceTagBatch getBatch(BigInteger batchId) {
        return repository.findOne(batchId);
    }

    public List<PriceTagBatch> getBatchesForStore(BigInteger storeId) {
        List<BigInteger> ids = priceTagRepository.findBatchIdsByStore(storeId);
        return Lists.newArrayList(repository.findAll(ids));
    }

    public void addPriceTagToBatch(PriceTag priceTag) {
        priceTagRepository.save(priceTag);
    }

    public void printBatch(BigInteger batchId, BarcodeType barcodeType) {
        List<PriceTag> toPrint = priceTagRepository.findByBatchId(batchId);
        List<ProductInfoDto> products = toPrint.stream()
                .map(this::convertToProductInfo)
                .collect(Collectors.toList());
        print(products, barcodeType);
    }

    private void print(List<ProductInfoDto> toPrint, BarcodeType barcodeType) {
        File file = new File("sample.pdf");
        try {
            new BarcodeInTable().manipulatePdf("sample.pdf", toPrint, barcodeType);
            String filename = file.getAbsolutePath();
            PDDocument document = PDDocument.load(new File (filename));

            PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(myPrintService);
            job.print();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ProductInfoDto convertToProductInfo(PriceTag priceTag) {
        ProductInfoDto dto = new ProductInfoDto();
        dto.setPriceTagCode(String.valueOf(priceTag.getId()));
        Product product = productRepository.findOne(priceTag.getStoreProduct().getProductId());
        dto.setProductName(product.getName());
        dto.setProductDescription(product.getDescription());
        Store store = storeRepository.findOne(priceTag.getStoreProduct().getStoreId());
        dto.setStoreName(store.getName());
        dto.setStoreAddress(store.getAddress());
        if(Objects.nonNull(priceTag.getStoreProduct().getStockId())) {
            Stock stock = stockRepository.findOne(priceTag.getStoreProduct().getStockId());
            dto.setStockName(stock.getName());
            if(Objects.nonNull(stock.getDesignId())) {
                dto.setDesignId(stock.getDesignId());
            } else {
                dto.setDesignId(priceTag.getDesignId());
            }
            PriceChanges priceChanges = priceChangesRepository.findOne(stock.getPriceChangesKey());
            dto.setCurrentPrice(changeCurrentPrice(priceTag.getStoreProduct().getPrice(), priceChanges));
        } else {
            dto.setCurrentPrice(priceTag.getStoreProduct().getPrice());
            dto.setDesignId(priceTag.getDesignId());
        }
        return dto;
    }

    private BigDecimal changeCurrentPrice(BigDecimal oldPrice, PriceChanges priceChanges) {
        PriceChangesType priceChangesType = PriceChangesType.getByKey(priceChanges.getPriceChangesType());
        switch (priceChangesType) {
            case SUM: return oldPrice.add(priceChanges.getValue());
            case PERCENT: return oldPrice.multiply(priceChanges.getValue().divide(BigDecimal.valueOf(100.),
                    BigDecimal.ROUND_HALF_UP));
            default: return oldPrice;
        }
    }
}
