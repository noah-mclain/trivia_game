package triviagamee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.Glow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML
    Label questionLabel;
    @FXML
    Label answerVerdict;
    @FXML
    Label scoreLabel;
    @FXML
    Button buttonA;
    @FXML
    Button buttonB;
    @FXML
    Button buttonC;
    @FXML
    Button buttonD;
    @FXML
    Button nextButton;

    ArrayList<Button> buttonsArray = new ArrayList<Button>();
    Question question;
    static int score = 0;
    String scoreText = "Score: ";
    int questionCount = 10;

    public void displayQuestion() {
        if (!GenreSelectScreenController.notMisc) {
            question = DatabaseConnection.retrieveQuestion();
        } else {
            question = DatabaseConnection.retrieveQuestion(GenreSelectScreenController.genreName);
        }
        questionLabel.setText(question.getQuestion());
        ArrayList<String> choices = new ArrayList<>();
        choices.add(question.getRightAnswer());
        choices.add(question.getChoiceB());
        choices.add(question.getChoiceC());
        choices.add(question.getChoiceD());
        Collections.shuffle(choices);
        buttonA.setText(choices.get(0));
        buttonB.setText(choices.get(1));
        buttonC.setText(choices.get(2));
        buttonD.setText(choices.get(3));
        Collections.addAll(buttonsArray, buttonA, buttonB, buttonC, buttonD);

        for (Button button : buttonsArray) {
            button.setOpacity(1.0f);
            button.setStyle("-fx-text-fill: rgb(234,0,255);");
        }

    }

    public void userChoice(ActionEvent e) {
        Button buttonCheck = (Button) e.getSource();

        Glow glow = new Glow();
        glow.setLevel(1.0);

        if (buttonCheck.getText().equals(question.getRightAnswer())) {
            score++;
            scoreLabel.setText(scoreText + String.valueOf(score));
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);
            for (Button button : buttonsArray) {
                if (button.getText().equals(question.getRightAnswer())) {
                    button.setStyle("-fx-text-fill: #99FF33"); // Neon green text
                    button.setEffect(glow); // Apply the glow effect
                } else {
                    button.setStyle("-fx-text-fill: #FFB6C1"); // Light pink text for the remaining buttons
                    button.setEffect(glow); // No glow effect
                }
            }
        }

        else {
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
            for (Button button : buttonsArray) {
                if (button.getText().equals(buttonCheck.getText())) {
                    button.setStyle("-fx-text-fill: rgb(247, 33, 25);"); // Red text
                    button.setEffect(glow); // Apply the glow effect
                } else if (button.getText().equals(question.getRightAnswer())) {
                    button.setStyle("-fx-text-fill: #99FF33"); // Neon green text
                    button.setEffect(glow); // Apply the glow effect
                } else {
                    button.setStyle("-fx-text-fill: #FFB6C1"); // Light pink text for the remaining buttons
                    button.setEffect(glow);
                }
            }
        }
        nextButton.setVisible(true);
        disableButtons(e);

    }

    public void disableButtons(ActionEvent e) {
        Button buttonCheck = (Button) e.getSource();

        for (Button button : buttonsArray) {
            button.setDisable(true);
            button.setOpacity(0.5f);

            if (button.getText().equals(question.getRightAnswer())) {
                for (Button buttons : buttonsArray) {
                    if (button.getText().equals(question.getRightAnswer())) {
                        button.setStyle("-fx-text-fill: #66FF00");
                    } else {
                        button.setStyle("-fx-text-fill: FF000D");
                    }
                }
            } else if (button.getText().equals(buttonCheck.getText())) {
                for (Button buttons : buttonsArray) {
                    if (button.getText().equals(question.getRightAnswer())) {
                        button.setStyle("-fx-text-fill: #66FF00");
                    } else {
                        button.setStyle("-fx-text-fill: FF000D");
                    }
                }
            } else {
                button.setStyle("-fx-text-fill: #FADADD");
            }
        }
    }

    public void nextClicked(ActionEvent e) {
        displayQuestion();
        for (Button button : buttonsArray) {
            button.setDisable(false);
        }
        answerVerdict.setText("");
        nextButton.setVisible(false);
        questionCount--;
        if (questionCount == 1) {
            nextButton.setText("Finish");
            try {
                switchScoreMenu(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
        displayQuestion();
    }

//    public void disableButtons() {
//        for (Button button : buttonsArray) {
//            button.setDisable(true);
//        }
//
//    }

    public void switchScoreMenu(ActionEvent e) throws IOException {
        Stage stage;
        Scene scene;

        Parent root = FXMLLoader.load(getClass().getResource("ScoreMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
