package triviagamee;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class QuestionController implements Initializable {
    @FXML
    Label countdownLabel;
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
    private Timeline countdownTimer;
    private int countdownSeconds = 11;
    private boolean timeup = false;
    ArrayList<Button> buttonsArray = new ArrayList<Button>();
    private Button chosen;
    Question question;
    static int score = 0;
    String scoreText = "Score: ";
    int questionCount = 10;
    static int streak = 0;
    int randomFlagIndex;
    ArrayList<String> flags;
    String rightAnswer;

    public void displayQuestion() {
        countdownSeconds = 11;
        if (!GenreSelectScreenController.notMisc) {
            question = DatabaseConnection.retrieveQuestion();
        } else {
            if (GenreSelectScreenController.genreName.equals("Flags")) {
                displayFlags();
                return;
            } else {
                question = DatabaseConnection.retrieveQuestion(GenreSelectScreenController.genreName);
            }
        }
        questionLabel.setText(question.getQuestion());
        ArrayList<String> choices = new ArrayList<>();
        //ArrayList<String> notRightAnswer = new ArrayList<>();
        choices.add(question.getRightAnswer());
        choices.add(question.getChoiceB());
        choices.add(question.getChoiceC());
        choices.add(question.getChoiceD());
//        notRightAnswer.add(question.getChoiceB());
//        notRightAnswer.add(question.getChoiceC());
//        notRightAnswer.add(question.getChoiceD());
        Collections.shuffle(choices);
        buttonA.setText(choices.get(0));
        buttonB.setText(choices.get(1));
        buttonC.setText(choices.get(2));
        buttonD.setText(choices.get(3));
        Collections.addAll(buttonsArray, buttonA, buttonB, buttonC, buttonD);

        for (Button button : buttonsArray) {
            button.setOpacity(1.0f);
            button.setStyle("-fx-text-fill: rgb(234,0,255);");
            if(!button.getText().equals(rightAnswer)){
                chosen = button;
            }
        }
    }

    public void userChoice(ActionEvent e) {
        Button buttonCheck = (Button) e.getSource();

        Glow glow = new Glow();
        glow.setLevel(1.0);
        if (GenreSelectScreenController.genreName != null && GenreSelectScreenController.genreName.equals("Flags")) {
            rightAnswer = flags.get(randomFlagIndex - 1);
        } else {
            rightAnswer = question.getRightAnswer();
        }

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
        }
        else if(timeup){
            buttonAudio("error");
            streak = 0;
            answerVerdict.setText("Too slow! ༽◺_◿༼ ");
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

        else {
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

    public void buttonAudio(String audioName) {
        AudioClip click = new AudioClip(getClass().getResource("/audios/" + audioName + ".mp3").toExternalForm());
        click.play();
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

    public void nextClicked(ActionEvent e) {
        buttonAudio("mouseclick");
        displayQuestion();
        for (Button button : buttonsArray) {
            button.setDisable(false);
        }
        answerVerdict.setText("");
        timeup = false;
        nextButton.setVisible(false);
        //countdownSeconds = 11;
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
        initializeCountdownTimer();
    }

    private void initializeCountdownTimer(){
        countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdownSeconds--;
            countdownLabel.setText("Time left: " + countdownSeconds + " seconds");
            if (countdownSeconds == 0) {
                handleTimeUp();
            }
            else if(countdownSeconds <0){
                countdownLabel.setText("");
            }
        }));
        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    private void handleTimeUp() {
        timeup = true;
        chosen.fire();
        userChoice(new ActionEvent());
        countdownLabel.setText("");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
           nextButton.fire();
        });
    }


    public void switchScoreMenu(ActionEvent e) throws IOException {
        Stage stage;
        Scene scene;

        Parent root = FXMLLoader.load(getClass().getResource("ScoreMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayFlags() {
        questionLabel.setDisable(true);
        flagImageView.setVisible(true);
        Collections.addAll(buttonsArray, buttonA, buttonB, buttonC, buttonD);

        randomFlagIndex = (int) (Math.random() * 13) + 1;
        flags = fillFlags();
        String answer = flags.get(randomFlagIndex - 1);
        String flagImagePath = String.format("/Flags/%d.png", randomFlagIndex);

        // Load flag image using ClassLoader.getResource()
        Image image = new Image(getClass().getResource(flagImagePath).toExternalForm());
        // Image image= new
        // Image("file:\\java2\\src\\main\\resources\\Flags\\"+randomFlagIndex+".png");
        flagImageView.setImage(image);
        ArrayList<String> countryNames = new ArrayList<>();
        countryNames.add(flags.get(randomFlagIndex - 1));
        int[] occArray = new int[flags.size()];
        occArray[randomFlagIndex - 1] = 1;
        for (int i = 1; i < 4; i++) {
            int randomizer = (int) (Math.random() * 13) + 1;
            if (occArray[randomizer - 1] == 1) {
                i--;
                continue;
            }
            occArray[randomizer - 1] = 1;
            countryNames.add(flags.get(randomizer - 1));
        }
        Collections.shuffle(countryNames);
        for (int i = 0; i < 4; i++) {
            buttonsArray.get(i).setText(countryNames.get(i));
        }

        for (Button button : buttonsArray) {
            button.setOpacity(1.0f);
            button.setStyle("-fx-text-fill: rgb(234,0,255);");
        }

    }

    public static ArrayList<String> fillFlags() {
        ArrayList<String> flags = new ArrayList<>();
        flags.add("Belgium");
        flags.add("Lebanon");
        flags.add("Albania");
        flags.add("Italy");
        flags.add("Azerbaijan");
        flags.add("Iceland");
        flags.add("Norway");
        flags.add("Mozambique");
        flags.add("Palestine");
        flags.add("Tunisia");
        flags.add("Peru");
        flags.add("Brazil");
        flags.add("Mexico");
        return flags;

    }

}
