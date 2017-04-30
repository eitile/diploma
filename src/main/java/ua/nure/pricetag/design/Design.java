package ua.nure.pricetag.design;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Cell;
import ua.nure.pricetag.dto.BarcodeType;
import ua.nure.pricetag.dto.ProductInfoDto;

import java.util.List;

public interface Design {

    List<Cell> getBarcode(ProductInfoDto dto, PdfDocument pdfDoc, BarcodeType barcodeType);

}
