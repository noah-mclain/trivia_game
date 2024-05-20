package triviagamee;

import javafx.scene.chart.PieChart;

public class Player {
    private String name;
    private String role;
    private String room;
    private int score;
    public Player(String name, String role, String room){
       this.name=name;
       this.role=role;
       this.room = room;
       this.score=0;
       //DatabaseConnection.storePlayer(this);
    }
    public String getName(){
        return this.name;
    }

    public String getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }
    public String getRoom(){
        return room;
    }
    public void addScore(){
        this.score++;
        //DatabaseConnection.updatePlayerScore(name, score);
    }
}
