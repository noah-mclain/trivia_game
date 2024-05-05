package triviagamee;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostWaitingRoom {


    public TextArea userUpdate;
    public TextField typing;
    public Label editableLabel;
    private Stage stage;
    public Scene scene;
    @FXML
    public AnchorPane parentPane;
    public ImageView loadingGIF;
    public Button startButton;
    private String currentUser;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                scene = parentPane.getScene();
                setupEventHandlers();
            }
        });
        currentUser= LoginMenuController.getCurrentUser();
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
                userUpdate.appendText(currentUser + " ♕: " + message + "\n");
                typing.clear();
                editableLabel.setText("");
            }
        }
    }


    public void hostStartGame(ActionEvent e) throws IOException{
        miscOrGenre();
        Parent root = FXMLLoader.load(getClass().getResource("multiplayerGame.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //idk how to continue from here bc i didnt manage the questions
    public void miscOrGenre(){
        if(HostChooseCategory.isMisc){
            DatabaseConnection.retrieveQuestion();
        }
        else{
            Button genre = HostChooseCategory.buttonClicked;
            DatabaseConnection.retrieveQuestion(genre.getText());
        }
    }

}
