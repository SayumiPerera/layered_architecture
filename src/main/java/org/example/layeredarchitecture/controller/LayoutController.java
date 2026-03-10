package org.example.layeredarchitecture.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.example.layeredarchitecture.Main;

public class LayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private AnchorPane getMainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Layout Loaded!");

    }

    @FXML
    private void loadView() {
        mainContent.getChildren().clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer.fxml"));
            AnchorPane st = fxmlLoader.load();

            LayoutController l = fxmlLoader.getController();
            mainContent.getChildren().add(st);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clickCustomerNav() throws IOException {
        Parent customerFxML = Main.loadFXML("Customer");
        mainContent.getChildren().setAll(customerFxML);
    }

    @FXML
    private void clickEmployeeNav() throws IOException {
        Parent employeeFxML = Main.loadFXML("Employee");
        mainContent.getChildren().setAll(employeeFxML);
    }

    @FXML
    private void clickOrderNav() throws IOException {
        Parent orderFXML = Main.loadFXML("Order");
        mainContent.getChildren().setAll(orderFXML);
    }

    @FXML
    private void clickItemNav() throws IOException {
        Parent itemFXML = Main.loadFXML("Item");
        mainContent.getChildren().setAll(itemFXML);
    }

    @FXML
    private void clickSupplierNav() throws IOException {
        Parent supplierFXML = Main.loadFXML("Supplier");
        mainContent.getChildren().setAll(supplierFXML);
    }

    @FXML
    private void clickPaymentNav() throws IOException {
        Parent paymentFXML = Main.loadFXML("Payment");
        mainContent.getChildren().setAll(paymentFXML);
    }

    @FXML
    private void clickStockNav() throws IOException {
        Parent stockFXML = Main.loadFXML("Stock");
        mainContent.getChildren().setAll(stockFXML);
    }

    @FXML
    private void clickLogoutNav() {
        try {
            org.example.layeredarchitecture.Main.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
