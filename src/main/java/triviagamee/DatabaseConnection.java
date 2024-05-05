package triviagamee;

import java.sql.*;
import java.util.ArrayList;
import java.lang.Math.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://trivia-game2.clsy2siwoygw.eu-north-1.rds.amazonaws.com:3306/trivia?user=admin&password=yahood123";


    private static int counter=1;
    public static Connection connect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // needs to be edited
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkCredentials(String username, String password) {
        if(username.equals("admin") && password.equals("yahood123")) storeQuestions();
        try (Connection connection = connect()) {
            String query = "SELECT * FROM logins";
            Statement statement = connection.createStatement();
            ResultSet answer = statement.executeQuery(query);
            while (answer.next()) {
                String userName= answer.getString("username");
                String passWord=answer.getString("userPassword");
                if ( userName.equals(username) &&passWord.equals(password)) {
                    connection.close();
                    return true;
                }
            }
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static boolean registerNewUser(String username, String password){
        if(checkCredentials(username,password)) return false;
        try(Connection connection = connect()){
            String query="INSERT INTO logins(username, userPassword) VALUES (?, ?)";
            PreparedStatement statement =connection.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.executeUpdate();
            counter++;
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static Question retrieveQuestion(){
        try(Connection connection = connect()){
            String query="SELECT COUNT(*) FROM questions";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                int range = result.getInt(1);
                int rand = (int)(Math.random()*range)+1;
                query= "SELECT * FROM questions WHERE ID = ";
                String id= String.valueOf(rand);
                query+=id;
                Statement retrieveStatement = connection.createStatement();
                ResultSet resultSet = retrieveStatement.executeQuery(query);
                while(resultSet.next()){
                    int counter= resultSet.getInt("ID");
                    String question = resultSet.getString("question");
                    String rightAnswer=resultSet.getString("rightAnswer");
                    String choiceA=resultSet.getString("answer2");
                    String choiceB=resultSet.getString("answer3");
                    String choiceC=resultSet.getString("answer4");
                    String category=resultSet.getString("category");
                    return new Question(counter, question,rightAnswer,choiceA,choiceB,choiceC,category);
                }
            }
            else{
                System.out.println("Result not found");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public static Question retrieveQuestion(String genre){
        try(Connection connection= connect()){
            String query="SELECT * FROM questions WHERE category = ? ORDER BY RAND() LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, genre);
                ResultSet resultSet= preparedStatement.executeQuery();
                if(resultSet.next()){
                    int id= resultSet.getInt("ID");
                    String question= resultSet.getString("question");
                    String rightAnswer = resultSet.getString("rightAnswer");
                    String choiceA= resultSet.getString("answer2");
                    String choiceB= resultSet.getString("answer3");
                    String choiceC= resultSet.getString("answer4");
                    String category= resultSet.getString("category");
                    return new Question(id,question,rightAnswer,choiceA,choiceB,choiceC,category);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public static void storeQuestions(){
        String filepath = "questions.csv";
        ArrayList<Question> questions= QuestionReader.readQuestionsFromFile(filepath);
        try(Connection connection = connect()){
            String query ="DROP TABLE IF EXISTS questions";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            query= "CREATE TABLE questions (\n" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "question nvarchar(300),\n" +
                    "rightAnswer nvarchar(300),\n" +
                    "answer2 nvarchar(300),\n" +
                    "answer3 nvarchar(300),\n" +
                    "answer4 nvarchar(300),\n" +
                    "category nvarchar(50)\n" +
                    ");";
            statement.executeUpdate(query);
            for(Question question : questions){
                query ="INSERT INTO questions (question, rightAnswer, answer2, answer3, answer4, category)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, question.getQuestion());
                    preparedStatement.setString(2, question.getRightAnswer());
                    preparedStatement.setString(3, question.getChoiceB());
                    preparedStatement.setString(4, question.getChoiceC());
                    preparedStatement.setString(5, question.getChoiceD());
                    preparedStatement.setString(6, question.getCategory());
                    preparedStatement.executeUpdate();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void storePlayer(Player player){
        try(Connection connection = connect()){
            String query="INSERT INTO players (user, role, room, score) VALUES (?, ?,?,?); ";
            try(PreparedStatement preparedStatement =connection.prepareStatement(query)){
                preparedStatement.setString(1,player.getName());
                preparedStatement.setString(2, player.getRole());
                preparedStatement.setString(3, player.getRoom());
                preparedStatement.setInt(4,0);
                preparedStatement.executeUpdate();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void updatePlayerScore( String name, int score){
        try(Connection connection = connect()){
            String query = "UPDATE players\n" +
                    "SET score = ?\n" +
                    "WHERE name = ?;";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                preparedStatement.setInt(1,score);
                preparedStatement.setString(2,name);
                preparedStatement.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void removePlayer(Player player){
        try(Connection connection = connect()){
            String query="DELETE FROM players WHERE name =?";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                preparedStatement.setString(1,player.getName());
                preparedStatement.executeUpdate();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void storeRoom(Room room){
        try(Connection connection = connect()) {
            String query = "INSERT INTO rooms (name, host, genre, status) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1,room.getRoomName());
                preparedStatement.setString(2, room.getHostName());
                preparedStatement.setString(3,room.getGenre());
                preparedStatement.setString(4,room.getRoomStatus());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void changeRoomGenre(Room room){
        try(Connection connection = connect()){
            String query="UPDATE rooms SET genre = ? WHERE name = ?";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                preparedStatement.setString(1,room.getGenre());
                preparedStatement.setString(2,room.getGenre());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void storeRoomQuestions(Room room, ArrayList<Question> questions){
        try(Connection connection = connect()){
            for(int i=0;i<10;i++){
                String query="UPDATE rooms SET q"+(i+1)+ "= ? WHERE name = ?";
                try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                    preparedStatement.setInt(1,questions.get(i).getID());
                    preparedStatement.setString(2,room.getRoomName());
                    preparedStatement.executeUpdate();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
