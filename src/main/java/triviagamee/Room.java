package triviagamee;

public class Room {
    private String roomName;
    private String hostName;
    private String genre;
    private String roomStatus;
    public Room(String name,String host,String genre){
        this.roomName=name;
        this.hostName=host;
        this.genre = genre;
        this.roomStatus="pending";
    }
    public String getRoomName() {
        return roomName;
    }
    public String getHostName() {
        return hostName;
    }
    public String getRoomStatus(){
        return this.roomStatus;
    }
    public void setActive(){
        this.roomStatus="Active";
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public String getGenre(){
        return this.genre;
    }
}
