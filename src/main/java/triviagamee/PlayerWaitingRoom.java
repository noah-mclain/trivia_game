package triviagamee;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerWaitingRoom implements Initializable {
    @FXML
    public Label waitingLabel;
    @FXML
    public TextArea userUpdate;
    @FXML
    public Label editableLabel;
    @FXML
    public TextField typing;
    public String username = "toto";
    public Scene scene;
    @FXML
    public AnchorPane parentPane;
    public ImageView loadingGIF;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                scene = parentPane.getScene();
                setupEventHandlers();
            }
        });
    }

    private void setupEventHandlers() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                typing.setVisible(true);
                typing.requestFocus();
                editableLabel.setText("");
            }
        });

        final PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> editableLabel.setText(""));
        typing.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            pause.playFromStart();
            editableLabel.setText("Typing...");
        });

        scene.setOnMouseClicked(event -> {
            typing.setVisible(false);
            editableLabel.setText("Press Enter to Chat");
        });
    }


        public void addToUserUpdate (KeyEvent event){
                if (event.getCode() == KeyCode.ENTER) {
                    String message = typing.getText();
                    if (!message.isEmpty()) {
                        userUpdate.appendText(username + ": " + message + "\n");
                        typing.clear();
                        editableLabel.setText("");
                    }
                }
            }
        }



