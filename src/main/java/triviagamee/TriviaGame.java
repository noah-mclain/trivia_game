package triviagamee;

// import java.io.*;
// import java.net.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.control.*;
//import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;

import java.io.IOException;

import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.media.*;

//THIS ENTIRE GUI IS BAYEEEEEZZZZZZZZZZ BAYEZ BAYEZ

public class TriviaGame extends Application {
    private DatabaseConnection db; //kill me
    private ClientEvents clientEvents = new ClientEvents(); //(ignore this idk how to set it up yet lol)

    // In the GUI there must be a text box "Server IP" which will be used to connect
    // to server application in case they are not on same PC
    // if they are on same PC you can use "localhost" in the IP field
    // to get the IP of PC on which the server application is running , run command
    // "ipconfig" from command prompt
    // There should be also "disconnect" button to disconnect connection to server

    private static String hostIP = "localhost";
    private static int port = 23;
    private Circle connectionStatus;

    // this is apparently the main entry point for all javaFX applications, and
    // where i intialize the UI and define what
    // should be shown in the application window
    public void start(Stage primaryStage) throws IOException {
    
        //el gui da very very bayez z

        Text triviatext = new Text("TRIVIA!");
        triviatext.setEffect(new Glow(0.8));
        animateText(triviatext);

        TextField ipfield = new TextField();
        ipfield.setPromptText("Enter server IP :)");

        Button startgame = new Button("Start Game");
        startgame.setStyle("-fx-border-color: transparent;");
        //there should be a text field here where user enters the ip address 

        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("start_menu.fxml"));
        //  Scene scene = new Scene(loader.load());

        //  StartMenuController startMenuController = loader.getController();
        // // ... (Interact with the controller if needed)

        // primaryStage.setScene(scene);
        // primaryStage.show();


        startgame.setOnAction(e -> {
            playSound("audios/90s-game-ui-6-185099.mp3");
            String serverIP = ipfield.getText();
            //dk why this isnt working ive been coding for 11 hours im ded
           // connect(serverIP);
            connectionStatus.setFill(Color.GREEN);
            onStartGameClick();
        });
        connectionStatus = new Circle(10);
        connectionStatus.setFill(Color.RED);

        VBox layout = new VBox(10, triviatext, ipfield, startgame, connectionStatus);
        Scene scene = new Scene(layout, 300, 200);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onStartGameClick() {
        // hn3ml implement lel game logic hena

        //should have used this vid bs kont bamoot, we can do this for the questions lama tezhar htb2a gamda awy
        //https://www.google.com/search?sca_esv=e08c057435075756&sca_upv=1&q=animate+words+with+javafx&source=lnms&uds=AMwkrPvzpJI9rbgukgnX7GaRFEHbcpuqjX9qXD7LJ8gls6q6Hd4CUVkDQwDMCLVvOGwdj4OsuQC3HUzdTMvHQyRB8DXnbe_OyWF0Z7z3s3iERYWFPyt6SI4sQnFXSJZA2mwYCzfLknPe2LBDuNGAnnP6H16VJbg0LCUJY7CDMybCLJEwzuVhBC8Mu7GwC_mpzl0Akt-Dda2TW76mEi6UkyEtEeJ_296Qh2RaUcvMv8wIrV5H9jKnJyMaqFf-qLRoOeIuap4W6LaFmRhz405OhFlIJjuXJ-iP_KovT_M69Ra5CTsfcCT1ToZLd5Rcn2HuPrIeacVslPpd&sa=X&ved=2ahUKEwiWitH18uWFAxWBQvEDHYFwDZQQ0pQJegQIERAB&biw=1422&bih=612&dpr=1.35#fpstate=ive&vld=cid:eb0575a7,vid:d2rSQPSjqp0,st:0

    }

    public void connect() {
        // This function should be in the connect button
        ClientEvents clientEvents = new ClientEvents();
        String hostIP = "localhost";
        int port = 8000;
        Client myClient = new Client(clientEvents);
        myClient.setHostIP(hostIP);
        myClient.setPort(port);
        myClient.connect();

    }
    private void animateText(Text text){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), text);
        translateTransition.setByY(10);

        translateTransition.setCycleCount(Animation.INDEFINITE);

        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    private void playSound(String media){
        String soundEffectPath = media; 
        AudioClip audioClip = new AudioClip(getClass().getResource(soundEffectPath).toExternalForm());
        audioClip.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
