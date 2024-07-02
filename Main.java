package MuslimQibla;

import MuslimQibla.Classes.Ayah;
import MuslimQibla.Classes.Page;
import MuslimQibla.Controllers.Mushaf;
import MuslimQibla.Classes.Surah;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


public class Main extends Application{
    /**
     * read the content of the file that was in the path
     * and returning it content in XWPFDocument variable
     * @param path
     * @return XWPFDocument that contains the content of the file
     */
    public XWPFDocument loadWordDocument(String path){
        try {
            URL url = getClass().getResource(path);

            File file = new File(url.toURI());

            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            fis.close();

            return document;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Read the mushaf word document and differentiating pages, surahs, and ayat from it
     * then saving them in Controllers.Mushaf variables mushaf, pages, and surahs
     * @throws XmlException
     * @throws IOException
     */
    public void loadMushaf() throws XmlException, IOException {
        XWPFDocument mushafText = loadWordDocument("Texts/UthmanicHafs v22.docx");

        List<XWPFParagraph> paragraphs = mushafText.getParagraphs();

        String surahName = "";

        int pageCount = 1;
        int surahNumber = 0;
        int numberOfAyatInSurah = 0;
        int ayatCount = 0;

        int totalNumberOfAyatIncludingBsmallah = 6348;
        int totalNumberOfPages = 604;
        int totalNumberOfSurahs = 114;
        Mushaf.ayat = new Ayah[totalNumberOfAyatIncludingBsmallah];
        Mushaf.pages = new Page[totalNumberOfPages];

        /*
        XWPFDocument reads word document as paragraphs instead of pages, so
        I am iterating on all of them to differentiate the pages, surahs, and ayat.
        And I found that at each new page there is a "\n\n", so i splitted the paragraph
        based on this assumption.Moreover, the surah name, the basmallah, and the surah itself
        all of them are in different paragraphs so it is easier to differentiate the surahs
        from each other
         */
        for (XWPFParagraph para : paragraphs) {
            String paragraph = para.getText();


            //There are some empty paragraphs so I added an if condition to not count them as ayat
            if(paragraph.isEmpty()){

                continue;
            }
            //There are some swarh that start at the start of the page and you can
            // know it by checking if there is "\n" before the word "سُورَة" so I consider it a new page and
            // increment the page conuter along with the page number and reset numberOfAyatInSurah
            // to 0
            else if(paragraph.startsWith("\nسُورَة")){

                pageCount++;

                surahName = paragraph.substring(9).replace("سُورَةُ", "");
                Mushaf.swarh.put(surahName, new Surah());
                Mushaf.swarh.get(surahName).name = surahName;
                Mushaf.swarh.get(surahName).pageNumber = pageCount;
                Mushaf.swarh.get(surahName).number = surahNumber + 1;
                System.out.println(surahName);
//                System.out.println(surahNumber + " " + surahName);
                surahNumber++;

//                System.out.println("بداية السورة صفحة"+pageCount);

                numberOfAyatInSurah = 0;
            }
            // There are other swarh that start in the middle of the page so I don't increment the page counter
            else if(paragraph.startsWith("سُورَةُ")){
                surahName = paragraph.substring(8).replace("سُورَةُ", "");
                Mushaf.swarh.put(surahName, new Surah());
                Mushaf.swarh.get(surahName).name = surahName;
                Mushaf.swarh.get(surahName).pageNumber = pageCount;
                Mushaf.swarh.get(surahName).number = surahNumber + 1;

//                System.out.println(surahNumber + " " + surahName);
//                System.out.println("بداية السورة صفحة"+pageCount);

                surahNumber++;

                numberOfAyatInSurah = 0;
            }
            //therer are two ways for writing basmallah
            else if (paragraph.strip().equals("بِسۡمِ ٱللَّهِ ٱلرَّحۡمَٰنِ ٱلرَّحِيمِ") ||
                    paragraph.strip().equals("بِّسۡمِ ٱللَّهِ ٱلرَّحۡمَٰنِ ٱلرَّحِيمِ")) {
                numberOfAyatInSurah = 0;

                Mushaf.ayat[ayatCount] = new Ayah();
                Mushaf.ayat[ayatCount].surahNumber = surahNumber;
                Mushaf.ayat[ayatCount].ayahNumber = numberOfAyatInSurah;
                Mushaf.ayat[ayatCount].pageNumber = pageCount;
                Mushaf.ayat[ayatCount].ayah = paragraph;
                Mushaf.ayat[ayatCount].surah = surahName;

                ayatCount++;
            }else{
                // if there was an additional "\n" at the start of a paragraph
                // that isn't a surah name i add 1 tho page count
                if(paragraph.startsWith("\n")){
                    paragraph = paragraph.substring(1);
                    pageCount++;
                }

                String[] surah = paragraph.split("\\n\\n");

                int counter = 0;
                for(String i : surah){
                    // splited the page by the numbers that it gives me ayat
                    String[] ayat = i.replace("\n", "").split("\\s*[\\u0660-\\u0669]+\\s*");
                    if(Mushaf.pages[pageCount+counter-1] == null){
                        Mushaf.pages[pageCount+counter-1] = new Page();
                        Mushaf.pages[pageCount+counter-1].number = pageCount + counter;
                        Mushaf.pages[pageCount+counter-1].ayahNumber = ayatCount;
                        Mushaf.pages[pageCount+counter-1].surahName = surahName.replace("سُورَةُ", "");
//                        System.out.println(Mushaf.pages[pageCount+counter-1].number);
//                        System.out.println(Mushaf.pages[pageCount+counter-1].ayahNumber);
                    }

                    for(String ayah : ayat){
                        numberOfAyatInSurah++;
                        Mushaf.ayat[ayatCount] = new Ayah();
                        Mushaf.ayat[ayatCount].surahNumber = surahNumber;
                        Mushaf.ayat[ayatCount].ayahNumber = numberOfAyatInSurah;
                        Mushaf.ayat[ayatCount].pageNumber = pageCount + counter;
                        Mushaf.ayat[ayatCount].ayah = ayah;
                        Mushaf.ayat[ayatCount].surah = surahName;
//                        System.out.println((surahNumber) + " " + surahName);
//                        System.out.println("رقم الاية");
//                        System.out.println(numberOfAyatInSurah);
//                        System.out.println("رقم الصفحة");
//                        System.out.println(pageCount + counter);
//                        System.out.println(ayah);
                        ayatCount++;
                    }
                    counter++;
                }

                int surahPageCount = surah.length;

                if(surahPageCount > 1) {
                    // i subtract one because i already added it when i found
                    // surah name
                    pageCount += (surahPageCount - 1);
                }

                Mushaf.swarh.get(surahName).numberOfAyat = numberOfAyatInSurah;
//
//                System.out.println(Mushaf.surahs[surahNumber-1].name);
//                System.out.println(Mushaf.surahs[surahNumber-1].number);
//                System.out.println(Mushaf.surahs[surahNumber-1].pageNumber);
//                System.out.println(Mushaf.surahs[surahNumber-1].numberOfAyat);
//                System.out.println("عدد ايات"+numberOfAyatInSurah);
//                System.out.println(ayatCount);
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException, XmlException {
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        loadMushaf();

        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/Mushaf.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Muslim Qibla");
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(780);
        primaryStage.setMinHeight(490);

        primaryStage.setWidth(1440);
        primaryStage.setHeight(800);

        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
