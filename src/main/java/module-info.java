module org.example.oop_and_javafx_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.example.oop_and_javafx_assignment to javafx.fxml;
    exports org.example.oop_and_javafx_assignment;
}