package triviagamee;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class GenreSelectMenuController {

    @FXML
    private Button flagsButtons;
    @FXML
    private Button geoButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button litButton;
    @FXML
    private Button moviesButton;
    @FXML
    private Button musicButton;
    @FXML
    private Button scienceButton;
    @FXML
    private Button showsButton;
    @FXML
    private Button miscButton;

    @FXML
    public void initialize() {
        loadFont("Beon-Regular.ttf");
        changeButtonBackground(flagsButtons);
        flagsButtons.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(geoButton);
        geoButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(historyButton);
        historyButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(litButton);
        litButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(moviesButton);
        moviesButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(musicButton);
        musicButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(scienceButton);
        scienceButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(showsButton);
        showsButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());
        changeButtonBackground(miscButton);
        miscButton.getScene().getStylesheets().add(getClass().getResource("triviagamee/Fonts.css").toExternalForm());

    }

    private void changeButtonBackground(Button button) {
        button.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }

    private void loadFont(String fontFileName) {
        try {
            InputStream is = GenreSelectMenuController.class.getResourceAsStream("/fonts/" + fontFileName);
            Font font = Font.loadFont(is, 36);
            if (font != null) {
                System.out.println("Font " + font.getName() + " loaded successfully.");
            } else {
                System.out.println("Failed to load font " + fontFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
