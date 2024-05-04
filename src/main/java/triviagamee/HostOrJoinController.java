package triviagamee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HostOrJoinController {

    public TextField gameNamePlayerTextField;
    @FXML
    private Button joinButton;
    @FXML
    private Button hostButton;
    @FXML
    private TextField gameNameHostTextField;




    public void initialize() {
        joinButton.setVisible(true);
        hostButton.setVisible(true);
        gameNameHostTextField.setVisible(false);
        gameNamePlayerTextField.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    switchWaitingRoom(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } );

        gameNameHostTextField.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    ChooseCategory(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } );
    }

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
        double removedhost = hostButton.getLayoutY();
        hostButton.setVisible(false);
        gameNamePlayerTextField.setLayoutY(removedhost);
        gameNamePlayerTextField.setVisible(true);

    }

    public void hostGameClicked() {
        double newhost = joinButton.getLayoutY();
        joinButton.setVisible(false);
        hostButton.setLayoutY(newhost);
        gameNameHostTextField.setVisible(true);
    }
}
