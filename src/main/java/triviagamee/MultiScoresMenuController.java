package triviagamee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MultiScoresMenuController {
    @FXML
    private TableView<Map<String, Object>> leaderBoard;
    @FXML
    private TableColumn<Map<String, Object>, String> nameCol;
    @FXML
    private TableColumn<Map<String, Object>, String> scoreCol;
    private String roomName;

    private Stage stage;
    private Scene scene;

    public MultiScoresMenuController(Room room) {
        this.roomName = room.getRoomName();
    }

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new Callback<CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Map<String, Object>, String> p) {
                return new SimpleStringProperty((String) p.getValue().get("name"));
            }
        });

        scoreCol.setCellValueFactory(new Callback<CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Map<String, Object>, String> p) {
                return new SimpleStringProperty(String.valueOf(p.getValue().get("score")));
            }
        });

//        leaderBoard.getColumns().add(nameCol);
//        leaderBoard.getColumns().add(scoreCol);

        ArrayList<Map<String, Object>> playersData = DatabaseConnection.retrieveScores(this.roomName);

        ObservableList<Map<String, Object>> players = FXCollections.observableArrayList(playersData);
        leaderBoard.setItems(players);

//        ObservableList<Player> players = FXCollections.observableArrayList();
//        for (Map<String, Object> playerData : playersData) {
//            String name = (String) playerData.get("name");
//            int score = (int) playerData.get("score");
//            players.add(new Player(name, "", this.roomName, score));
//        }
//        leaderBoard.setItems(players);
    }

    public void switchToGenresAgain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HostChooseCategory.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepageAgain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
