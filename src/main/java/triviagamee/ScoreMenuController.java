package triviagamee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreMenuController implements Initializable {
    @FXML Label scoreLabelScoreMenu;

    private Stage stage;
    private Scene scene;


    public void switchToGenresAgain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GenreSelectScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepageAgain(ActionEvent e)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
           scoreLabelScoreMenu.setText("You got "+QuestionController.score+" /10\n");
           if(QuestionController.score<5){
               buttonAudio("hehehe");
               scoreLabelScoreMenu.setText(scoreLabelScoreMenu.getText()+"you are as pathetic as ever =͟͟͞͞( •̀д•́))) ");
               scoreLabelScoreMenu.setTextFill(Color.RED);
           }
           else{
               buttonAudio("WOOO");
               scoreLabelScoreMenu.setText(scoreLabelScoreMenu.getText()+"you are amazingly amazing ★~(◠‿◕✿) ");
               scoreLabelScoreMenu.setTextFill(Color.GREEN);
           }

    }

    public void buttonAudio(String audioName){
        AudioClip click= new AudioClip(getClass().getResource("/audios/"+audioName+".mp3").toExternalForm());
        click.play();
    }

}
