package org.example.layeredarchitecture.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.layeredarchitecture.Main;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void initialize() {

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login_page(new ActionEvent());
            }
        });

        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login_page(new ActionEvent());
            }
        });
    }

    @FXML
    private void login_page(ActionEvent event) {
        String realUsername = "s";
        String realPassword = "s";

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (realUsername.equals(username) && realPassword.equals(password)) {
            try {
                Main.setRoot("Layout");
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("INCORRECT");
            alert.setContentText("Please enter correct username and password.");
            alert.show();
        }
    }
}
