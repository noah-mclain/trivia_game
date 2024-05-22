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
            connection.close();
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
                connection.close();
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
                preparedStatement.close();
                connection.close();
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
                preparedStatement.close();
                connection.close();
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
                preparedStatement.close();
                connection.close();
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
                preparedStatement.close();
                connection.close();
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
                preparedStatement.close();
                connection.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /*public static void storeRoomQuestions(Room room, ArrayList<Question> questions){
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
    }*/


    // update table to hold an arraylist
    // update function to skip p2->p10
    public static void initializeRoom(String room, String player, String questions, String genre, String status){
        try(Connection connection = connect()){
            String query = "INSERT INTO rooms (room, playerHost,questions, genre, roomStatus) VALUES (?,?,?,?,?)";
            try(PreparedStatement statement=connection.prepareStatement(query)){
                statement.setString(1, room);
                statement.setString(2,player);
                statement.setString(3,questions);
                statement.setString(4,genre);
                statement.setString(5, status);
                statement.executeUpdate();
                statement.close();
                connection.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    //i tried to implement these for the chatrooms idk how the table looks for this one
    public static void updateChatrooms(int chatId, String message, String senderUsername) throws SQLException {
        try (Connection connection = connect()) {
            // Retrieve user IDs in the chat room
            String query = "SELECT user_id FROM chatroom_users WHERE chatroom_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Integer> userIds = new ArrayList<>();
            while (resultSet.next()) {
                userIds.add(resultSet.getInt("user_id"));
            }

            // update user text fields for each user in the chat room awaw
            for (int userId : userIds) {
                updateTextfieldForUser(connection, userId, chatId, message, senderUsername);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void updateTextfieldForUser(Connection connection, int userId, int chatId, String message, String senderUsername) throws SQLException {
        // u can modify this
        String query = "UPDATE users SET text_field = CONCAT(text_field, ?, ?, ?) WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "\n[" + senderUsername + " (" + chatId + ")] " + message); // Append message with sender and chat info
        preparedStatement.setString(2, "\n"); // Optional newline separator
        preparedStatement.setInt(3, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public static Question retrieveQuestion(int ID){
        try(Connection connection = connect()){
            String query = "SELECT * FROM questions WHERE ID= ?";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                preparedStatement.setInt(1,ID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String question = resultSet.getString("question");
                    String rightAnswer = resultSet.getString("rightAnswer");
                    String choiceA = resultSet.getString("answer2");
                    String choiceB = resultSet.getString("answer3");
                    String choiceC = resultSet.getString("answer4");
                    String category = resultSet.getString("category");
                    return new Question(id,question,rightAnswer,choiceA,choiceB,choiceC,category);
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public static String retrieveQuestionString(String name){
        try(Connection connection = connect()){
            String query= "SELECT * FROM rooms WHERE room = ?";
            try(PreparedStatement preparedStatement= connection.prepareStatement(query)){
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String questionString = resultSet.getString("questions");
                    return questionString;
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    /*public static void updatePlayerScore(String name, int score){
        try(Connection connection = connect()){

        }
    }*/


    public static void retrieveScores (String roomName) {
        try (Connection connection = connect()) {
            String query = "SELECT name, score FROM players WHERE roomName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roomName);

            ResultSet resSet = preparedStatement.executeQuery();

            while (resSet.next()) {
                String playerName = resSet.getString("name");
                int score = resSet.getInt("score");
                System.out.println("Player name: " + playerName + " - Score: " + score);
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
