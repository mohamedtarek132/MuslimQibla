package Muslim_Qibla.Controllers;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

//hello i am ruining the code by useless comments
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Mushaf implements Initializable{
    @FXML
    TextField ayahSearchField;
    @FXML
    AnchorPane anchor;
    @FXML
    ImageView backgroundImage;
    @FXML
    Rectangle shadeRectangle;
    @FXML
    Rectangle menu;
    @FXML
    Line l;
    @FXML
            //hello i am ruining the code by useless comments
    Rectangle quranRectangle;
    @FXML
    TextFlow quranBody;
    @FXML
    Polyline rightArrow;
    @FXML
    Polyline leftArrow;
    @FXML
    Button pageButton;
    @FXML
    Rectangle ayahSearchRec;
    @FXML
    ImageView searchIcon;
    @FXML
    Text surah;
    @FXML
    Button surahButton;
    @FXML
    Text juz;
    @FXML
    Button juzButton;
    @FXML
    ScrollPane scrollMenu;
    @FXML
    Text menuTitle;
    char []arabicNumbers = new char[]{'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};

    @FXML
    ChangeListener<Number> quranRectangleHeightListener =(observableValue, number, t1) -> {
        double half = quranRectangle.getLayoutY() + quranRectangle.getHeight()/2 - 25;
        double x = 250 - 187;
        double top = quranRectangle.getLayoutY() + quranRectangle.getHeight() * ( x/761);
        AnchorPane.setTopAnchor(leftArrow, half);
        AnchorPane.setTopAnchor(rightArrow, half);
        AnchorPane.setTopAnchor(quranBody,top);
    };
    @FXML
    ChangeListener<Number> quranRectangleWidthListener = ((observableValue, number, t1) -> {
        double half = quranRectangle.getLayoutX() + quranRectangle.getWidth()/2 ;

        pageButton.setLayoutX(half - 75);
        menu.setLayoutX(half - menu.getWidth()/2);
        scrollMenu.setLayoutX(half - menu.getWidth()/2 + 50);
        l.setLayoutX(scrollMenu.getLayoutX());
        menuTitle.setLayoutX(half - menu.getWidth()/2 +  menu.getWidth()*210/680.0);
    });
    @FXML
    ChangeListener<Number> quranBodyHeightListener = ((observableValue, oldValue, newValue) ->{
        changeFont(oldValue.doubleValue(),newValue.doubleValue(),"h");
    } );
    ChangeListener<Number> quranBodyWidthListener = ((observableValue, oldValue, newValue) ->{
        changeFont(oldValue.doubleValue(),newValue.doubleValue(),"w");
    } );
    ChangeListener<Number> anchorWidthListener = ((observableValue, oldValue, newValue) ->{
        int baseAnchorWidth = 1440;
        int xOffset = 119;

        double newAnchorWidth = anchor.getWidth();
        double newQuranRecWidth = 950;
        double newMenuWidth = 680;
        double newScrollMenuWidth = 605;
        double newAyahSearchRecWidth = newAnchorWidth - (baseAnchorWidth - 450 - xOffset);

        double differenceBetweenNewAndBaseAnchorWidth = newAnchorWidth - baseAnchorWidth;
        double proportion = 395.0/450;


        if(newAnchorWidth >= baseAnchorWidth){
            newQuranRecWidth = newAnchorWidth - (baseAnchorWidth - newQuranRecWidth);

            quranRectangle.setLayoutX(219);

            rightArrow.setLayoutX(290);

            searchIcon.setLayoutX(634.4);
            ayahSearchField.setLayoutX(236.2);
            ayahSearchRec.setLayoutX(220);

            quranBody.setLayoutX(290);

            surah.setLayoutX(737);
            surahButton.setLayoutX(532);

            juz.setLayoutX(425);
            juzButton.setLayoutX(219.2);

            ayahSearchRec.setWidth(450);
            ayahSearchField.setPrefWidth(395);
        }else if (newAnchorWidth <= baseAnchorWidth - xOffset){
            newQuranRecWidth = newAnchorWidth - (baseAnchorWidth - newQuranRecWidth - xOffset);
            newMenuWidth = newAnchorWidth - (baseAnchorWidth - newMenuWidth - xOffset);
            newScrollMenuWidth = newAnchorWidth - (baseAnchorWidth - newScrollMenuWidth - xOffset);

            quranRectangle.setLayoutX(219 - xOffset);
            rightArrow.setLayoutX(290- xOffset);
            ayahSearchField.setLayoutX(236.2 - xOffset);
            ayahSearchRec.setLayoutX(220 - xOffset);
            quranBody.setLayoutX(290- xOffset );
            surah.setLayoutX(737 - xOffset);
            surahButton.setLayoutX(532 - xOffset);
            juz.setLayoutX(425 - xOffset);
            juzButton.setLayoutX(219.2 - xOffset);

            if(anchor.getWidth() > 1100) {
                searchIcon.setLayoutX(634.4 + differenceBetweenNewAndBaseAnchorWidth);
                ayahSearchRec.setWidth(newAyahSearchRecWidth);
                ayahSearchField.setPrefWidth(newAyahSearchRecWidth * proportion - 20);
            } else{
                newAyahSearchRecWidth = 1100 - (baseAnchorWidth - 450 - xOffset);
                searchIcon.setLayoutX(634.4 - 340);
                ayahSearchRec.setWidth(newAyahSearchRecWidth);
                ayahSearchField.setPrefWidth(newAyahSearchRecWidth * proportion - 20);
            }
        } else{
            quranRectangle.setLayoutX(219 + differenceBetweenNewAndBaseAnchorWidth);
            rightArrow.setLayoutX(290 + differenceBetweenNewAndBaseAnchorWidth);
            searchIcon.setLayoutX(634.4 + differenceBetweenNewAndBaseAnchorWidth);
            ayahSearchField.setLayoutX(236.2 + differenceBetweenNewAndBaseAnchorWidth);
            ayahSearchRec.setLayoutX(220 + differenceBetweenNewAndBaseAnchorWidth);
            quranBody.setLayoutX(290 + differenceBetweenNewAndBaseAnchorWidth);
            surah.setLayoutX(737 + differenceBetweenNewAndBaseAnchorWidth);
            surahButton.setLayoutX(532 + differenceBetweenNewAndBaseAnchorWidth);
            juz.setLayoutX(425 + differenceBetweenNewAndBaseAnchorWidth);
            juzButton.setLayoutX(219.2 + differenceBetweenNewAndBaseAnchorWidth);

            double half = quranRectangle.getLayoutX() + quranRectangle.getWidth()/2;
            pageButton.setLayoutX(half - 75);
            menu.setLayoutX(half - menu.getWidth()/2);
            scrollMenu.setLayoutX(half - menu.getWidth()/2 + 50);
            l.setLayoutX(half - menu.getWidth()/2 + 50);
            menuTitle.setLayoutX(half - menu.getWidth()/2 + 210);
        }

        quranRectangle.setWidth(newQuranRecWidth);
        menu.setWidth(newMenuWidth);
        scrollMenu.setPrefWidth(newScrollMenuWidth);
    });
    ChangeListener<Number> anchorHeightListener = ((observableValue, oldValue, newValue) -> {
        scrollMenu.setPrefHeight(newValue.doubleValue() - (1024 - 613));
    });
    //hello i am ruining the code by useless comments
    public void addListeners() {
        quranRectangle.widthProperty().addListener(quranRectangleWidthListener);
        quranRectangle.heightProperty().addListener(quranRectangleHeightListener);
        quranBody.prefHeightProperty().addListener(quranBodyHeightListener);
        quranBody.prefWidthProperty().addListener(quranBodyWidthListener);
        anchor.widthProperty().addListener(anchorWidthListener);
        anchor.heightProperty().addListener(anchorHeightListener);
    }
    public void bindWidth(){
        backgroundImage.fitWidthProperty().bind(anchor.widthProperty());
        shadeRectangle.widthProperty().bind(anchor.widthProperty());
        l.endXProperty().bind(menu.widthProperty().subtract(680-580));
        quranBody.prefWidthProperty().bind(quranRectangle.widthProperty().subtract(950-785));
    }
    public void bindHeight() {
        backgroundImage.fitHeightProperty().bind(anchor.heightProperty());
        shadeRectangle.heightProperty().bind(anchor.heightProperty());
        quranRectangle.heightProperty().bind(anchor.heightProperty().subtract(1024 - 761));
        menu.heightProperty().bind(anchor.heightProperty().subtract(1024 - 800));
        quranBody.prefHeightProperty().bind(anchor.heightProperty().subtract(1024-675));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindWidth();

        bindHeight();

        addListeners();
    }
    public void menuToFront(){
        shadeRectangle.toFront();
        menu.toFront();
        l.toFront();
        scrollMenu.toFront();
        menuTitle.toFront();
    }
    public void menuToBack(){
        shadeRectangle.toBack();
        menu.toBack();
        l.toBack();
        scrollMenu.toBack();
        menuTitle.toBack();
    }
    public String toArabicNumbers(int number)
    {
        String pageNumber = "";
        int temp = number;
        while(temp !=0){
            char temp2 = arabicNumbers[temp%10];
            pageNumber += temp2;
            temp/=10;
        }

        pageNumber = new StringBuilder(pageNumber).reverse().toString();

        return pageNumber;
    }
    public void changeFont(double oldValue, double newValue, String type){
        if (quranBody.getChildren().get(0) instanceof Text && quranBody.getHeight()!=0 && quranBody.getWidth()!=0) {
            Text text = (Text) quranBody.getChildren().get(0);
            String font = text.getFont().getFamily();
            double size = 32;
            int prefHeight = 800;
            int prefWidth = 800;
            int pref = prefWidth * prefHeight;
            if (type.equals("w")){
                newValue = newValue * quranBody.getPrefHeight();
            }else if (type.equals("h")){
                newValue =  (newValue * quranBody.getPrefWidth());
            }
            double proportion = newValue/pref;

            if (proportion < 0.05){
                size = (size * (proportion + 0.2));
            }
            else if (proportion < 0.1){
                size = (size * (proportion + 0.24));
            }else if (proportion < 0.2){
                size = (size * (proportion + 0.28));
            }
            else if (proportion <= 0.51){
                size = (size * (proportion + 0.285));
            }else if (proportion <= 1){
                size = (size * (proportion + 0.22));
            }else{
                size = (size * (proportion + 0.2));
            }

            for (int i = 0; i < quranBody.getChildren().size(); i++) {
                if(quranBody.getChildren().get(i) instanceof Text) {
                    Text temp1 = (Text) quranBody.getChildren().get(i);
                    temp1.setFont(Font.font(font, size));
                }
                //hello i am ruining the code by useless comments
            }
        }
    }

    public void setScrollMenuAndMenuTitle(String menuName, Node content, int style){
        double x = scrollMenu.getLayoutX();
        double y = scrollMenu.getLayoutY();
        double prefWidth = scrollMenu.getPrefWidth();
        double prefHeight = scrollMenu.getPrefHeight();

        anchor.getChildren().remove(scrollMenu);
        anchor.getChildren().remove(menuTitle);

        scrollMenu = new ScrollPane(content);
        scrollMenu.setLayoutX(x);
        scrollMenu.setLayoutY(y);
        scrollMenu.setPrefWidth(prefWidth);
        scrollMenu.setPrefHeight(prefHeight);
        scrollMenu.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollMenu.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/scrollPane.css");


        menuTitle.setFont(Font.font("noon", 38));
        menuTitle.setText(menuName);
        menuTitle.setFill(Paint.valueOf("white"));
        menuTitle.setLayoutX(menuTitle.getLayoutX());
        menuTitle.setLayoutY(185);
        menuTitle.setFontSmoothingType(FontSmoothingType.GRAY);

        anchor.getChildren().add(scrollMenu);
        anchor.getChildren().add(menuTitle);
    }

    public GridPane savepoint(){
        VBox surahName = new VBox(16);
        VBox pageNumber = new VBox(18.7);
        VBox savepointName = new VBox(10);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);

        for(int i = 0; i < 200; i++)
        {
            Text name = new Text("سورة الفاتحة");
            name.setFont(Font.font("noon",22));
            name.setFill(Paint.valueOf("White"));
            name.setFontSmoothingType(FontSmoothingType.GRAY);


            surahName.getChildren().add(name);

            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(45);
            button.setFont(Font.font("noon",22));
            button.setText("علامة  ۱");
            button.setPadding(new Insets(-20,0, -15,0));

            savepointName.getChildren().add(button);

            VBox numbers = new VBox(7);
            numbers.setAlignment(Pos.CENTER);

            Text number = new Text("صفحة" );
            number.setFont(Font.font("noon",22));
            number.setFill(Paint.valueOf("White"));
            number.setTextAlignment(TextAlignment.CENTER);
            number.setFontSmoothingType(FontSmoothingType.GRAY);
            number.setBoundsType(TextBoundsType.VISUAL);

            numbers.getChildren().add(number);

            number = new Text("۱" );
            number.setFont(Font.font("noon",22));
            number.setFill(Paint.valueOf("White"));
            number.setTextAlignment(TextAlignment.RIGHT);
            number.setFontSmoothingType(FontSmoothingType.GRAY);
            number.setBoundsType(TextBoundsType.VISUAL);

            numbers.getChildren().add(number);

            pageNumber.getChildren().add(numbers);
        }
        int i = 10;
        gridPane.add(surahName, i, 0);
        gridPane.add(savepointName, i + 1, 0);
        gridPane.add(pageNumber, i + 2, 0);

        return gridPane;
    }
    public void saveMenu(ActionEvent ae){
        setScrollMenuAndMenuTitle("حفظ العلامة", savepoint(), 3);
        menuToFront();
    }
    public void savepointMenu(ActionEvent ae){
        setScrollMenuAndMenuTitle("حفظ العلامة", savepoint(), 3);
        menuToFront();
    }
    public void surahMenu(ActionEvent ae) throws IOException {
        VBox surahs = new VBox(17);
        for(int i = 0; i < 113; i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxmls/surah.fxml"));
            AnchorPane surah = loader.load();
            Surah temp = loader.getController();
            temp.setSurahNumber(toArabicNumbers(i + 1));
            surahs.getChildren().add(surah);
        }
        setScrollMenuAndMenuTitle("الإنتقال الى سورة", surahs, 2);
        menuToFront();
    }
    public void pageMenu(ActionEvent ae){
        GridPane gridPane = new GridPane();
        GridPane leftGridPane = new GridPane();
        GridPane rightGridPane = new GridPane();

        VBox leftGridButtons = new VBox(10);
        VBox rightGridButtons = new VBox(10);

        VBox leftGridSurahNames = new VBox(10);
        VBox rightGridSurahNames = new VBox(10);

        gridPane.setHgap(40);
        rightGridPane.setHgap(20);
        leftGridPane.setHgap(20);

        gridPane.getColumnConstraints().add(new ColumnConstraints(250));

        for (int i = 1; i <= 300; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));

            String pageNumber = toArabicNumbers(i * 2);

            button.setText("صفحة " + pageNumber);
            leftGridButtons.getChildren().add(button);

            Text text = new Text("البَقَرَة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));
            text.setFontSmoothingType(FontSmoothingType.GRAY);

            leftGridSurahNames.getChildren().add(text);
        }

        for (int i = 0; i < 301; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));

            String pageNumber = toArabicNumbers(i * 2 + 1);

            button.setText("صفحة " + pageNumber);
            rightGridButtons.getChildren().add(button);

            Text text = new Text("الفَاتِحَة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));
            text.setFontSmoothingType(FontSmoothingType.GRAY);

            rightGridSurahNames.getChildren().add(text);
        }
        leftGridPane.add(leftGridButtons, 0,0);
        rightGridPane.add(rightGridButtons, 0,0);

        leftGridPane.add(leftGridSurahNames,1,0);
        rightGridPane.add(rightGridSurahNames,1,0);

        gridPane.add(leftGridPane,0,0);
        gridPane.add(rightGridPane,2,0);


        setScrollMenuAndMenuTitle("الإنتقال الى صفحة", gridPane, 1);
        menuToFront();
        //hello i am ruining the code by useless comments
    }
    public void juzMenu(ActionEvent ae){
        menuToFront();

        GridPane gridPane = new GridPane();
        GridPane leftGridPane = new GridPane();
        GridPane rightGridPane = new GridPane();

        VBox leftGridButtons = new VBox(10);
        VBox rightGridButtons = new VBox(10);

        VBox leftGridSurahNames = new VBox(10);
        VBox rightGridSurahNames = new VBox(10);

        gridPane.setHgap(40);
        rightGridPane.setHgap(10);
        leftGridPane.setHgap(10);

        gridPane.getColumnConstraints().add(new ColumnConstraints(250));

        for (int i = 1; i <= 15; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));

            String juzNumber = toArabicNumbers(i*2);

            button.setText("الجزء  " + juzNumber);

            leftGridButtons.getChildren().add(button);

            Text text = new Text("البسملة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));

            leftGridSurahNames.getChildren().add(text);
        }

        for (int i = 0; i < 15; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));

            String juzNumber = toArabicNumbers(i * 2 + 1);

            button.setText("الجزء  " + juzNumber);
            rightGridButtons.getChildren().add(button);

            Text text = new Text("البسملة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));
            rightGridSurahNames.getChildren().add(text);
        }
        leftGridPane.add(leftGridButtons, 0,0);
        rightGridPane.add(rightGridButtons, 0,0);

        leftGridPane.add(leftGridSurahNames,1,0);
        rightGridPane.add(rightGridSurahNames,1,0);

        gridPane.add(leftGridPane,0,0);
        gridPane.add(rightGridPane,2,0);

        setScrollMenuAndMenuTitle("الإنتقال الى جزء", gridPane, 1);
        menuToFront();
        //hello i am ruining the code by useless comments
    }
    //hello i am ruining the code by useless comments
}
