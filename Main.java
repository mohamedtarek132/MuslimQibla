package MuslimQibla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
        System.out.println("end");
    }
    @Override
    public void start(Stage primaryStage) throws IOException, XmlException {
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        Loader loader = new Loader();
        loader.loadMushaf();
        loader.loadMakanAlNzool();
        loader.loadAzkar();

        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/Mushaf.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Muslim Qibla");
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(780);
        primaryStage.setMinHeight(500);

        primaryStage.setWidth(1440);
        primaryStage.setHeight(800);

        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
