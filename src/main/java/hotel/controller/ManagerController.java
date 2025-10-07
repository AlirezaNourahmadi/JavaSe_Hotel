package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.entity.Manager;
import hotel.model.entity.Person;
import hotel.model.service.ManagerService;
import hotel.model.entity.enums.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField personIdField;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField salaryField;

    @FXML
    private DatePicker hireDatePicker;

    @FXML
    private TextField managerIdField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void saveManager() {
        try {
            Manager manager = Manager.builder()
                    .id(Integer.parseInt(idField.getText()))
                    .firstName(firstNameField.getText())
                    .lastName(lastNameField.getText())
                    .email(emailField.getText())
                    .userName(usernameField.getText())
                    .password(passwordField.getText())
                    .phone(phoneField.getText())
                    .address(addressField.getText())
                    .birthDate(birthDatePicker.getValue())
                    .personId(Person.builder().id(Integer.parseInt(personIdField.getText())).build())
                    .employeeId(Integer.parseInt(employeeIdField.getText()))
                    .employeeName(employeeNameField.getText())
                    .role(Role.valueOf(roleField.getText().toUpperCase()))
                    .salary(Double.parseDouble(salaryField.getText()))
                    .hireDate(hireDatePicker.getValue())
                    .managerId(Employee.builder().id(Integer.parseInt(managerIdField.getText())).build())
                    .build();
            ManagerService.getService().save(manager);
            showAlert("Success", "Manager saved successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error saving manager: " + e.getMessage());
        }
    }

    @FXML
    private void editManager() {
        try {
            Manager manager = Manager.builder()
                    .id(Integer.parseInt(idField.getText()))
                    .firstName(firstNameField.getText())
                    .lastName(lastNameField.getText())
                    .email(emailField.getText())
                    .userName(usernameField.getText())
                    .password(passwordField.getText())
                    .phone(phoneField.getText())
                    .address(addressField.getText())
                    .birthDate(birthDatePicker.getValue())
                    .personId(Person.builder().id(Integer.parseInt(personIdField.getText())).build())
                    .employeeId(Integer.parseInt(employeeIdField.getText()))
                    .employeeName(employeeNameField.getText())
                    .role(Role.valueOf(roleField.getText().toUpperCase()))
                    .salary(Double.parseDouble(salaryField.getText()))
                    .hireDate(hireDatePicker.getValue())
                    .managerId(Employee.builder().id(Integer.parseInt(managerIdField.getText())).build())
                    .build();
            ManagerService.getService().edit(manager);
            showAlert("Success", "Manager updated successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error updating manager: " + e.getMessage());
        }
    }

    @FXML
    private void deleteManager() {
        try {
            int id = Integer.parseInt(idField.getText());
            ManagerService.getService().delete(id);
            showAlert("Success", "Manager deleted successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error deleting manager: " + e.getMessage());
        }
    }

    @FXML
    private void loadManager() {
        try {
            int id = Integer.parseInt(idField.getText());
            Manager manager = ManagerService.getService().findById(id);
            if (manager != null) {
                firstNameField.setText(manager.getFirstName());
                lastNameField.setText(manager.getLastName());
                emailField.setText(manager.getEmail());
                usernameField.setText(manager.getUserName());
                passwordField.setText(manager.getPassword());
                phoneField.setText(manager.getPhone());
                addressField.setText(manager.getAddress());
                birthDatePicker.setValue(manager.getBirthDate());
                personIdField.setText(String.valueOf(manager.getPersonId().getId()));
                employeeIdField.setText(String.valueOf(manager.getEmployeeId()));
                employeeNameField.setText(manager.getEmployeeName());
                roleField.setText(manager.getRole().name());
                salaryField.setText(String.valueOf(manager.getSalary()));
                hireDatePicker.setValue(manager.getHireDate());
                managerIdField.setText(String.valueOf(manager.getManagerId().getId()));
            } else {
                showAlert("Info", "Manager not found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading manager: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}