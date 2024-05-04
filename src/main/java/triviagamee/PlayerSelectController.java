package triviagamee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class PlayerSelectController {
    @FXML
    public Button singlePlayer;
    @FXML
    public Button multiPlayer;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
//     public void switchQuestionPage(ActionEvent e) throws IOException {
    public void switchToSinglePlayer(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayerScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void switchGenreSelectPage(ActionEvent e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("genreSelect_menu.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    public void switchToMultiPlayer(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MultiPlayerScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
