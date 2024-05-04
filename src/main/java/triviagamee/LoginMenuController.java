
package triviagamee;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginMenuController {
    String check="amputate";

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField userInputText;
    @FXML TextField passwordInputText;
    @FXML Label labelVerdict;
    @FXML ImageView bgView;
    @FXML Button nextButton;

    public void setVisibilityFalse(){
        nextButton.setVisible(false);
    }

    public void login(ActionEvent e) throws IOException {

            if(DatabaseConnection.checkCredentials(userInputText.getText(),passwordInputText.getText())){
                labelVerdict.setText("Login successful ╰(▔∀▔)╯");
                labelVerdict.setTextFill(Color.GREEN);
//                nextButton.setVisible(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                    try {
                        switchToPlayerSelect(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                delay.play();
        }
        else{
            labelVerdict.setText("wrong user-name ヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void signup(ActionEvent e) throws IOException{
        labelVerdict.setText("");
        isCorrectUsernameFormat(userInputText.getText());
        isCorrectPasswordFormat(passwordInputText.getText());

        if(!labelVerdict.getText().isEmpty()) return;

       boolean registerExceptionCheck =DatabaseConnection.registerNewUser(userInputText.getText(),passwordInputText.getText());
        if(registerExceptionCheck){
            labelVerdict.setText("Sign-up successful HAAIII °˖✧◝(⁰▿⁰)◜✧˖°");
            labelVerdict.setTextFill(Color.GREEN);
//            nextButton.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                try {
                    switchToPlayerSelect(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            delay.play();
        }
        else{
            labelVerdict.setText("Sign-up unsuccessfulヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void isCorrectUsernameFormat(String enteredUsername){
        if(enteredUsername.length()<4){
            labelVerdict.setText("Username should be at least 4 characters long! (┛◉Д◉)┛彡┻━┻ ");
        }

    }

    public  void isCorrectPasswordFormat(String enteredPassword){
        if(!(enteredPassword.matches(".*\\d+.*") )){
            labelVerdict.setText("password must contain at least one number!\n");
        }
        if(!(enteredPassword.matches(".*[a-z]+.*"))){
            labelVerdict.setText(labelVerdict.getText()+" Password should contain at least 1 lowercase letter! ");
        }
        if(!(enteredPassword.matches(".*[A-Z]+.*"))){
            labelVerdict.setText(labelVerdict.getText()+" Password should contain at least one uppercase letter!\n");
        }
        if(enteredPassword.length()<8){
            labelVerdict.setText(labelVerdict.getText()+" Password should be at least 8 characters long!");
        }
    }

    public void initialize() {
        userInputText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                passwordInputText.requestFocus();
            }
        });
//tried here to make an event handler for when the use presses the enter button, that the login is automatically performed, tis glitching bs hazabatha

//        passwordInputText.setOnKeyPressed(event -> {
//            if(event.getCode() == KeyCode.ENTER) {
//                try {
//                        login(new ActionEvent());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                }
//           }
//       });
    }


    
//    public void switchToLogin(ActionEvent e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }

    public void switchToPlayerSelect(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playersSelect_menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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
