module triviagamee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    //this was needed to indicate that any module that requires mine will implictly require the graphics as well i think
    requires transitive javafx.graphics;

    opens triviagamee to javafx.fxml;
    exports triviagamee;
}
