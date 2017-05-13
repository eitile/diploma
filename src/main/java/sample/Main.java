package sample;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("sample.pdf");
        PDDocument document = PDDocument.load(file);

        PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(myPrintService);
        if (job.printDialog()) {
            try {job.print();}
            catch (PrinterException exc) {
                System.out.println(exc);
             }
         }   
    }

}
