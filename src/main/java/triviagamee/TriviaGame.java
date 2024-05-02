package triviagamee;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import java.io.IOException;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.media.*;


public class TriviaGame extends Application {
    //kill me
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

   
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(TriviaGame.class.getResource("login_menu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene = new Scene(root);
        stage.setFullScreen(true);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
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
    // private void animateText(Text text){
    //     TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), text);
    //     translateTransition.setByY(10);

    //     translateTransition.setCycleCount(Animation.INDEFINITE);

    //     translateTransition.setAutoReverse(true);
    //     translateTransition.play();
    // }

    // private void playSound(String media){
    //     String soundEffectPath = media; 
    //     AudioClip audioClip = new AudioClip(getClass().getResource(soundEffectPath).toExternalForm());
    //     audioClip.play();
    // }

    public static void main(String[] args) {
        launch(args);
    }

}
