package triviagamee;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class MultiPlayerQuesScreenController implements Initializable {
    private GameManager gameManager;

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
    @FXML
    ImageView flagImageView;
    @FXML
    private MediaView timer;
    private File timerFile;
    private Media timerVid;

    ArrayList<Button> buttonsArray = new ArrayList<Button>();
    Question question;
    static int score = 0;
    String scoreText = "Score: ";
    int questionCount = 10;
    static int streak = 0;
    int randomFlagIndex;
    ArrayList<String> flags;
    String rightAnswer;
    public Scene scene;

    public MultiPlayerQuesScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public void initialize(GameManager gameManager) {
        this.gameManager = gameManager;
        timerFile = new File("10 sec timer");

//        if (!GenreSelectScreenController.notMisc) {
//            question = gameManager.retrieveCurrentQuestion();
//        } else {
////            if (GenreSelectScreenController.genreName.equals("Flags")) {
////                displayFlags();
////                return;
////            } else {
//                question = DatabaseConnection.retrieveQuestion(GenreSelectScreenController.genreName);
////            }
//        }

        question = gameManager.retrieveCurrentQuestion();

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
//        if (GenreSelectScreenController.genreName != null && GenreSelectScreenController.genreName.equals("Flags")) {
//            rightAnswer = flags.get(randomFlagIndex - 1);
//        } else {
            rightAnswer = question.getRightAnswer();
//        }

        if (buttonCheck.getText().equals(rightAnswer)) {
            streak += 1;
            if (streak > 4) {
                buttonAudio("levelup");
                streak = 1;
            } else
                buttonAudio("wee " + streak);
            score++;
            scoreLabel.setText(scoreText + String.valueOf(score));
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);
            for (Button button : buttonsArray) {
                if (button.getText().equals(rightAnswer)) {
                    button.setStyle("-fx-text-fill: #99FF33"); // Neon green text
                    button.setEffect(glow); // Apply the glow effect
                } else {
                    button.setStyle("-fx-text-fill: #FFB6C1"); // Light pink text for the remaining buttons
                    button.setEffect(glow); // No glow effect
                }
            }
        } else {
            buttonAudio("error");
            streak = 0;
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
            for (Button button : buttonsArray) {
                if (button.getText().equals(buttonCheck.getText())) {
                    button.setStyle("-fx-text-fill: rgb(247, 33, 25);"); // Red text
                    button.setEffect(glow); // Apply the glow effect
                } else if (button.getText().equals(rightAnswer)) {
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

    public void nextClicked(ActionEvent e) {
        questionCount--;
        System.out.println(questionCount);
        if (questionCount == 1) {
            System.out.println("combileet");
            nextButton.setText("Finish");
            try {                                  // we need to know where tog o after rounds ends
                switchScoreMenu(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        buttonAudio("mouseclick");
        initialize(gameManager);
        for (Button button : buttonsArray) {
            button.setDisable(false);
        }
        answerVerdict.setText("");
        nextButton.setVisible(false);


    }

    public void disableButtons(ActionEvent e) {
        Button buttonCheck = (Button) e.getSource();

        for (Button button : buttonsArray) {
            button.setDisable(true);
            button.setOpacity(0.5f);

            if (button.getText().equals(rightAnswer)) {
                for (Button buttons : buttonsArray) {
                    if (button.getText().equals(rightAnswer)) {
                        button.setStyle("-fx-text-fill: #66FF00");
                    } else {
                        button.setStyle("-fx-text-fill: FF000D");
                    }
                }
            } else if (button.getText().equals(buttonCheck.getText())) {
                for (Button buttons : buttonsArray) {
                    if (button.getText().equals(rightAnswer)) {
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
    public void buttonAudio(String audioName) {
        AudioClip click = new AudioClip(getClass().getResource("/audios/" + audioName + ".mp3").toExternalForm());
        click.play();
    }
    public void initialize(URL location, ResourceBundle resources) {
        initialize(gameManager);
    }

    public void switchScoreMenu(Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MultiScoresMenu.fxml"));
        loader.setController(new MultiScoresMenuController(gameManager.getRoom()));
        Parent root = loader.load();

        // Retrieve the stage from the event's source node
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
