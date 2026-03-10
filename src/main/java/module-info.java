module com.example.layeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires com.example.layeredarchitecture;
    requires java.desktop;
    requires jfoenix;

    opens org.example.layeredarchitecture to javafx.fxml;
    opens org.example.layeredarchitecture.controller to javafx.fxml;
    opens org.example.layeredarchitecture.view.tdm to javafx.base;

    exports org.example.layeredarchitecture;
    exports org.example.layeredarchitecture.controller;
}