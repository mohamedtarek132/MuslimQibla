package Muslim_Qibla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class main  extends Application{
    public static void main(String[] args) {
        launch(args);
        AnchorPane d = new AnchorPane();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/Mushaf.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hell");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
