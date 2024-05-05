package triviagamee;
import java.util.ArrayList;

public class GameManager {
    private ArrayList<Player> players;
    private Room room;
    private ArrayList<Question> questions;
    public GameManager( Player host, Room room){
        this.players=new ArrayList<>();
        players.add(host);
        this.room = room;
        for(int i=0;i<10;i++){
            Question question = DatabaseConnection.retrieveQuestion();
            questions.add(question);
        }
    }
    public GameManager(Player host, Room room, String genre){
        this.players=new ArrayList<>();
        players.add(host);
        this.room=room;
        for(int i=0;i<10;i++){
            Question question = DatabaseConnection.retrieveQuestion(genre);
            questions.add(question);
        }
    }
}
