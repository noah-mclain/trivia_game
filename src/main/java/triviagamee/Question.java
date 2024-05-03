package triviagamee;

public class Question {
    private String question;
    private String rightAnswer;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    public Question(String question, String right, String A, String B, String c){
        this.question=question;
        this.rightAnswer=right;
        this.choiceB=A;
        this.choiceC=B;
        this.choiceD=c;
    }
    public String getQuestion(){
        return this.question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getChoiceC(){
        return this.choiceC;
    }
    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceD() {
        return choiceD;
    }
}