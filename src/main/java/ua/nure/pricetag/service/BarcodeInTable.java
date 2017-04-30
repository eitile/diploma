package ua.nure.pricetag.service;


import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import ua.nure.pricetag.design.DesignResolver;
import ua.nure.pricetag.dto.BarcodeType;
import ua.nure.pricetag.dto.ProductInfoDto;

import java.math.RoundingMode;
import java.util.List;

public class BarcodeInTable {

    public void manipulatePdf(String path, List<ProductInfoDto> toPrint, BarcodeType barcodeType) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path));
        Document doc = new Document(pdfDoc);

        Table table = new Table(4);

        toPrint.forEach(info -> {
            List<Cell> cells = DesignResolver.getBarcodeDesign(info).getBarcode(info, pdfDoc, barcodeType);
            table.addCell(cells.get(0));
            table.addCell(cells.get(1));
        });

        doc.add(table);
        doc.close();
    }

}
