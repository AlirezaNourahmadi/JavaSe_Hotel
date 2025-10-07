package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.entity.Person;
import hotel.model.service.EmployeeService;
import hotel.model.entity.enums.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

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
    private TextField roleField; // Assuming string input for Role

    @FXML
    private TextField salaryField;

    @FXML
    private DatePicker hireDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic if needed
    }

    @FXML
    private void saveEmployee() {
        try {
            Employee employee = Employee.builder()
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
                    .build();
            EmployeeService.getService().save(employee);
            showAlert("Success", "Employee saved successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error saving employee: " + e.getMessage());
        }
    }

    @FXML
    private void editEmployee() {
        try {
            Employee employee = Employee.builder()
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
                    .build();
            EmployeeService.getService().edit(employee);
            showAlert("Success", "Employee updated successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error updating employee: " + e.getMessage());
        }
    }

    @FXML
    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            EmployeeService.getService().delete(id);
            showAlert("Success", "Employee deleted successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error deleting employee: " + e.getMessage());
        }
    }

    @FXML
    private void loadEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            Employee employee = EmployeeService.getService().findById(id);
            if (employee != null) {
                firstNameField.setText(employee.getFirstName());
                lastNameField.setText(employee.getLastName());
                emailField.setText(employee.getEmail());
                usernameField.setText(employee.getUserName());
                passwordField.setText(employee.getPassword());
                phoneField.setText(employee.getPhone());
                addressField.setText(employee.getAddress());
                birthDatePicker.setValue(employee.getBirthDate());
                personIdField.setText(String.valueOf(employee.getPersonId().getId()));
                employeeIdField.setText(String.valueOf(employee.getEmployeeId()));
                employeeNameField.setText(employee.getEmployeeName());
                roleField.setText(employee.getRole().name());
                salaryField.setText(String.valueOf(employee.getSalary()));
                hireDatePicker.setValue(employee.getHireDate());
            } else {
                showAlert("Info", "Employee not found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading employee: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}