module org.example.naptar5_grafikus {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.naptar5_grafikus to javafx.fxml;
    exports org.example.naptar5_grafikus;
}