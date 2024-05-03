package triviagamee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML Label questionLabel;
    @FXML Label answerVerdict;
    @FXML Button buttonA;
    @FXML Button buttonB;
    @FXML Button buttonC;
    @FXML Button buttonD;
    Question question;
    static int score =0;
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

    }
    public void choiceA() throws InterruptedException {
        if(buttonA.getText().equals(question.getRightAnswer())){
            score++;
            buttonA.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀) ,Score: "+score);
            answerVerdict.setTextFill(Color.GREEN);
        }
        else{
            buttonA.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
//        Thread.sleep(4000);
//        displayQuestion();
    }
    public void choiceB() throws InterruptedException {
        if(buttonB.getText().equals(question.getRightAnswer())){
            score++;
            buttonB.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀), Score: "+ score);
            answerVerdict.setTextFill(Color.GREEN);
        }
        else{
            buttonB.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
//        Thread.sleep(4000);
//        displayQuestion();
    }
    public void choiceC() throws InterruptedException {
        if(buttonC.getText().equals(question.getRightAnswer())) {
            score++;
            buttonC.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            answerVerdict.setText("Amazing! (⁀ᗢ⁀), Score: "+ score);
            answerVerdict.setTextFill(Color.GREEN);
        }
        else{
            buttonC.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
//        Thread.sleep(4000);
//        displayQuestion();
    }
    public void choiceD() throws InterruptedException {
        if(buttonD.getText().equals(question.getRightAnswer())){
            buttonD.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: green;");
            score++;
            answerVerdict.setText("Amazing! (⁀ᗢ⁀), Score: "+ score);
            answerVerdict.setTextFill(Color.GREEN);

        }
        else{
            buttonD.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: red;");
            answerVerdict.setText("Pathetic! ༽◺_◿༼ ");
            answerVerdict.setTextFill(Color.RED);
        }
//        Thread.sleep(4000);
//        displayQuestion();
    }
    public void initialize(URL location, ResourceBundle resources) {
        displayQuestion();
    }
}
