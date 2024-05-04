package triviagamee;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;

public class DatabaseTimer {
    private static final String DB_URL = "jdbc:mysql://trivia-game2.clsy2siwoygw.eu-north-1.rds.amazonaws.com:3306/trivia?user=admin&password=yahood123";
    private static final String USER = "username";
    private static final String PASS = "password";

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                queryDatabase();
            }
        }, 0, 1000); // Run the task every 1 second
    }

    private static void queryDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM your_table")) {

            while (rs.next()) {
                // Process the result set
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
