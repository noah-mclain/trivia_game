package triviagamee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class GenreSelectScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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


    public void miscClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayerScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean notMisc=false;
    public static String genreName;
    public void genreClciked(ActionEvent e) throws IOException{
        notMisc=true;
        Button button= (Button)e.getSource();
        genreName=button.getText();
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayerScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
