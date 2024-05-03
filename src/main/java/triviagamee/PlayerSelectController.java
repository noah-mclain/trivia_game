package triviagamee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class PlayerSelectController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchQuestionPage(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayerScreen.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
