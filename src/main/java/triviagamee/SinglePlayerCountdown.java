
package triviagamee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SinglePlayerCountdown {
        @FXML
        private MediaView tenSecTimer;
    //still needs to be added to the fxml
        public void setTenSecTimer(){
            String videoPath = "path.mp4";
            Media media = new Media(new File(videoPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            tenSecTimer.setMediaPlayer(mediaPlayer);

            mediaPlayer.setOnEndOfMedia(()->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Time's up loser");
                alert.setHeaderText(null);
                alert.setContentText("Too late! Looks like you lost this question >:)");
                alert.show();

                new Thread(()->{
                    try {
                        Thread.sleep(1500); // Sleep for 1.5 seconds
                        alert.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("NextWindow.fxml"));
                    Stage stage = (Stage) tenSecTimer.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            mediaPlayer.play();
        }
    }


