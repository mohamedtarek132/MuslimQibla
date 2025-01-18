package MuslimQibla;

import MuslimQibla.Classes.*;
import MuslimQibla.Controllers.AzkarController;
import MuslimQibla.Controllers.Mushaf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Loader {
    /**
     * read the content of the file that was in the path
     * and returning it content in XWPFDocument variable
     * @param path
     * @return XWPFDocument that contains the content of the file
     */
    public XWPFDocument loadWordDocument(String path){
        try {
            URL url = getClass().getResource(path);
            System.out.println(url);
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

    public PDDocument loadPDFDocument(String path){
        try {
            URL url = getClass().getResource(path);

            File file = new File(url.toURI());

            PDDocument  document = org.apache.pdfbox.Loader.loadPDF(file);

            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void loadAzkar() throws IOException {
        PDDocument azkar = loadPDFDocument("Texts/ar_sa7e7_elazkar.pdf");
        myStripper pdfStripper = new myStripper();

        pdfStripper.setSortByPosition(true);
        pdfStripper.setShouldSeparateByBeads(true);
//        pdfStripper.setWordSeparator(" ");
        pdfStripper.setSpacingTolerance(0.1f);
        pdfStripper.setStartPage(13);
        pdfStripper.setEndPage(13);

        pdfStripper.getText(azkar);
        String zikrr = "";
        zikrr = String.join("\n",  pdfStripper.lines);
        String text = "فوالله ما كذبت ع ى عثمان ولا كذب عثمان ع ى الن  !ولكن اليوم أصاب فيه ما أصاب غضبت فنسيت أن أقولها.";
        System.out.println(text.replaceAll(" ع ى ", " علي "));
        AzkarController.azkar.zikrs.add(new Zikr(zikrr));

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
                Mushaf.ayat[ayatCount].ayah = paragraph.replace("\u200C\u200D\u200E\u200F", "");
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

                for(String page : surah){
                    int enterCount = 0;

                    // splited the page by the numbers that it gives me ayat
                    int length = 0;
                    String[] ayat = page.split("\\s*[\\u0660-\\u0669]+\\s*");

                    if(Mushaf.pages[pageCount+counter-1] == null){
                        Mushaf.pages[pageCount+counter-1] = new Page();
                        Mushaf.pages[pageCount+counter-1].number = pageCount + counter;
                        Mushaf.pages[pageCount+counter-1].ayahNumber = ayatCount;
                        Mushaf.pages[pageCount+counter-1].surahName = surahName.replace("سُورَةُ", "");
//                        System.out.println(Mushaf.pages[pageCount+counter-1].number);
//                        System.out.println(Mushaf.pages[pageCount+counter-1].ayahNumber);
                    }

                    for(String ayah : ayat){
                        ayah = ayah.replace("\n", "");
                        numberOfAyatInSurah++;
//                        if(ayah.contains("\n")) {
//                            for(int i = 0; i < ayah.length(); i++){
//                                if(ayah.charAt(i) == '\n'){
//                                    enterCount++;
//                                    if(enterCount % 2 == 1){
//                                        StringBuilder string = new StringBuilder(ayah);
//                                        string.setCharAt(i, ' ');
//                                        ayah = string.toString();
//                                    }
//                                }
//                            }
//                        }
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
            }
        }
    }
    public void loadMakanAlNzool() throws FileNotFoundException {
        File file = null;
        try {
            file = new File(getClass().getResource("Texts/makanAlNzool").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(file);

        Set<String> keys = Mushaf.swarh.keySet();
        Iterator<String> key = keys.iterator();

        while (scanner.hasNextLine()) {
            String makanElNzool = scanner.nextLine();
            Mushaf.swarh.get(key.next()).makanElnzool = makanElNzool;
        }

        scanner.close();
    }
    public void loadCheckPoints() throws FileNotFoundException {
        File file = null;
        try {
            file = new File(Objects.requireNonNull(getClass().getResource("Texts/checkPoints")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(file);

        Set<String> keys = Mushaf.swarh.keySet();
        Iterator<String> key = keys.iterator();

        while (scanner.hasNextLine()) {
            String makanElNzool = scanner.nextLine();
            Mushaf.swarh.get(key.next()).makanElnzool = makanElNzool;
        }

        scanner.close();
    }
}
