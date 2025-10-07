package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.entity.Person;
import hotel.model.entity.enums.Role;
import hotel.model.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField idText, firstNameText, lastNameText, emailText, userNameText, passwordText, phoneText, addressText, personIdText, employeeIdText, employeeNameText, roleText, salaryText;
    @FXML
    private DatePicker birthDatePicker, hireDatePicker;
    @FXML
    private TextField findByNameText, findByIdText;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn, lastNameColumn, emailColumn, userNameColumn, phoneColumn, addressColumn, employeeNameColumn, roleColumn;
    @FXML
    private TableColumn<Employee, LocalDate> birthDateColumn, hireDateColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private Button saveButton, editButton, deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        saveButton.setOnAction(e -> saveEmployee());
        editButton.setOnAction(e -> editEmployee());
        deleteButton.setOnAction(e -> deleteEmployee());

        findByNameText.setOnKeyReleased(e -> searchByName());
        findByIdText.setOnKeyReleased(e -> searchById());

        employeeTable.setOnMouseClicked(e -> selectFromTable());
        employeeTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        firstNameText.clear();
        lastNameText.clear();
        emailText.clear();
        userNameText.clear();
        passwordText.clear();
        phoneText.clear();
        addressText.clear();
        birthDatePicker.setValue(null);
        personIdText.clear();
        employeeIdText.clear();
        employeeNameText.clear();
        roleText.clear();
        salaryText.clear();
        hireDatePicker.setValue(null);

        showDataOnTable(EmployeeService.getService().findAll());
    }

    private void showDataOnTable(List<Employee> employeeList) {
        ObservableList<Employee> observableList = FXCollections.observableList(employeeList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        employeeTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            if (employee != null) {
                idText.setText(String.valueOf(employee.getId()));
                firstNameText.setText(employee.getFirstName());
                lastNameText.setText(employee.getLastName());
                emailText.setText(employee.getEmail());
                userNameText.setText(employee.getUserName());
                passwordText.setText(employee.getPassword());
                phoneText.setText(employee.getPhone());
                addressText.setText(employee.getAddress());
                birthDatePicker.setValue(employee.getBirthDate());
                personIdText.setText(String.valueOf(employee.getPersonId().getId()));
                employeeIdText.setText(String.valueOf(employee.getEmployeeId()));
                employeeNameText.setText(employee.getEmployeeName());
                roleText.setText(employee.getRole().name());
                salaryText.setText(String.valueOf(employee.getSalary()));
                hireDatePicker.setValue(employee.getHireDate());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveEmployee() {
        try {
            Employee employee = Employee.builder()
                    .firstName(firstNameText.getText())
                    .lastName(lastNameText.getText())
                    .email(emailText.getText())
                    .userName(userNameText.getText())
                    .password(passwordText.getText())
                    .phone(phoneText.getText())
                    .address(addressText.getText())
                    .birthDate(birthDatePicker.getValue())
                    .personId(Person.builder().id(Integer.parseInt(personIdText.getText())).build())
                    .employeeId(Integer.parseInt(employeeIdText.getText()))
                    .employeeName(employeeNameText.getText())
                    .role(Role.valueOf(roleText.getText().toUpperCase()))
                    .salary(Double.parseDouble(salaryText.getText()))
                    .hireDate(hireDatePicker.getValue())
                    .build();
            EmployeeService.getService().save(employee);
            showAlert(Alert.AlertType.INFORMATION, "Employee Saved Successfully\n" + employee);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Employee Save Failed: " + e.getMessage());
        }
    }

    private void editEmployee() {
        try {
            Employee employee = Employee.builder()
                    .id(Integer.parseInt(idText.getText()))
                    .firstName(firstNameText.getText())
                    .lastName(lastNameText.getText())
                    .email(emailText.getText())
                    .userName(userNameText.getText())
                    .password(passwordText.getText())
                    .phone(phoneText.getText())
                    .address(addressText.getText())
                    .birthDate(birthDatePicker.getValue())
                    .personId(Person.builder().id(Integer.parseInt(personIdText.getText())).build())
                    .employeeId(Integer.parseInt(employeeIdText.getText()))
                    .employeeName(employeeNameText.getText())
                    .role(Role.valueOf(roleText.getText().toUpperCase()))
                    .salary(Double.parseDouble(salaryText.getText()))
                    .hireDate(hireDatePicker.getValue())
                    .build();
            EmployeeService.getService().edit(employee);
            showAlert(Alert.AlertType.INFORMATION, "Employee Edited Successfully\n" + employee);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Employee Edit Failed: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(idText.getText());
            EmployeeService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Employee Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Employee Delete Failed: " + e.getMessage());
        }
    }

    private void searchByName() {
        try {
            List<Employee> filtered = EmployeeService.getService().findAll();
            if (!findByNameText.getText().isEmpty()) {
                filtered.removeIf(e -> !e.getFirstName().toLowerCase().contains(findByNameText.getText().toLowerCase()));
            }
            showDataOnTable(filtered);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Search Failed: " + e.getMessage());
        }
    }

    private void searchById() {
        try {
            if (!findByIdText.getText().isEmpty()) {
                int id = Integer.parseInt(findByIdText.getText());
                Employee employee = EmployeeService.getService().findById(id);
                showDataOnTable(employee != null ?
                        java.util.Collections.singletonList(employee) :
                        new java.util.ArrayList<Employee>());
            } else {
                showDataOnTable(EmployeeService.getService().findAll());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Search Failed: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg, ButtonType.OK);
        alert.show();
    }
}