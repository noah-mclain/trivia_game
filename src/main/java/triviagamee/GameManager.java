package triviagamee;
import java.sql.Array;
import java.util.ArrayList;

public class GameManager {
    private ArrayList<Player> players;
    private Room room;
    private ArrayList<Question> questions;
    private String genre;
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
        this.questions=new ArrayList<>();
        players.add(host);
        this.room=room;
        this.genre=genre;
        for(int i=0;i<10;i++){
            Question question = DatabaseConnection.retrieveQuestion(genre);
            questions.add(question);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public String getHost(){
        return this.players.get(0).getName();
    }
    public String getGenre(){
        return this.genre;
    }
    public void storeRoom(){
        DatabaseConnection.initializeRoom(this.getRoom().getRoomName(),this.getHost(),this.getGenre());
    }
}
