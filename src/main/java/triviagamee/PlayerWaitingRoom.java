package triviagamee;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.animation.PauseTransition;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.net.URL;

public class PlayerWaitingRoom implements Initializable {
    @FXML
    public Label waitingLabel;
    @FXML
    public TextArea userUpdate;
    @FXML
    public Label editableLabel;
    @FXML
    public TextField typing;
    @FXML
    public AnchorPane parentPane;

    public ImageView loadingGIF;
    public Button startButton;
    private String currentUser;
    public boolean isTyping= false;


    public SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a"); // Adjust format as needed
    private String timestamp;

    public Scene scene;
    List<String> animals = Arrays.asList("Tiger", "Goat", "Cat", "Dog", "Elephant", "Lion", "Bear",
            "Giraffe", "Zebra", "Kangaroo", "Penguin", "Dolphin", "Whale", "Rabbit", "Fox", "Wolf",
            "Deer", "Horse", "Monkey", "Panda", "Leopard", "Cheetah", "Hippopotamus", "Crocodile",
            "Squirrel", "Owl", "Eagle", "Parrot", "Peacock", "Pigeon", "Jellyfish", "Armadillo",
            "Goblin shark","Cow");

    public String getTimestamp(){
        return timestamp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonAudio("cutemoosic");
        parentPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                scene = parentPane.getScene();
                setupEventHandlers();
            }
        });
        currentUser= LoginMenuController.getCurrentUser();

        if(currentUser.isEmpty()){
            currentUser= "Anonymous ";
            Random random = new Random();
            int randomIndex= random.nextInt(animals.size()+1);
            currentUser+= animals.get(randomIndex);
        }


        if(HostOrJoinController.isHost){
            startButton.setVisible(true);
            loadingGIF.setVisible(false);
            waitingLabel.setText("Players are waiting for you to start the game!");
        }
    }

    private void setupEventHandlers() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonAudio("taptap");
                typing.setVisible(true);
                typing.requestFocus();
                editableLabel.setText("");
            }
        });

        final PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            editableLabel.setText("");
            isTyping = false;
        });

        typing.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(!isTyping){
                if(HostOrJoinController.isHost) {
                    userUpdate.appendText("Game Host ♕ is typing...\n");
                    timestamp = dateFormat.format(new Date());
                }
                else userUpdate.appendText(currentUser + " is typing...\n");
                timestamp = dateFormat.format(new Date());
                isTyping = true;
            }
            pause.playFromStart();
            editableLabel.setText("You are now typing...");
        });

        scene.setOnMouseClicked(event -> {
            buttonAudio("mouseclick");
            typing.setText("");
            typing.setVisible(false);
            editableLabel.setText("Press Enter to Chat");
        });
    }

    public void buttonAudio(String audioName){
        AudioClip click= new AudioClip(getClass().getResource("/audios/"+audioName+".mp3").toExternalForm());
        click.play();
    }
        public void addToUserUpdate (KeyEvent event){
                if (event.getCode() == KeyCode.ENTER) {
                    String message = typing.getText();
                    if (!message.isEmpty()) {
                        buttonAudio("textsend");
                        if(HostOrJoinController.isHost){
                            timestamp = dateFormat.format(new Date());
                            userUpdate.appendText(currentUser + " ♕: " + message +"    "+timestamp +"\n");
                        }
                        else {
                            timestamp = dateFormat.format(new Date());
                            userUpdate.appendText(currentUser + ": " + message+"    "+timestamp + "\n");
                        }
                        typing.clear();
                        editableLabel.setText("");
                    }
                }
            }

        public void hostStartGame(ActionEvent e) throws IOException {
            buttonAudio("mouseclick");
            //mezabataha teshta8al only when the startbutton is clicked
            miscOrGenre();
            //didnt do this fxml yet
            Parent root = FXMLLoader.load(getClass().getResource("multiplayerGame.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

        //idk how to continue this bc i didnt manage the questions
        public void miscOrGenre(){
        //for some reason the miscellaneous one is the only one not working
            if(HostChooseCategory.isMisc){
                DatabaseConnection.retrieveQuestion();
            }
            else{
                Button genre = HostChooseCategory.buttonClicked;
                DatabaseConnection.retrieveQuestion(genre.getText());
            }
        }







}

