package Muslim_Qibla.Controllers;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Surah {
    @FXML
    Text surahNumber;
    @FXML
    Text pageNumber;
    @FXML
    Text numberOfAyat;
    public void setSurahNumber(String text)
    {
        surahNumber.setText(text);
    }
}
