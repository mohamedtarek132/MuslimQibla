package MuslimQibla.Classes;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myStripper extends PDFTextStripper {
    float y = 0;
    ArrayList<String> line = new ArrayList<>();
    public ArrayList<String> lines = new ArrayList<>();
    @Override
    protected void startPage(PDPage page) throws IOException
    {
//        String partOfLine = "";
//        if(line != null) {
//            for (String l : line) {
//                partOfLine = l + partOfLine;
//            }
//            if(!partOfLine.isEmpty()) {
//                System.out.println(partOfLine);
//                lines.add(partOfLine);
//            }
//        }
//        line = new ArrayList<>();

        super.startPage(page);
    }

    @Override
    protected void writeLineSeparator() throws IOException
    {
//        String partOfLine = "";
//        if(line != null) {
//            for (String l : line) {
////                System.out.println(l);
//                partOfLine = l.strip().replace("0|1|2|3|4|5|6|7|8|9", "") + partOfLine;
//            }
//            if(!partOfLine.isEmpty()) {
//                System.out.println(y);
//                System.out.println(partOfLine);
//                lines.add(partOfLine);
//            }
//        }

//        line = new ArrayList<>();
        super.writeLineSeparator();
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException
    {
        float difference = textPositions.get(0).getY() - y;
//        System.out.println(difference);
        if (difference > 9)
        {
            String partOfLine = "";
            if(line != null) {
//                System.out.println("dfd");

                for (String l : line) {
                    System.out.println(l);
                    String []ls = l.split(" ");
                    String []s = partOfLine.split(" ");
                    if(!l.isEmpty()) {
                        if (ls[(ls.length - 1)].length() == 1 && s[0].length() == 1 && l.charAt(l.length() - 1) == ' '){
                            System.out.println(1);
                            l = l.substring(0, l.length() - 1);
                        }
                        else if (ls[(ls.length - 1)].length() > 1 && s[0].length() > 1 && l.charAt(l.length() - 1) != ' '){
                            l = l + " ";
                            System.out.println(2);
                        }
                    }
                    partOfLine = l + partOfLine;
                }
                if(!partOfLine.isEmpty()) {
//                    System.out.println(y);
                    System.out.println(partOfLine.contains(" ع ى "));
                    partOfLine = partOfLine.replaceAll(" ء|$ءٍ| ءٍ | ء$", " شئ ")
                            .replaceAll(" ع +ى | عى ", " علي ")
                            .replaceAll(" م ا ", " ما ")
                            .replaceAll(" الن ", " النبي ")
                            .replaceAll(" الله م ", " اللهم ")
                            .replaceAll(" الله أكر ", " الله أكبر ")
                            .replaceAll(" ي ", " في ")
                            .replaceAll(" يئ ", " يجئ ")
                            .replaceAll(" غروا ", " غروبها ")
                            .replaceAll(" إي ", " إلي ")
                            .replaceAll(" الرمزي ", " الترمزي ")
                            .replaceAll(" عافي ", " عافني في ")
                            .replaceAll(" عاف ", " عافني ")
                            .replaceAll(" القر ", " القبر ")
                            .strip();
//                    for(int i = 0; i < partOfLine.length(); i++){
//                        System.out.println(partOfLine.charAt(i));
//                    }
                    System.out.println(partOfLine);
                    lines.add(partOfLine);
                }
                line = new ArrayList<>();
            }
        }
        String unicode = "";
        for (TextPosition texts : textPositions) {
            // Print each character along with its font information
            System.out.println("Character: " + texts.getUnicode() + ", Font: " + texts.getFont().getName());
        }
//        System.out.println(unicode);
        line.add(text.strip()
                .replaceAll("\\d+\\.|\\uF074|\\uF065|ْ|ࣳ|ّ|ْ|ٓ|َ|ُ|ِ|\\(\\(|\\)\\)|[\\u064B-\\u0652]|:|\\n|\uF05B|\uF05D", "")
                .replaceAll(",", " ")
        );

        y = textPositions.get(0).getY();
        super.writeString(text, textPositions);
    }
}
