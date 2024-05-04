package triviagamee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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


public class TriviaGame extends Application {
    //kill me
   
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(TriviaGame.class.getResource("login_menu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene = new Scene(root);
//        Scene scene = new Scene(root);
//        String css = this.getClass().getResource("/triviagamee/GlobalScene.css").toExternalForm();
//        scene.getStylesheets().add(css);
        stage.setFullScreen(true);
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

    // private void playSound(String media){
    //     String soundEffectPath = media; 
    //     AudioClip audioClip = new AudioClip(getClass().getResource(soundEffectPath).toExternalForm());
    //     audioClip.play();
    // }

    public static void main(String[] args) {
        launch(args);
    }

}
