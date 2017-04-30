package ua.nure.pricetag.design;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import org.apache.commons.lang.StringUtils;
import ua.nure.pricetag.dto.BarcodeType;
import ua.nure.pricetag.dto.ProductInfoDto;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;


public class HotOfferDesign implements Design {

    @Override
    public List<Cell> getBarcode(ProductInfoDto dto, PdfDocument pdfDoc, BarcodeType barcodeType) {
        Cell description = new Cell().add(getProductDescription(dto)).setBackgroundColor(Color.RED);
        Cell barcode = barcodeType == BarcodeType.BARCODE ? getEanCode(dto, pdfDoc) : getQrCode(dto, pdfDoc);
        return Arrays.asList(description, barcode);
    }

    private Cell getEanCode(ProductInfoDto dto, PdfDocument pdfDoc) {
        Barcode128 code128 = new Barcode128(pdfDoc);
        code128.setCode(dto.getPriceTagCode() + ":" + dto.getCurrentPrice());
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(pdfDoc)).setAutoScale(true);
        return new Cell().add(code128Image).setBackgroundColor(Color.RED);
    }

    private Cell getQrCode(ProductInfoDto dto, PdfDocument pdfDoc) {
        BarcodeQRCode qrCode = new BarcodeQRCode(dto.getPriceTagCode() + ":" + dto.getCurrentPrice());
        Image qrCodeImage = new Image(qrCode.createFormXObject(pdfDoc)).setAutoScale(true);
        return new Cell().add(qrCodeImage).setBackgroundColor(Color.RED);
    }

    private String getProductDescription(ProductInfoDto dto) {
        StringBuilder description = new StringBuilder();
        if(StringUtils.isNotBlank(dto.getStockName())) {
            description.append("STOCK! ")
                    .append(dto.getStockName())
                    .append(System.lineSeparator());
        }
        description.append("Hot price!")
                .append(System.lineSeparator())
                .append(dto.getProductName())
                .append(": ")
                .append(dto.getProductDescription())
                .append(System.lineSeparator())
                .append("Price: ")
                .append(dto.getCurrentPrice().setScale(2, RoundingMode.CEILING))
                .append(" UAH.");
        return description.toString();
    }

}
