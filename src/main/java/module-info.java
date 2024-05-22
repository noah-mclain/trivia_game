module triviagamee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens triviagamee to javafx.fxml;
    exports triviagamee;
}
