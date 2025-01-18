package MuslimQibla.Controllers;

import MuslimQibla.Classes.Ayah;
import MuslimQibla.Classes.Page;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

//hello i am ruining the code by useless comments
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class MainMenu implements Initializable {
    @FXML
    Button azkarButton;
    @FXML
    Button mushafButton;
    @FXML
    Button calenderButton;
    @FXML
    Button prayerTimeButton;
    @FXML
    public Button settingsButton;
    @FXML
    public ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void changePageTo(String url, Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changePageToMushaf(ActionEvent ae){
        Stage stage;
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        changePageTo("../Fxmls/Mushaf.fxml", stage);
    }
    public void changePageToAzkar(ActionEvent ae){
        Stage stage;
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        changePageTo("../Fxmls/Azkar.fxml", stage);
    }
    public void changePageToCalender(ActionEvent ae){
        Stage stage;
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        changePageTo("../Fxmls/Calender.fxml", stage);
    }
    public void changePageToHomePage(ActionEvent ae){
        Stage stage;
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        changePageTo("../Fxmls/Mushaf.fxml", stage);
    }
    public void changePageToSettings(ActionEvent ae){
        Stage stage;
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        changePageTo("../Fxmls/Settings.fxml", stage);
    }
}
