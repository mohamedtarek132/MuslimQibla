package Muslim_Qibla.Controllers;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

//hello i am ruining the code by useless comments
import java.net.URL;
import java.util.ResourceBundle;

public class Mushaf implements Initializable{
    @FXML
    TextField ayahSearchField;
    @FXML
    ScrollBar i;
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
    Text goza;
    @FXML
    Button gozaButton;
    @FXML
    ScrollPane scrollMenu;
    @FXML
    Text menuTitle;

    @FXML
    ChangeListener quranRectangleHeightListner =(observableValue, number, t1) -> {
        double half = quranRectangle.getLayoutY() + quranRectangle.getHeight()/2 - 25;
        double x = 250 - 187;
        double top = quranRectangle.getLayoutY() + quranRectangle.getHeight() * ( x/761);
        AnchorPane.setTopAnchor(leftArrow, half);
        AnchorPane.setTopAnchor(rightArrow, half);
        AnchorPane.setTopAnchor(quranBody,top);
    };
    @FXML
    ChangeListener quranRectangleWidthListner = ((observableValue, number, t1) -> {
        double half = quranRectangle.getLayoutX() + quranRectangle.getWidth()/2 ;

        AnchorPane.setLeftAnchor(pageButton, half - 75);

        menu.setLayoutX(half - menu.getWidth()/2);
        scrollMenu.setLayoutX(half - menu.getWidth()/2 + 50);
        l.setLayoutX(scrollMenu.getLayoutX());
        menuTitle.setLayoutX(half - menu.getWidth()/2 +  menu.getWidth()*210/680.0);

        System.out.println(half - menu.getWidth()/2 + menu.getWidth()*210/680.0);
        System.out.println(half - menu.getWidth()/2);
    });
    @FXML
    ChangeListener quranBodyHeightListner = ((observableValue, oldValue, newValue) ->{
        if(oldValue instanceof Number){
            Number oldValue1 = (Number) oldValue;
            Number newValue1 = (Number) newValue;
            changeFont(oldValue1.doubleValue(),newValue1.doubleValue(),"h");
        }
    } );
    ChangeListener quranBodyWidthListner = ((observableValue, oldValue, newValue) ->{
        if(oldValue instanceof Number){
            Number oldValue1 = (Number) oldValue;
            Number newValue1 = (Number) newValue;
            changeFont(oldValue1.doubleValue(),newValue1.doubleValue(),"w");
        }
    } );
    //hello i am ruining the code by useless comments

    @FXML
    void trying(ActionEvent event){
        System.out.println(anchor.getHeight());
        System.out.println(anchor.getWidth());
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
            if(proportion > 0.5)
            size = (int)(size * (proportion + 0.285));
            else if (proportion < 0.05){
                size = (int)(size * (proportion + 0.2));
            }
            else if (proportion < 0.1){
                size = (size * (proportion + 0.24));
            }else if (proportion < 0.2){
                size = (size * (proportion + 0.275));
            }
            else{
                size = (int)(size * (proportion + 0.31));
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
    public void saveMenu(ActionEvent ae){
        menuToFront();
    }
    public void surahMenu(ActionEvent ae){
        menuToFront();
    }
    public void pageMenu(ActionEvent ae){
        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();

        VBox buttons = new VBox(10);
        VBox buttons2 = new VBox(10);

        VBox surahName = new VBox(10);
        VBox surahName2 = new VBox(10);

        gridPane.setHgap(40);
        gridPane2.setHgap(20);
        gridPane1.setHgap(20);

        gridPane.getColumnConstraints().add(new ColumnConstraints(250));

        for (int i = 0; i < 300; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));
            button.setText("صفحة ۲");
            buttons.getChildren().add(button);

            Text text = new Text("البَقَرَة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));
            surahName.getChildren().add(text);
        }

        for (int i = 0; i < 301; i++) {
            Button button = new Button();
            button.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/button.css");
            button.setPrefWidth(180);
            button.setPrefHeight(40);
            button.setFont(Font.font("Tajwal",16));
            button.setText("صفحة ۱");
            buttons2.getChildren().add(button);

            Text text = new Text("الفَاتِحَة");
            text.setFont(Font.font("noon",22));
            text.setFill(Paint.valueOf("White"));
            surahName2.getChildren().add(text);
        }
        gridPane1.add(buttons, 0,0);
        gridPane2.add(buttons2, 0,0);

        gridPane1.add(surahName,1,0);
        gridPane2.add(surahName2,1,0);

        gridPane.add(gridPane1,0,0);
        gridPane.add(gridPane2,2,0);

        double x = scrollMenu.getLayoutX();
        double y = scrollMenu.getLayoutY();
        double prefWidth = scrollMenu.getPrefWidth();
        double prefHeight = scrollMenu.getPrefHeight();

        anchor.getChildren().remove(scrollMenu);
        anchor.getChildren().remove(menuTitle);

        scrollMenu = new ScrollPane(gridPane);
        scrollMenu.setLayoutX(x);
        scrollMenu.setLayoutY(y);
        scrollMenu.setPrefWidth(prefWidth);
        scrollMenu.setPrefHeight(prefHeight);
        scrollMenu.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollMenu.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollMenu.getStylesheets().add("file:/C:/Users/hp/Documents/Programming%20projects/Java%20Projects/out/production/Java%20Projects/Muslim_Qibla/Css_Files/scrollPane.css");


        menuTitle.setFont(Font.font("noon", 38));
        menuTitle.setText("الإنتقال الى صفحة");
        menuTitle.setFill(Paint.valueOf("white"));
        menuTitle.setLayoutX(menuTitle.getLayoutX());
        menuTitle.setLayoutY(185);

        anchor.getChildren().add(scrollMenu);
        anchor.getChildren().add(menuTitle);

        menuToFront();
        //hello i am ruining the code by useless comments
    }
    public void gozaMenu(ActionEvent ae){
        menuToFront();
    }
    //hello i am ruining the code by useless comments
    public void savepointMenu(ActionEvent ae){
        menuToFront();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundImage.fitWidthProperty().bind(anchor.widthProperty());
        shadeRectangle.widthProperty().bind(anchor.widthProperty());
        l.endXProperty().bind(menu.widthProperty().subtract(680-580));
        quranBody.prefWidthProperty().bind(quranRectangle.widthProperty().subtract(950-785));

        backgroundImage.fitHeightProperty().bind(anchor.heightProperty());
        shadeRectangle.heightProperty().bind(anchor.heightProperty());
        quranRectangle.heightProperty().bind(anchor.heightProperty().subtract(1024 - 761));
        menu.heightProperty().bind(anchor.heightProperty().subtract(1024 - 800));
        quranBody.prefHeightProperty().bind(anchor.heightProperty().subtract(1024-675));
        scrollMenu.prefHeightProperty().bind(anchor.heightProperty().subtract(1024-613));

        quranRectangle.widthProperty().addListener(quranRectangleWidthListner);
        quranRectangle.heightProperty().addListener(quranRectangleHeightListner);
        quranBody.prefHeightProperty().addListener(quranBodyHeightListner);
        quranBody.prefWidthProperty().addListener(quranBodyWidthListner);

        anchor.widthProperty().addListener((observableValue, oldValue, newValue) ->{
            double newQuarnRecWidth = 950;
            double newMenuWidth = 680;
            double newScrollMenuWidth = 605;
            double newAyahSearchRecWidth = anchor.getWidth() - (1440 - 450 - 119);
            double value = anchor.getWidth() - 1440;
            double newQuranRecLeftConstraint = anchor.getWidth() - (1440 - 219);
            double proportion = 395.0/450;
            if(anchor.getWidth() >= 1440){
                newQuarnRecWidth = anchor.getWidth() - (1440 - newQuarnRecWidth);

                quranRectangle.setLayoutX(219);
                rightArrow.setLayoutX(290);
                searchIcon.setLayoutX(634.4 );
                ayahSearchField.setLayoutX(236.2 );
                ayahSearchRec.setLayoutX(220 );
                quranBody.setLayoutX(290 );
                surah.setLayoutX(737);
                surahButton.setLayoutX(532);
                goza.setLayoutX(425);
                gozaButton.setLayoutX(219.2);

                ayahSearchRec.setWidth(450);
                ayahSearchField.setPrefWidth(395);
            }else if (anchor.getWidth() <= 1440 - 119){
                newQuarnRecWidth = anchor.getWidth() - (1440 - newQuarnRecWidth - 119);
                newMenuWidth = anchor.getWidth() - (1440 - newMenuWidth - 119);
                newScrollMenuWidth = anchor.getWidth() - (1440 - newScrollMenuWidth - 119);

                quranRectangle.setLayoutX(219 - 119);
                rightArrow.setLayoutX(290- 119 );
                ayahSearchField.setLayoutX(236.2 - 119);
                ayahSearchRec.setLayoutX(220 - 119);
                quranBody.setLayoutX(290- 119 );
                surah.setLayoutX(737 - 119);
                surahButton.setLayoutX(532 - 119);
                goza.setLayoutX(425 - 119);
                gozaButton.setLayoutX(219.2 - 119);
                if(anchor.getWidth() > 1100) {
                    searchIcon.setLayoutX(634.4 + value);
                    ayahSearchRec.setWidth(newAyahSearchRecWidth);
                    ayahSearchField.setPrefWidth(newAyahSearchRecWidth * proportion - 20);
                } else{
                    newAyahSearchRecWidth = 1100 - (1440 - 450 - 119);
                    searchIcon.setLayoutX(634.4 - 340);
                    ayahSearchRec.setWidth(1100 - (1440 - 450 - 119));
                    ayahSearchField.setPrefWidth(newAyahSearchRecWidth * proportion - 20);
                }
            } else{
                quranRectangle.setLayoutX(newQuranRecLeftConstraint);
                rightArrow.setLayoutX(290 + value);
                searchIcon.setLayoutX(634.4 + value);
                ayahSearchField.setLayoutX(236.2 + value);
                ayahSearchRec.setLayoutX(220 + value);
                quranBody.setLayoutX(290 + value);
                surah.setLayoutX(737 + value);
                surahButton.setLayoutX(532 + value);
                goza.setLayoutX(425 + value);
                gozaButton.setLayoutX(219.2 + value);
                double half = quranRectangle.getLayoutX() + quranRectangle.getWidth()/2;
                AnchorPane.setLeftAnchor(pageButton, half - 75);
                menu.setLayoutX(half - menu.getWidth()/2);
                scrollMenu.setLayoutX(half - menu.getWidth()/2 + 50);
                l.setLayoutX(half - menu.getWidth()/2 + 50);
                menuTitle.setLayoutX(half - menu.getWidth()/2 + 210);
            }

            quranRectangle.setWidth(newQuarnRecWidth);
            menu.setWidth(newMenuWidth);
            scrollMenu.setPrefWidth(newScrollMenuWidth);

//            if(newQuranRecLeftConstraint > 100 && newQuranRecLeftConstraint < 220 ){
//                AnchorPane.setLeftAnchor(quranRectangle,newQuranRecLeftConstraint);
//                AnchorPane.setLeftAnchor(rightArrow,240 - value);
//                AnchorPane.setLeftAnchor(searchIcon, 634.4 - value);
//                AnchorPane.setLeftAnchor(ayahSearchField,236.2 - value);
//                AnchorPane.setLeftAnchor(ayahSearchRec,220 - value);
//                AnchorPane.setLeftAnchor(quranBody,290 - value);
//                double half = quranRectangle.getLayoutX() + quranRectangle.getWidth()/2 - 75;
//                AnchorPane.setLeftAnchor(pageButton, half);
//                AnchorPane.setLeftAnchor(pageButtonRect, half);
//                AnchorPane.setLeftAnchor(pageNumber, half + 50);
//
//            }else {
//
//                if (newAyahSearchRecWidth > 450) {
//                    ayahSearchRec.setWidth(450);
//                    ayahSearchField.setPrefWidth(395);
//                    AnchorPane.setLeftAnchor(searchIcon, 634.4);
//                } else if (newAyahSearchRecWidth < 150) {
//                    ayahSearchRec.setWidth(150);
//                    ayahSearchField.setPrefWidth(150 * proportion - 30);
//                } else {
//                    //hello i am ruining the code by useless comments
//                    ayahSearchRec.setWidth(newAyahSearchRecWidth);
//                    ayahSearchField.setPrefWidth(newAyahSearchRecWidth * proportion - 30);
//                    AnchorPane.setLeftAnchor(searchIcon, 634.4 - value);
//                }
//                quranRectangle.setWidth(newQuarnRecWidth);
//            }
        });
    }
    //hello i am ruining the code by useless comments

}
