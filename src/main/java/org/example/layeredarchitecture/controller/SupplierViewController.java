package org.example.layeredarchitecture.controller;

import org.example.layeredarchitecture.bo.BOFactory;
import org.example.layeredarchitecture.bo.custom.SupplierBO;
import org.example.layeredarchitecture.dto.SupplierDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

    public class SupplierViewController {

        @FXML private TextField txtSupplierCompanyId;
        @FXML private TextField txtSupplierCompanyName;
        @FXML private TextField txtAddress;
        @FXML private TextField txtItems;
        @FXML private TextField txtContact;

        @FXML private Button btnSave;
        @FXML private Button btnUpdate;
        @FXML private Button btnDelete;
        @FXML private Button btnSearch;
        @FXML private Button btnReset;

        //property injection (Layered Architecture style - exactly like CustomerViewController)
        SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

        @FXML
        private void handleSave(ActionEvent event) {
            try {
                SupplierDTO supplier = getSupplierFromFields();
                boolean isSaved = supplierBO.saveSupplier(supplier);
                if (isSaved) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier saved successfully.");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed", "Failed to save supplier.");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }

        @FXML
        private void handleUpdate(ActionEvent event) {
            try {
                SupplierDTO supplier = getSupplierFromFields();
                boolean isUpdated = supplierBO.updateSupplier(supplier);
                if (isUpdated) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier updated successfully.");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed", "Failed to update supplier.");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }

        @FXML
        private void handleDelete(ActionEvent event) {
            String supplierId = txtSupplierCompanyId.getText();
            if (supplierId.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Enter Supplier ID to delete.");
                return;
            }

            try {
                boolean isDeleted = supplierBO.deleteSupplier(supplierId);
                if (isDeleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier deleted successfully.");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed", "Failed to delete supplier.");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }

        @FXML
        private void handleSearch(ActionEvent event) {
            String supplierId = txtSupplierCompanyId.getText();
            if (supplierId.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Enter Supplier ID to search.");
                return;
            }

            try {
                SupplierDTO supplier = supplierBO.findSupplier(supplierId);   // matches SupplierBO interface (like findCustomer in CustomerViewController)
                if (supplier != null) {
                    fillFieldsFromSupplier(supplier);
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Not Found", "No supplier found with ID: " + supplierId);
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }

        @FXML
        private void handleReset(ActionEvent event) {
            clearFields();
        }

        private SupplierDTO getSupplierFromFields() {
            String supplierId = txtSupplierCompanyId.getText();
            String companyName = txtSupplierCompanyName.getText();
            String address = txtAddress.getText();
            String itemsText = txtItems.getText();
            List<String> itemsList = Arrays.asList(itemsText.split(",\\s*"));
            String contact = txtContact.getText();

            return new SupplierDTO(supplierId, companyName, address, itemsList, contact);
        }

        private void fillFieldsFromSupplier(SupplierDTO supplier) {
            txtSupplierCompanyId.setText(supplier.getSupplierCompanyId());
            txtSupplierCompanyName.setText(supplier.getSupplierCompanyName());
            txtAddress.setText(supplier.getAddress());
            txtItems.setText(String.join(", ", supplier.getItems()));
            txtContact.setText(supplier.getContact());
        }

        private void clearFields() {
            txtSupplierCompanyId.clear();
            txtSupplierCompanyName.clear();
            txtAddress.clear();
            txtItems.clear();
            txtContact.clear();
        }

        private void showAlert(Alert.AlertType type, String title, String msg) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        }

}
