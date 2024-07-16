package MuslimQibla.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Surah {
    @FXML
    Text surahNumber;
    @FXML
    Text pageNumber;
    @FXML
    Text numberOfAyat;
    @FXML
    Text makanElNzool;
    @FXML
    public Button surahButton;
    public void setSurahNumber(String text)
    {
        surahNumber.setText(text);
    }
}
