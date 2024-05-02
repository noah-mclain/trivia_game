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
        System.out.println(username);
        System.out.println(password);
        try (Connection connection = connect()) {
            String query = "SELECT * FROM logins";
            Statement statement = connection.createStatement();
            ResultSet answer = statement.executeQuery(query);
            while (answer.next()) {
                String userName= answer.getString("username");
                String passWord=answer.getString("userPassword");
                System.out.println(userName.getClass());
                System.out.println(passWord.getClass());
                System.out.println("Retrieved username: " + userName);
                System.out.println("Retrieved password: " + passWord);
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
//    public static String retrieveQuestion(){
//        try(Connection connection = connect()){
//            String query="SELECT COUNT(*) FROM Questions";
//            Statement statement = connection.createStatement();
//            ResultSet result=statement.executeQuery(query);
//            int range = result.getInt(1);
//            int pid= (int)Math.random()+1*range;
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }
//    }

    
}
