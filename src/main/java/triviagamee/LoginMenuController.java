
// package triviagamee;

// import java.io.IOException;
// import java.sql.SQLException;

// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Text;

// public class LoginMenuController {

//     @FXML
//     private Text triviaText;

//     @FXML
//     private TextField usernameField;

//     @FXML
//     private PasswordField passwordField;

//     @FXML
//     private TextField ipAddressField;

//     @FXML
//     private Button loginButton;

//     @FXML
//     private Button signupButton;

//     @FXML
//     private Circle connectionStatus;

//     private TriviaGame triviaGame;

//     public void setTriviaGame(TriviaGame triviaGame) {
//         this.triviaGame = triviaGame;
//     }

//     public void handleLoginClick() throws IOException {
//         String username = usernameField.getText();
//         String password = passwordField.getText();
//         String ipAddress = ipAddressField.getText();

//         // Validate user input (optional)

//         try {
//             if (triviaGame.loginUser(username, password)) {
//                 // Login successful
//                 connectionStatus.setFill(Color.GREEN);
//                 // Handle successful login (e.g., connect to server, start game logic)
//                 triviaGame.connect(ipAddress);
//             } else {
//                 // Login failed
//                 connectionStatus.setFill(Color.RED);
//                 // Show error message (optional)
//             }
//         } catch (SQLException e) {
//             // Database error handling
//             e.printStackTrace();
//             // Show error message (optional)
//         }
//     }

//     public void handleSignupClick() {
//         // Implement signup functionality (optional)
//         // This could involve opening a separate signup window
//     }
// }
