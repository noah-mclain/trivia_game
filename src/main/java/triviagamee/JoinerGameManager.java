package triviagamee;

public class JoinerGameManager extends GameManager {
    public JoinerGameManager(Player joiner, Room room) {
        super(joiner, room);
        this.questions.clear();
        String questionString = DatabaseConnection.retrieveQuestionString(this.getRoom().getRoomName());
        String[] questionIds = questionString.split(",");
        for (int i = 0; i < 10; i++) {
            Question question = DatabaseConnection.retrieveQuestion(Integer.valueOf(questionIds[i]));
            questions.add(question);
        }
    }
    public void storeRoom(){
        DatabaseConnection.insertPlayer(this.player.getName(), this.room.getRoomName());
    }


}
