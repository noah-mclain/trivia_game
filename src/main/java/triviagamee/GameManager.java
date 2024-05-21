package triviagamee;

import java.sql.Array;
import java.util.ArrayList;

public class GameManager {
    protected ArrayList<Player> players;
    protected Room room;
    protected ArrayList<Question> questions;
    protected String genre;
    protected StringBuilder questionString;
    protected int counter;


    public GameManager(Room room) {
        this.room = room;
    }

    public GameManager(Player host, Room room) {
        this.players = new ArrayList<>();
        this.questions = new ArrayList<>();
        players.add(host);
        this.room = room;
        this.genre = "misc";
        this.counter = 0;
        questionString = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            Question question = DatabaseConnection.retrieveQuestion();
            questions.add(question);
            questionString.append(question.getID());
            if (i != 9)
                questionString.append(',');
        }
    }

    public GameManager(Player host, Room room, String genre) {
        this.players = new ArrayList<>();
        this.questions = new ArrayList<>();
        players.add(host);
        this.room = room;
        this.genre = genre;
        questionString = new StringBuilder();
        this.counter = 0;
        for (int i = 0; i < 10; i++) {
            Question question = DatabaseConnection.retrieveQuestion(genre);
            questions.add(question);
            questionString.append(question.getID());
            if (i != 9)
                questionString.append(',');
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

    public String getHost() {
        return this.players.get(0).getName();
    }

    public String getGenre() {
        return this.genre;
    }

    public String getQuestionString() {
        return this.questionString.toString();
    }

    public void storeRoom() {
        DatabaseConnection.initializeRoom(this.getRoom().getRoomName(), this.getHost(), this.getQuestionString(),
                this.getGenre(), this.room.getRoomStatus());
    }

    public Question retrieveCurrentQuestion() {
        Question question = DatabaseConnection.retrieveQuestion((questions.get(counter).getID()));
        counter++;
        return question;
    }

}
