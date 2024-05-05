package triviagamee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class GenreSelectScreenController {

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
        changeButtonBackground(geoButton);
        changeButtonBackground(historyButton);
        changeButtonBackground(litButton);
        changeButtonBackground(moviesButton);
        changeButtonBackground(musicButton);
        changeButtonBackground(scienceButton);
        changeButtonBackground(showsButton);
        changeButtonBackground(miscButton);
        String css = getClass().getResource("Font.css").toExternalForm();
        if (getClass().getResource("Font.css").toExternalForm() == null) {
            System.out.println("CSS not found");
        }
        else {
            Platform.runLater(() -> {

                flagsButtons.getScene().getStylesheets().add(css);
                geoButton.getScene().getStylesheets().add(css);
                litButton.getScene().getStylesheets().add(css);
                historyButton.getScene().getStylesheets().add(css);
                moviesButton.getScene().getStylesheets().add(css);
                musicButton.getScene().getStylesheets().add(css);
                scienceButton.getScene().getStylesheets().add(css);
                showsButton.getScene().getStylesheets().add(css);
                miscButton.getScene().getStylesheets().add(css);
            });
        }
    }

    private void changeButtonBackground(Button button) {
        button.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }

    private void loadFont(String fontFileName) {
        try {
            InputStream is = GenreSelectScreenController.class.getResourceAsStream("/fonts/" + fontFileName);
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

    public void miscClicked(){
        DatabaseConnection.retrieveQuestion();
    }

    public void genreClicked(ActionEvent e){
        Button buttonClicked= (Button) e.getSource();
        DatabaseConnection.retrieveQuestion(buttonClicked.getText());

    }
}
