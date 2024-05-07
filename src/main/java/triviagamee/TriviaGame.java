package triviagamee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

// import java.io.*;
// import java.net.*;
// import javafx.scene.Group;
// import javafx.scene.layout.*;
// import javafx.scene.text.Text;
// import javafx.scene.effect.*;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.scene.paint.*;
// import javafx.animation.*;
// import javafx.util.Duration;
// import javafx.scene.media.*;
// import javafx.scene.shape.*;

public class TriviaGame extends Application {
    //kill me
   
    public void start(Stage stage) throws IOException {
        //playMusic("sillygoofy");
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(TriviaGame.class.getResource("login_menu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene = new Scene(root);
//        stage.setFullScreen(true);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    // private void animateText(Text text){
    //     TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), text);
    //     translateTransition.setByY(10);

    //     translateTransition.setCycleCount(Animation.INDEFINITE);

    //     translateTransition.setAutoReverse(true);
    //     translateTransition.play();
    // }

    public void playMusic(String musicName) {
        try {
            Media buzzer = new Media(getClass().getResource("/audio/"+musicName+".mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(buzzer);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public void buttonAudio(String audioName){
        AudioClip click= new AudioClip(getClass().getResource("/audios/"+audioName+".mp3").toExternalForm());
        click.play();
    }


    // private void playSound(String media){
    //     String soundEffectPath = media; 
    //     AudioClip audioClip = new AudioClip(getClass().getResource(soundEffectPath).toExternalForm());
    //     audioClip.play();
    // }

    public static void main(String[] args) {
        launch(args);
    }

}
