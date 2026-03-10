package org.example.layeredarchitecture.controller;

import org.example.layeredarchitecture.bo.BOFactory;
import org.example.layeredarchitecture.bo.custom.StockBO;
import org.example.layeredarchitecture.dto.StockDTO;
import org.example.layeredarchitecture.view.tdm.StockTM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockViewController {

    @FXML private TextField txtStockId;
    @FXML private TextField txtItemId;
    @FXML private TextField txtQty;
    @FXML private TextField txtUpdateInfo;

    @FXML private TableView<StockTM> tblStock;
    @FXML private TableColumn<StockTM, Integer> colStockId;
    @FXML private TableColumn<StockTM, Integer> colItemId;
    @FXML private TableColumn<StockTM, Integer> colQty;
    @FXML private TableColumn<StockTM, String> colUpdateInfo;

    // property injection
    StockBO stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOType.STOCK);

    public void initialize() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUpdateInfo.setCellValueFactory(new PropertyValueFactory<>("updateInfo"));

        loadTable();

        tblStock.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillFields(newSelection);
            }
        });
    }

    private void fillFields(StockTM tm) {
        txtStockId.setText(String.valueOf(tm.getStockId()));
        txtItemId.setText(String.valueOf(tm.getItemId()));
        txtQty.setText(String.valueOf(tm.getQty()));
        txtUpdateInfo.setText(tm.getUpdateInfo());
    }

    @FXML
    void handleSave(ActionEvent event) {
        try {
            StockDTO dto = new StockDTO(
                    Integer.parseInt(txtStockId.getText()),
                    Integer.parseInt(txtItemId.getText()),
                    Integer.parseInt(txtQty.getText()),
                    txtUpdateInfo.getText()
            );

            if (stockBO.saveStock(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Stock saved successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save stock!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter valid numeric values for IDs and quantity!");
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        try {
            StockDTO dto = new StockDTO(
                    Integer.parseInt(txtStockId.getText()),
                    Integer.parseInt(txtItemId.getText()),
                    Integer.parseInt(txtQty.getText()),
                    txtUpdateInfo.getText()
            );

            if (stockBO.updateStock(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Stock updated successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update stock!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter valid numeric values for IDs and quantity!");
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        try {
            int stockId = Integer.parseInt(txtStockId.getText());

            if (stockBO.deleteStock(stockId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Stock deleted successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete stock!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter a valid numeric Stock ID!");
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        try {
            int stockId = Integer.parseInt(txtStockId.getText());

            StockDTO dto = stockBO.findStock(stockId);
            if (dto != null) {
                fillFields(new StockTM(dto.getStockId(), dto.getItemId(), dto.getQty(), dto.getUpdateInfo()));
            } else {
                showAlert(Alert.AlertType.WARNING, "Not Found", "Stock not found!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to search stock!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter a valid numeric Stock ID!");
        }
    }

    @FXML
    void handleReset(ActionEvent event) {
        clearFields();
    }

    private void loadTable() {
        tblStock.getItems().clear();
        try {
            ArrayList<StockDTO> stockList = stockBO.getAllStocks();
            for (StockDTO dto : stockList) {
                tblStock.getItems().add(new StockTM(
                        dto.getStockId(),
                        dto.getItemId(),
                        dto.getQty(),
                        dto.getUpdateInfo()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load stocks!");
        }
    }

    private void clearFields() {
        txtStockId.clear();
        txtItemId.clear();
        txtQty.clear();
        txtUpdateInfo.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}