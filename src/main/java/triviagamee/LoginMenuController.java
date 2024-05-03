
package triviagamee;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginMenuController {
    String check = "amputate";

    @FXML
    AnchorPane ap;
    @FXML
    CubicCurve blueStroke;
    @FXML
    Label labelVerdict;
    @FXML
    Button loginButton;
    @FXML
    Button nextButton;
    @FXML
    TextField passwordInputText;
    @FXML
    Rectangle pinkBox;
    @FXML
    CubicCurve pinkStroke;
    @FXML
    Rectangle purpleBox;
    @FXML
    Button signupButton;
    @FXML
    TextField userInputText;
    @FXML
    Label welcomeText;

//    public void initialize() {
//        bindNodeSize(blueStroke, ap);
//        bindNodeSize(labelVerdict, ap);
//        bindNodeSize(loginButton, ap);
//        bindNodeSize(nextButton, ap);
//        bindNodeSize(passwordInputText, ap);
//        bindNodeSize(pinkBox, ap);
//        bindNodeSize(pinkStroke, ap);
//        bindNodeSize(purpleBox, ap);
//        bindNodeSize(signupButton, ap);
//        bindNodeSize(userInputText, ap);
//        bindNodeSize(welcomeText, ap);
//       }
//
//    private void bindNodeSize(Node node, AnchorPane ap) {
//        node.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
//            double scale = Math.min(
//                    ap.getWidth() / newValue.getWidth(),
//                    ap.getHeight() / newValue.getHeight());
//            node.setScaleX(scale);
//            node.setScaleY(scale);
//        });
//    }

    public void setVisibilityFalse() {
        nextButton.setVisible(false);
    }

    public void login(ActionEvent e) throws IOException {

        if (DatabaseConnection.checkCredentials(userInputText.getText(), passwordInputText.getText())) {
            labelVerdict.setText("Login successful ╰(▔∀▔)╯");
            labelVerdict.setTextFill(Color.GREEN);
            // nextButton.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                try {
                    switchToPlayerSelect(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            delay.play();
        } else {
            labelVerdict.setText("wrong user-name ヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void signup(ActionEvent e) throws IOException {
        boolean registerExceptionCheck = DatabaseConnection.registerNewUser(userInputText.getText(),
                passwordInputText.getText());
        if (registerExceptionCheck) {
            labelVerdict.setText("Sign-in successful HAAIII °˖✧◝(⁰▿⁰)◜✧˖°");
            labelVerdict.setTextFill(Color.GREEN);
            // nextButton.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                try {
                    switchToPlayerSelect(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            delay.play();
        } else {
            labelVerdict.setText("Sign-in unsuccessfulヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    // public void switchToLogin(ActionEvent e) throws IOException {
    // Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
    // stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    //
    // }

    public void switchToPlayerSelect(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playersSelect_menu.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void singlePlayer(ActionEvent e) {

    }

    public void multiPlayer(ActionEvent e) {

    }

    // @FXML
    // private Label welcomeText;
    //
    // @FXML
    // protected void onHelloButtonClick() {
    // welcomeText.setText("Welcome to JavaFX Application!");
    // }

}
