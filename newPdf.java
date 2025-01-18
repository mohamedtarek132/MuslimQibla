//package MuslimQibla;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.pdfbox.text.TextPosition;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.*;
//
//public class newPdf {
//    public static void main(String[] args) throws URISyntaxException {
//        try {
//            // Load the original PDF
//            URL url = MuslimQibla.Main.class.getResource("Texts/ar_sa7e7_elazkar.pdf");
//
//            File file = new File(url.toURI());
//            PDDocument document = org.apache.pdfbox.Loader.loadPDF(file);
//
//            // Create a new PDF document
//            PDDocument newDocument = new PDDocument();
//
//            // Load the full SakkalMajalla font
//            url = MuslimQibla.Main.class.getResource("majalla.ttf");
//
//            file = new File(url.toURI());
//            PDType0Font sakkalMajallaFont = PDType0Font.load(newDocument, file);
//
//            // Write the extracted text into the new PDF
//            for (PDPage page : document.getPages()) {
//                // Extract text from the current page
//                PDFTextStripper stripper = new PDFTextStripper() {
//                    @Override
//                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
//                        // Preprocess the text to handle unrecognized characters
//                        String processedText = preprocessText(text, sakkalMajallaFont);
//
//                        // Create a new page in the new document
//                        PDPage newPage = new PDPage();
//                        newDocument.addPage(newPage);
//
//                        // Write processed text to the new page
//                        try (PDPageContentStream contentStream = new PDPageContentStream(newDocument, newPage)) {
//                            contentStream.setFont(sakkalMajallaFont, 12);
//                            contentStream.beginText();
//                            contentStream.newLineAtOffset(50, 750);
//                            contentStream.showText(processedText);
//                            contentStream.endText();
//                        }
//                    }
//                };
//
//                // Extract text from the current page
//                stripper.getText(document);
//            }
//
//            // Save and close the new document
//            newDocument.save("output_with_processed_text.pdf");
//            newDocument.close();
//            document.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private static String preprocessText(String text, PDFont font) throws IOException {
//        // Example: Remove unrecognized characters
//        StringBuilder result = new StringBuilder();
//        for (char ch : text.toCharArray()) {
//            if (isCharacterSupported(font, ch)) {
//                result.append(ch);
//            } else {
//                result.append(' '); // Replace with a placeholder if necessary
//            }
//        }
//        return result.toString();
//    }
//
//    private static boolean isCharacterSupported(PDFont font, char ch) throws IOException {
//        // Check if the font supports the character
//        return font.getEncoding().encode(ch) != -1;
//    }
//}
