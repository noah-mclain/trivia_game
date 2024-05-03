package triviagamee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML Label questionLabel;
    @FXML Label answerVerdict;
    @FXML Label scoreLabel;
    @FXML ToggleButton buttonA;
    @FXML ToggleButton buttonB;
    @FXML ToggleButton buttonC;
    @FXML ToggleButton buttonD;
    @FXML Button nextButton;
    ToggleGroup group;
    ArrayList<ToggleButton> buttonsArray=new ArrayList<ToggleButton>();

    Question question;
    static int score =0;
    String scoreText="Score: ";
    public void displayQuestion(){
        question = DatabaseConnection.retrieveQuestion();
        questionLabel.setText(question.getQuestion());
        ArrayList<String> choices= new ArrayList<>();
        choices.add(question.getRightAnswer());
        choices.add(question.getChoiceB());
        choices.add(question.getChoiceC());
        choices.add(question.getChoiceD());
        Collections.shuffle(choices);
        buttonA.setText(choices.get(0));
        buttonB.setText(choices.get(1));
        buttonC.setText(choices.get(2));
        buttonD.setText(choices.get(3));
        Collections.addAll(buttonsArray,buttonA,buttonB,buttonC,buttonD);

    }
    public void choiceA() throws InterruptedException {
        if(buttonA.getText().equals(question.getRightAnswer())){
            score++;
            scoreLabel.setText(scoreText+String.valueOf(score));
            buttonA.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);
//            buttonA.setDisable(true);
        }
        else{
            buttonA.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
        nextButton.setVisible(true);
        disableButtons();
    }
    public void choiceB() throws InterruptedException {

        if(buttonB.getText().equals(question.getRightAnswer())){
            score++;
            scoreLabel.setText(scoreText+String.valueOf(score));
            buttonB.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);
//            buttonB.setDisable(true);
        }
        else{
            buttonB.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
        nextButton.setVisible(true);
        disableButtons();

    }
    public void choiceC() throws InterruptedException {

        if(buttonC.getText().equals(question.getRightAnswer())) {
            score++;
            scoreLabel.setText(scoreText+String.valueOf(score));
            buttonC.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);
//            buttonC.setDisable(true);

        }
        else{
            buttonC.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
        nextButton.setVisible(true);
        disableButtons();

    }

    public void choiceD() throws InterruptedException {
        if(buttonD.getText().equals(question.getRightAnswer())){
            score++;
            scoreLabel.setText(scoreText+String.valueOf(score));
            buttonD.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀)");
            answerVerdict.setTextFill(Color.GREEN);

        }
        else{
            buttonD.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
        nextButton.setVisible(true);
        disableButtons();
    }

    public void nextClicked(){
        displayQuestion();
        for(ToggleButton button: buttonsArray){
            button.setDisable(false);
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
        displayQuestion();
    }

    public void disableButtons(){
        for(ToggleButton button: buttonsArray){
            button.setDisable(true);
        }

    }

}

