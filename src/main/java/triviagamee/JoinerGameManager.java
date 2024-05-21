package triviagamee;

public class JoinerGameManager extends GameManager {
    public JoinerGameManager(Room room) {
        super(room);
        String questionString = DatabaseConnection.retrieveQuestionString(this.getRoom().getRoomName());
        String[] questionIds = questionString.split(",");
        for (int i = 0; i < 10; i++){
            Question question = DatabaseConnection.retrieveQuestion(Integer.valueOf(questionIds[i]));
            questions.add(question);
        }
    }
}
