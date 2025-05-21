module org.example.eliteltek2_grafikus {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eliteltek2_grafikus to javafx.fxml;
    exports org.example.eliteltek2_grafikus;
}