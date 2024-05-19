package triviagamee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;

public class HostOrJoinController {

    public TextField gameNamePlayerTextField;
    public Button backButton;
    @FXML
    private Button joinButton;
    @FXML
    private Button hostButton;
    @FXML
    private TextField gameNameHostTextField;
    public static String roomName;
    public static String host="koko!";
    public static boolean isHost = false;

    //enum is a list of constant values we hena ana esta5demtaha to make a set of predefined values that the button variable can take
    //i used them to define all the possible value states a function can handle

    private enum ButtonState {initial, clicked_once, clicked_twice}
    private ButtonState joinButtonState = ButtonState.initial;
    private ButtonState hostButtonState = ButtonState.initial;


    public void initialize() {
        joinButton.setVisible(true);
        hostButton.setVisible(true);
        gameNameHostTextField.setVisible(false);

        gameNamePlayerTextField.setOnKeyPressed(event-> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonAudio("mouseclick");
                if (joinButtonState == ButtonState.clicked_once) {
                    if(gameNamePlayerTextField.getText().isEmpty()){
                        buttonAudio("BOO!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Oopsie!");
                        alert.setContentText("Can't enter without a game without its name can we? ಠಿ_ಠ");
                        alert.showAndWait();
                        gameNamePlayerTextField.setText("");
                    }
                    else{
                        try {
                            buttonAudio("mouseclick");
                            switchWaitingRoom(event);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        gameNameHostTextField.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.ENTER) {
                if(hostButtonState == ButtonState.clicked_once){
                    if(gameNameHostTextField.getText().isEmpty()) {
                        buttonAudio("chipmunkhehe");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Nope sorry.");
                        alert.setContentText("Sorry mate you can't enter without a writing game name (ˉ▽￣～)\n That's a bit lazy...");
                        alert.showAndWait();
                        gameNameHostTextField.setText("");
                    }
                    else{
                        try {
                            buttonAudio("mouseclick");
                            ChooseCategory(event);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }

        });
    }

//    public void back(){
//        //lssa mazabattahash
//    }


    public void ChooseCategory(KeyEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HostChooseCategory.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchWaitingRoom(KeyEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("PlayerWaitingRoom.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void joinGameClicked() {
        switch (joinButtonState) {
            case initial:
                buttonAudio("mouseclick");
                // Initial click: Hide host button, show text field
                joinButtonState = ButtonState.clicked_once;
                double removedHost = hostButton.getLayoutY();
                hostButton.setVisible(false);
                gameNamePlayerTextField.setLayoutY(removedHost);
                gameNamePlayerTextField.setVisible(true);
                break;
            case clicked_once:
                if (gameNamePlayerTextField.getText().isEmpty()) {
                    buttonAudio("BOO!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Oopsie!");
                    alert.setContentText("Can't enter without a game without its name can we? ಠಿ_ಠ");
                    alert.showAndWait();
                    gameNamePlayerTextField.setText("");
                }
                else{
                    buttonAudio("mouseclick");
                    joinButtonState = ButtonState.clicked_twice;
                    try {
                        switchWaitingRoom();  // No KeyEvent needed here
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
        }



    }

    private void switchWaitingRoom() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("PlayerWaitingRoom.fxml"));
        Stage stage = (Stage) joinButton.getScene().getWindow(); // Assuming joinButton is always visible
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonAudio(String audioName){
        AudioClip click= new AudioClip(getClass().getResource("/audios/"+audioName+".mp3").toExternalForm());
        click.play();
    }

    public void hostGameClicked() {
        switch (hostButtonState) {
            case initial:
                buttonAudio("mouseclick");
                // Initial click: Hide join button, show text field
                hostButtonState = ButtonState.clicked_once;
                isHost = true;
                double newHost = joinButton.getLayoutY();
                joinButton.setVisible(false);
                hostButton.setLayoutY(newHost);
                gameNameHostTextField.setVisible(true);
                break;
            case clicked_once:
                // Second click: Choose category (assuming text is entered)
                if(gameNameHostTextField.getText().isEmpty()){
                    buttonAudio("chipmunkhehe");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Nope sorry.");
                    alert.setContentText("Sorry mate you can't enter without a writing game name (ˉ▽￣～)\n That's a bit lazy...");
                    alert.showAndWait();
                    gameNameHostTextField.setText("");
                    roomName=gameNameHostTextField.getText();
                }
               else{
                    buttonAudio("mouseclick");
                    hostButtonState = ButtonState.clicked_twice;
                    try {
                        ChooseCategory();  // No KeyEvent needed here
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
        }
    }


    private void ChooseCategory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HostChooseCategory.fxml"));
        Stage stage = (Stage) joinButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
