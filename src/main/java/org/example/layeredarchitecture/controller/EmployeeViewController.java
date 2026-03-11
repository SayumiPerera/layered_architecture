package org.example.layeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import org.example.layeredarchitecture.bo.BOFactory;
import org.example.layeredarchitecture.bo.custom.EmployeeBO;
import org.example.layeredarchitecture.dto.EmployeeDTO;
import org.example.layeredarchitecture.view.tdm.EmployeeTM;

//import org.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeViewController {
    public AnchorPane root;
    public TextField txtEmployeeName;
    public TextField txtEmployeeId;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TextField txtEmployeeAddress;
    public TableView<EmployeeTM> tblEmployees;
    public JFXButton btnAddNewEmployee;

    //property injection

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize() {
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        initUI();

        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtEmployeeId.setText(newValue.getId());
                txtEmployeeName.setText(newValue.getName());
                txtEmployeeAddress.setText(newValue.getAddress());

                txtEmployeeId.setDisable(false);
                txtEmployeeName.setDisable(false);
                txtEmployeeAddress.setDisable(false);
            }
        });

        txtEmployeeAddress.setOnAction(event -> btnSave.fire());
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        tblEmployees.getItems().clear();
        /*Get all employees*/
        try {
            //tight coupling--->Loose couple
            ArrayList<EmployeeDTO> employeeDTOArrayList = employeeBO.getAllEmployees();

            for (EmployeeDTO employeeDTO : employeeDTOArrayList) {
                tblEmployees.getItems().add(new EmployeeTM(
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getSalary()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeAddress.clear();
        txtEmployeeId.setDisable(true);
        txtEmployeeName.setDisable(true);
        txtEmployeeAddress.setDisable(true);
        txtEmployeeId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/org/example/layered_architecture/layout.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void handleUpdate(ActionEvent actionEvent) {
        txtEmployeeId.setDisable(false);
        txtEmployeeName.setDisable(false);
        txtEmployeeAddress.setDisable(false);
        txtEmployeeId.clear();
        txtEmployeeId.setText(generateNewId());
        txtEmployeeName.clear();
        txtEmployeeAddress.clear();
        txtEmployeeName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblEmployees.getSelectionModel().clearSelection();
    }

    public void handleSave(ActionEvent actionEvent) {
        String id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtEmployeeAddress.getText();
        double salary = Double.parseDouble(txtEmployeeId.getText());

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtEmployeeName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtEmployeeAddress.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Employee*/
            try {
                if (existEmployee(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                //tight coupling--->Loose couple
                employeeBO.saveEmployee(new EmployeeDTO(id, name, address,salary));

                tblEmployees.getItems().add(new EmployeeTM(id, name, address,salary));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the employee " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            /*Update employee*/
            try {
                if (!existEmployee(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such employee associated with the id " + id).show();
                }
                //tight coupling--->Loose couple
                employeeBO.updateEmployee(new EmployeeDTO(id, name, address,salary));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the employee " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            EmployeeTM selectedEmployee = tblEmployees.getSelectionModel().getSelectedItem();
            selectedEmployee.setName(name);
            selectedEmployee.setAddress(address);
            tblEmployees.refresh();
        }

        btnAddNewEmployee.fire();
    }

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        //tight coupling--->Loose couple
        return employeeBO.existEmployee(id);
    }

    public void handleDelete(ActionEvent actionEvent) {
        /*Delete Employee*/
        String id = tblEmployees.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existEmployee(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such employee associated with the id " + id).show();
            }
            //tight coupling--->Loose couple
            employeeBO.deleteEmployee(id);

            tblEmployees.getItems().remove(tblEmployees.getSelectionModel().getSelectedItem());
            tblEmployees.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the employee " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateNewId() {
        try {
            return employeeBO.generateEmployeeNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblEmployees.getItems().isEmpty()) {
            return "E00-001";
        } else {
            String id = getLastEmployeeId();
            int newEmployeeId = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        }
    }

    private String getLastEmployeeId() {
        List<EmployeeTM> tempEmployeesList = new ArrayList<>(tblEmployees.getItems());
        Collections.sort(tempEmployeesList);
        return tempEmployeesList.get(tempEmployeesList.size() - 1).getId();
    }

}
