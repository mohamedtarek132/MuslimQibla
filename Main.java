package MuslimQibla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
//        primaryStage.initStyle(StageStyle.UNDECORATED);
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
