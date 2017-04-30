package ua.nure.pricetag;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class PrintingSample {

    public static void main(String[] args) throws Exception {
        File file = new File("sample.pdf");
        String filename = file.getAbsolutePath();
        PDDocument document = PDDocument.load(new File(filename));

        PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(myPrintService);
        job.print();
    }

}
