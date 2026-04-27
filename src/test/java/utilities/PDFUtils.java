package utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtils {

    public static String readPDF(String filePath) {
        String text = "";
        try {
            File file = new File(filePath);
            PDDocument pdDocument = PDDocument.load(file);

            int totalPages = pdDocument.getNumberOfPages();
            System.out.println("Total pages: " + totalPages);

            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(pdDocument);

            System.out.println("text:"+text);

            pdDocument.close(); // don’t forget this


            /// we want to use as url

          //  File file = new File(filePath);
         //   URL url = file.toURI().toURL(); //
         //   PDDocument pdDocument = PDDocument.load(url.openStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
///Read pdf by page number
    public static String readPDFPage(String filePath, int pageNo) {
        String text = "";
        try {
            PDDocument document = PDDocument.load(new File(filePath));
            PDFTextStripper pdfStripper = new PDFTextStripper();

            pdfStripper.setStartPage(pageNo);
            pdfStripper.setEndPage(pageNo);

            text = pdfStripper.getText(document);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void pdfvalidation() {
        /// get the path from properties file
        String pdfPath= ConfigReader.getProperty("pdfpath");
        /// assign relative path
       // String pdfPath = "C:\\Users\\ganesh.nunna2014\\Downloads\\Bhanu_Prkash_SDET_Resume_S.pdf";

        String pdfContent =PDFUtils.readPDF(pdfPath);

    }

}