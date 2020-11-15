package presentation;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import DAO.ReportClient;
import model.Client;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import static java.lang.String.valueOf;

/*public class PDF {




    public void crearePDF1() throws IOException {
       // ReportClient reportClient=new ReportClient();
           PDPage page =new PDPage();

            PDDocument document = new PDDocument();
           // ArrayList<Object> text = reportClient.report();
            File file =new File("Out.pdf");



          //  document=PDDocument.load(file);
            document.addPage(page);
            document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document,page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 7);

           contentStream.newLineAtOffset(100, 700);

           contentStream.showText("");





            contentStream.endText();
            System.out.println("Content added");
            contentStream.close();
            document.save("Out.pdf");



            System.out.println("PDF created");

            document.close();


        }

}*/
public class PDF {
    /***
     *
     * @param text este o lista de tip Object care urmeaza sa fie scrisa in pdf
     * @throws IOException daca text nu este de tipul cerut
     */
    public void crearePDF(ArrayList<Object> text) throws IOException {
        String text1=String.valueOf(text);
        PDDocument doc = null;
        try {
            doc = new PDDocument();
           File file =new File("Out.pdf");
            doc=PDDocument.load(file);
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = null;

            contentStream = new PDPageContentStream(doc, page);


            PDFont pdfFont = PDType1Font.HELVETICA;

            float fontSize;
            if(text1.startsWith("[Order[orderId=1,")) {
                 fontSize = 8.9f;
            }else  if(text1.startsWith("[OrderItem[orderId=1,")) {
                fontSize=19f;}else{
                 fontSize = 14;
            }
            float leading = 1.5f * fontSize;

            PDRectangle mediabox = page.getMediaBox();
            float margin = 72;
            float width = mediabox.getWidth() - 2 * margin;
            float startX = mediabox.getLowerLeftX() + margin;
            float startY = mediabox.getUpperRightY() - margin;


            List<String> lines = new ArrayList<String>();
            int lastSpace = -1;
            while (text1.length() > 0) {
                int spaceIndex = text1.indexOf(' ', lastSpace + 1);
                if (spaceIndex < 0)
                    spaceIndex = text1.length();
                String subString = text1.substring(0, spaceIndex);
                float size = fontSize * pdfFont.getStringWidth(subString) / 1000;

                if (size > width) {
                    if (lastSpace < 0)
                        lastSpace = spaceIndex;
                    subString = text1.substring(0, lastSpace);
                    lines.add(subString);
                    text1 = text1.substring(lastSpace).trim();

                    lastSpace = -1;
                }
                else if (spaceIndex == text1.length()) {
                    lines.add(text1);

                    text1 = "";
                } else {
                    lastSpace = spaceIndex;
                }
            }

            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(startX, startY);
            float currentY=startY;
            for (String line : lines) {
                currentY -=leading;
                if(currentY<=margin){
                    contentStream.endText();
                    contentStream.close();
                    PDPage new_Page = new PDPage();
                    doc.addPage(new_Page);
                    contentStream = new PDPageContentStream(doc, new_Page);
                    contentStream.beginText();
                    contentStream.setFont(pdfFont, fontSize);
                    contentStream.newLineAtOffset(startX, startY);
                    currentY=startY;
                }

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();

            doc.save("Out.pdf");
        } finally {
            if (doc != null) {

                doc.close();
            }

        }
    }
}