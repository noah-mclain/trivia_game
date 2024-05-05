package triviagamee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerWaitingRoom {
    public Label waitingLabel;
    public TextArea userUpdate;
    public Label editableLabel;
    @FXML
    Button genrePopUp;


    public void chat(){
        userUpdate.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                editableLabel.setText("You are able to chat");
            } else {
                editableLabel.setText("Typing...");
            }
        });
    }

    public void genrepopup(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GenreSelectScreen.fxml"));
        Stage popup = new Stage();
        Scene scene = new Scene(root, 600, 400);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }
}
