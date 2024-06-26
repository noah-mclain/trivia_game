
package triviagamee;

import java.io.File;
import java.io.IOException;

import javafx.animation.PauseTransition;
//import javafx.beans.binding.Bindings;
//import javafx.beans.binding.DoubleBinding;
//import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginMenuController {
    // String check = "amputate";

    @FXML
    private AnchorPane ap;
    @FXML
    private CubicCurve blueStroke;
    @FXML
    private Label labelVerdict;
    @FXML
    private Button loginButton;
    @FXML
    private Button nextButton;
    @FXML
    private TextField passwordInputText;
    @FXML
    private Rectangle pinkBox;
    @FXML
    private CubicCurve pinkStroke;
    @FXML
    private Rectangle purpleBox;
    @FXML
    private Button signupButton;
    @FXML
    private TextField userInputText;
    @FXML
    private Label welcomeText;

    private static String CurrentUser;

    // public void setDynamicSizes() {
    // // For each component, call setDynamicSize with the component and its ratios
    // setDynamicSize(blueStroke, 0.1, 0.1, 0.3, 0.3);
    // setDynamicSize(labelVerdict, 0.2, 0.2, 0.2, 0.2);
    // setDynamicSize(loginButton, 0.3, 0.3, 0.1, 0.1);
    // setDynamicSize(nextButton, 0.4, 0.4, 0.1, 0.1);
    // setDynamicSize(passwordInputText, 0.5, 0.5, 0.3, 0.1);
    // setDynamicSize(pinkBox, 0.6, 0.6, 0.2, 0.2);
    // setDynamicSize(pinkStroke, 0.7, 0.7, 0.2, 0.2);
    // setDynamicSize(purpleBox, 0.8, 0.8, 0.1, 0.1);
    // setDynamicSize(signupButton, 0.9, 0.9, 0.1, 0.1);
    // setDynamicSize(userInputText, 0.5, 0.5, 0.3, 0.1);
    // setDynamicSize(welcomeText, 0.5, 0.5, 0.3, 0.1);
    // }
    //
    // private void setDynamicSize(Node node, double layoutXRatio, double
    // layoutYRatio, double widthRatio, double heightRatio) {
    // node.layoutXProperty().bind(ap.widthProperty().multiply(layoutXRatio));
    // node.layoutYProperty().bind(ap.heightProperty().multiply(layoutYRatio));
    // if (node instanceof Region) {
    // ((Region)
    // node).prefWidthProperty().bind(ap.widthProperty().multiply(widthRatio));
    // ((Region)
    // node).prefHeightProperty().bind(ap.heightProperty().multiply(heightRatio));
    // } else if (node instanceof Shape) {
    // node.scaleXProperty().bind(ap.widthProperty().multiply(widthRatio));
    // node.scaleYProperty().bind(ap.heightProperty().multiply(heightRatio));
    // }
    // }

    // public void initialize() {
    // double newValue = ap.widthProperty().get() * 0.1;
    //
    // // Unbind and set new value if properties are already bound
    // unbindAndSetNewValue(purpleBox, newValue);
    // unbindAndSetNewValue(pinkBox, newValue);
    //
    // // Bind node sizes
    // bindNodeSize(blueStroke, ap);
    // bindNodeSize(labelVerdict, ap);
    // bindNodeSize(loginButton, ap);
    // bindNodeSize(nextButton, ap);
    // bindNodeSize(passwordInputText, ap);
    // bindNodeSize(pinkBox, ap);
    // bindNodeSize(pinkStroke, ap);
    // bindNodeSize(purpleBox, ap);
    // bindNodeSize(signupButton, ap);
    // bindNodeSize(userInputText, ap);
    // bindNodeSize(welcomeText, ap);
    // }
    //
    // private void unbindAndSetNewValue(Node node, double newValue) {
    // if (node.layoutXProperty().isBound()) {
    // node.layoutXProperty().unbind();
    // node.setLayoutX(newValue);
    // System.out.println("The layoutX property of the rectangle was bound and has
    // been set to a new value.");
    // } else {
    // System.out.println("The layoutX property of the rectangle is not bound.");
    // }
    // }
    //
    // private void bindNodeSize(Node node, AnchorPane ap) {
    // // Unbind properties if they are already bound
    // if(node.layoutXProperty().isBound()) {
    // node.layoutXProperty().unbind();
    // }
    // if(node.layoutYProperty().isBound()) {
    // node.layoutYProperty().unbind();
    // }
    //
    // // Bind properties
    // node.layoutXProperty().bind(ap.widthProperty().multiply(0.1));
    // node.layoutYProperty().bind(ap.heightProperty().multiply(0.1));
    //
    // if (node instanceof Region) {
    // Region region = (Region) node;
    //
    // // Unbind properties if they are already bound
    // if(region.prefWidthProperty().isBound()) {
    // region.prefWidthProperty().unbind();
    // }
    // if(region.prefHeightProperty().isBound()) {
    // region.prefHeightProperty().unbind();
    // }
    //
    // // Bind properties
    // region.prefWidthProperty().bind(ap.widthProperty().multiply(0.8));
    // region.prefHeightProperty().bind(ap.heightProperty().multiply(0.8));
    // } else if (node instanceof Shape) {
    // Shape shape = (Shape) node;
    //
    // // Unbind properties if they are already bound
    // if(shape.scaleXProperty().isBound()) {
    // shape.scaleXProperty().unbind();
    // }
    // if(shape.scaleYProperty().isBound()) {
    // shape.scaleYProperty().unbind();
    // }
    //
    // // Bind properties
    // shape.scaleXProperty().bind(ap.widthProperty().multiply(0.8));
    // shape.scaleYProperty().bind(ap.heightProperty().multiply(0.8));
    // }
    // }

    public void setVisibilityFalse() {
        nextButton.setVisible(false);
    }

    public void login(ActionEvent e) throws IOException {
        buttonAudio("loginclick");
        if (DatabaseConnection.checkCredentials(userInputText.getText(), passwordInputText.getText())) {
            labelVerdict.setText("Login successful ╰(▔∀▔)╯");
            labelVerdict.setTextFill(Color.GREEN);
            // nextButton.setVisible(true);
            CurrentUser = userInputText.getText();
            PauseTransition delay = new PauseTransition(Duration.seconds(1.2));
            delay.setOnFinished(event -> {
                try {
                    switchToPlayerSelect(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            delay.play();
        } else {

            if (DatabaseConnection.checkCredentials(userInputText.getText(), passwordInputText.getText())) {
                labelVerdict.setText("Login successful ╰(▔∀▔)╯");
                labelVerdict.setTextFill(Color.GREEN);
                // nextButton.setVisible(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
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
    }

    public static String getCurrentUser() {
        return CurrentUser;
    }

    public void signup(ActionEvent e) throws IOException {
        buttonAudio("loginclick");
        labelVerdict.setText("");
        isCorrectUsernameFormat(userInputText.getText());
        isCorrectPasswordFormat(passwordInputText.getText());

        if (!labelVerdict.getText().isEmpty())
            return;

        boolean registerExceptionCheck = DatabaseConnection.registerNewUser(userInputText.getText(),
                passwordInputText.getText());
        if (registerExceptionCheck) {
            labelVerdict.setText("Sign-up successful HAAIII °˖✧◝(⁰▿⁰)◜✧˖°");
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
            labelVerdict.setText("Sign-up unsuccessfulヾ( ･`⌓´･)ﾉﾞ ");
            labelVerdict.setTextFill(Color.RED);
        }
    }

    public void isCorrectUsernameFormat(String enteredUsername) {
        if (enteredUsername.length() < 4) {
            labelVerdict.setText("Username should be at least 4 characters long! (┛◉Д◉)┛彡┻━┻ ");
        }

    }

    public void isCorrectPasswordFormat(String enteredPassword) {
        if (!(enteredPassword.matches(".*\\d+.*"))) {
            labelVerdict.setText("password must contain at least one number!\n");
        }
        if (!(enteredPassword.matches(".*[a-z]+.*"))) {
            labelVerdict.setText(labelVerdict.getText() + " Password should contain at least 1 lowercase letter! ");
        }
        if (!(enteredPassword.matches(".*[A-Z]+.*"))) {
            labelVerdict.setText(labelVerdict.getText() + " Password should contain at least one uppercase letter!\n");
        }
        if (enteredPassword.length() < 8) {
            labelVerdict.setText(labelVerdict.getText() + " Password should be at least 8 characters long!");
        }
    }

    public void initialize() {
        buttonAudio("cutemoosic");
        userInputText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                passwordInputText.requestFocus();
            }
        });

    }

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

    public void moveToLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButton.fire();
        }
    }

    public void buttonAudio(String audioName) {
        AudioClip click = new AudioClip(getClass().getResource("/audios/" + audioName + ".mp3").toExternalForm());
        click.play();
    }

}
