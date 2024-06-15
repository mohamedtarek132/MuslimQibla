package Muslim_Qibla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//hello i am ruining the code by useless comments
public class main  extends Application{
    public static void main(String[] args) {
        launch(args);
        AnchorPane d = new AnchorPane();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        //hello i am ruining the code by useless comments

//        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/Mushaf.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Muslim Qibla");
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(780);
        primaryStage.setMinHeight(490);
        //hello i am ruining the code by useless comments

        primaryStage.setWidth(1440);
        primaryStage.setHeight(800);
        //hello i am ruining the code by useless comments

//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    //hello i am ruining the code by useless comments
}
