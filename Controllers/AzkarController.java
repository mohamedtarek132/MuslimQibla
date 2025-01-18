package MuslimQibla.Controllers;
import MuslimQibla.Classes.Azkar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;

//hello i am ruining the code by useless comments
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AzkarController implements Initializable{
    @FXML
    AnchorPane anchor;
    @FXML
    ScrollPane scrollPane;
    @FXML
    ImageView backgroundImage;
    @FXML
    Button mainMenuButton;
    @FXML
    Text ayah;
    static public Azkar azkar = new Azkar();
    String buttonCss = getClass().getResource("../CssFiles/button.css").toExternalForm();
    String textFieldCss = getClass().getResource("../CssFiles/textField.css").toExternalForm();
    String scrollPaneCss = getClass().getResource("../CssFiles/scrollPane.css").toExternalForm();
    public void addListeners(){
        anchor.widthProperty().addListener((observableValue, oldValue, newValue)  -> {
            double widthDifferenceBetweenAnchorAndScrollPane = newValue.doubleValue() - scrollPane.getPrefWidth();
            double newXValueScrollPane = widthDifferenceBetweenAnchorAndScrollPane/2 + 10;
            double widthDifferenceBetweenAnchorAndAyah = newValue.doubleValue() - ayah.getWrappingWidth();
            double newXValueAyah = widthDifferenceBetweenAnchorAndAyah/2;
            scrollPane.setLayoutX(newXValueScrollPane);
            ayah.setLayoutX(newXValueAyah);
        });
    }
    public void bindWidth(){
        backgroundImage.fitWidthProperty().bind(anchor.widthProperty());
        scrollPane.prefWidthProperty().bind(anchor.widthProperty().subtract(1440-1150));
    }
    public void bindHeight(){
        backgroundImage.fitHeightProperty().bind(anchor.heightProperty());
        scrollPane.prefHeightProperty().bind(anchor.heightProperty().subtract(1024 - 740));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindWidth();
        bindHeight();
        addListeners();
        try {
            addAzkar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchor.getChildren().remove(mainMenuButton);

        mainMenuButton = new Button();

        Image img = new Image(getClass().getResourceAsStream("../Images/menu.png"));
        ImageView image = new ImageView(img);

        mainMenuButton.setGraphic(image);
        mainMenuButton.setBackground(Background.EMPTY);
        mainMenuButton.setOnAction(this::mainMenu);
        anchor.getChildren().add(mainMenuButton);

        AnchorPane.setRightAnchor(mainMenuButton, 40.799999999999955);
        AnchorPane.setTopAnchor(mainMenuButton, 42.2);
    }
    public void mainMenu(ActionEvent ae) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxmls/MainMenu.fxml"));
        AnchorPane mainMenu = null;
        try {
            mainMenu = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPane.setTopAnchor(mainMenu, 0.0d);
        AnchorPane.setRightAnchor(mainMenu, 0.0);
        AnchorPane.setBottomAnchor(mainMenu, 0.0);
        anchor.getChildren().add(mainMenu);
    }
    public void addAzkar() throws IOException {
        VBox vBox = new VBox(20);
        for(int i = 0; i < azkar.zikrs.size(); i++)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxmls/zikr.fxml"));
            AnchorPane zikr = loader.load();
            Zikr temp = loader.getController();
            temp.zikrText.setText(azkar.zikrs.get(i).zikr);
            vBox.getChildren().add(zikr);
        }
        int x = 255;
        int y = 200;
        int prefWidth = 1150;
        int prefHeight = 740;
        anchor.getChildren().remove(scrollPane);
        scrollPane = new ScrollPane(vBox);
        scrollPane.setLayoutX(x);
        scrollPane.setLayoutY(y);
        scrollPane.setPrefWidth(prefWidth);
        scrollPane.setPrefHeight(prefHeight);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.getStylesheets().add(scrollPaneCss);
        AnchorPane.setBottomAnchor(scrollPane, 40d);
        AnchorPane.setTopAnchor(scrollPane, 200d);
        anchor.getChildren().add(scrollPane);
    }
}
