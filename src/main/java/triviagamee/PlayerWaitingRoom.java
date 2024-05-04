package triviagamee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PlayerWaitingRoom {
    public Label waitingLabel;
    public TextArea userUpdate;
    public Label editableLabel;


    public void chat(){
        userUpdate.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                editableLabel.setText("You are able to chat");
            } else {
                editableLabel.setText("Typing...");
            }
        });
    }
}
