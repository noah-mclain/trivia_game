
package triviagamee;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginMenuController {
    String check="amputate";

    @FXML TextField userInputText;
    @FXML TextField passwordInputText;
    @FXML Label labelVerdict;
    @FXML ImageView bgView;


    public void login(ActionEvent e){
            if(DatabaseConnection.checkCredentials(userInputText.getText(),passwordInputText.getText())){
            labelVerdict.setText("Login successful ╰(▔∀▔)╯");
            labelVerdict.setTextFill(Color.GREEN);
        }
        else{
            labelVerdict.setText("wrong user-name ヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void signup(ActionEvent e){
       boolean registerExceptionCheck =DatabaseConnection.registerNewUser(userInputText.getText(),passwordInputText.getText());
        if(registerExceptionCheck){
            labelVerdict.setText("Sign-in successful HAAIII °˖✧◝(⁰▿⁰)◜✧˖°");
            labelVerdict.setTextFill(Color.GREEN);
        }
        else{
            labelVerdict.setText("Sign-in unsuccessfulヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void singlePlayer(ActionEvent e){

    }

    public void multiPlayer(ActionEvent e){

    }




//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }


 }
