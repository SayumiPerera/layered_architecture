package org.example.layeredarchitecture.controller;

import org.example.layeredarchitecture.bo.BOFactory;
import org.example.layeredarchitecture.bo.custom.PaymentBO;
import org.example.layeredarchitecture.dto.PaymentDTO;
import org.example.layeredarchitecture.view.tdm.PaymentTM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentViewController {

    @FXML private TextField paymentIdField;
    @FXML private TextField customerIdField;
    @FXML private TextField paymentMethodField;
    @FXML private TextField totalAmountField;
    @FXML private TextField qtyField;
    @FXML private DatePicker datePicker;

    @FXML private TableView<PaymentTM> paymentTable;
    @FXML private TableColumn<PaymentTM, Integer> colPaymentId;
    @FXML private TableColumn<PaymentTM, Integer> colCustomerId;
    @FXML private TableColumn<PaymentTM, String> colPaymentMethod;
    @FXML private TableColumn<PaymentTM, Double> colTotalAmount;
    @FXML private TableColumn<PaymentTM, Integer> colQty;
    @FXML private TableColumn<PaymentTM, LocalDate> colDate;

    // property injection
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    public void initialize() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTable();

        paymentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillFields(newSelection);
            }
        });
    }

    private void fillFields(PaymentTM tm) {
        paymentIdField.setText(String.valueOf(tm.getPaymentId()));
        customerIdField.setText(String.valueOf(tm.getCustomerId()));
        paymentMethodField.setText(tm.getPaymentMethod());
        totalAmountField.setText(String.valueOf(tm.getTotalAmount()));
        qtyField.setText(String.valueOf(tm.getQty()));
        datePicker.setValue(tm.getDate());
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            PaymentDTO dto = new PaymentDTO(
                    0,
                    Integer.parseInt(customerIdField.getText().trim()),
                    paymentMethodField.getText().trim(),
                    Double.parseDouble(totalAmountField.getText().trim()),
                    Integer.parseInt(qtyField.getText().trim()),
                    datePicker.getValue()
            );

            if (paymentBO.savePayment(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment saved successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter valid numbers.");
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            PaymentDTO dto = new PaymentDTO(
                    Integer.parseInt(paymentIdField.getText().trim()),
                    Integer.parseInt(customerIdField.getText().trim()),
                    paymentMethodField.getText().trim(),
                    Double.parseDouble(totalAmountField.getText().trim()),
                    Integer.parseInt(qtyField.getText().trim()),
                    datePicker.getValue()
            );

            if (paymentBO.updatePayment(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment updated successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter valid numbers.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            int paymentId = Integer.parseInt(paymentIdField.getText().trim());

            if (paymentBO.deletePayment(paymentId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment deleted successfully!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid Payment ID.");
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        try {
            int paymentId = Integer.parseInt(paymentIdField.getText().trim());

            PaymentDTO dto = paymentBO.findPayment(paymentId);
            if (dto != null) {
                fillFields(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getCustomerId(),
                        dto.getPaymentMethod(),
                        dto.getTotalAmount(),
                        dto.getQty(),
                        dto.getDate()
                ));
            } else {
                showAlert(Alert.AlertType.ERROR, "Not Found", "Payment not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid Payment ID.");
        }
    }

    private void loadTable() {
        paymentTable.getItems().clear();
        try {
            ArrayList<PaymentDTO> paymentList = paymentBO.getAllPayments();
            for (PaymentDTO dto : paymentList) {
                paymentTable.getItems().add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getCustomerId(),
                        dto.getPaymentMethod(),
                        dto.getTotalAmount(),
                        dto.getQty(),
                        dto.getDate()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void clearFields() {
        paymentIdField.clear();
        customerIdField.clear();
        paymentMethodField.clear();
        totalAmountField.clear();
        qtyField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
}