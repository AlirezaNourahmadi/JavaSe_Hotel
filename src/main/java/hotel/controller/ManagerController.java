package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.entity.Manager;
import hotel.model.entity.Person;
import hotel.model.entity.enums.Role;
import hotel.model.service.ManagerService;
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

public class ManagerController implements Initializable {

    @FXML
    private TextField idText, firstNameText, lastNameText, emailText, userNameText, passwordText, phoneText, addressText, personIdText, employeeIdText, employeeNameText, roleText, salaryText, managerIdText;
    @FXML
    private DatePicker birthDatePicker, hireDatePicker;
    @FXML
    private TextField findByNameText, findByIdText;
    @FXML
    private TableView<Manager> managerTable;
    @FXML
    private TableColumn<Manager, Integer> idColumn;
    @FXML
    private TableColumn<Manager, String> firstNameColumn, lastNameColumn, emailColumn, userNameColumn, phoneColumn, addressColumn, employeeNameColumn, roleColumn;
    @FXML
    private TableColumn<Manager, LocalDate> birthDateColumn, hireDateColumn;
    @FXML
    private TableColumn<Manager, Double> salaryColumn;
    @FXML
    private Button saveButton, editButton, deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        saveButton.setOnAction(e -> saveManager());
        editButton.setOnAction(e -> editManager());
        deleteButton.setOnAction(e -> deleteManager());

        findByNameText.setOnKeyReleased(e -> searchByName());
        findByIdText.setOnKeyReleased(e -> searchById());

        managerTable.setOnMouseClicked(e -> selectFromTable());
        managerTable.setOnKeyReleased(e -> selectFromTable());
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
        managerIdText.clear();

        showDataOnTable(ManagerService.getService().findAll());
    }

    private void showDataOnTable(List<Manager> managerList) {
        ObservableList<Manager> observableList = FXCollections.observableList(managerList);

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

        managerTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Manager manager = managerTable.getSelectionModel().getSelectedItem();
            if (manager != null) {
                idText.setText(String.valueOf(manager.getId()));
                firstNameText.setText(manager.getFirstName());
                lastNameText.setText(manager.getLastName());
                emailText.setText(manager.getEmail());
                userNameText.setText(manager.getUserName());
                passwordText.setText(manager.getPassword());
                phoneText.setText(manager.getPhone());
                addressText.setText(manager.getAddress());
                birthDatePicker.setValue(manager.getBirthDate());
                personIdText.setText(String.valueOf(manager.getPersonId().getId()));
                employeeIdText.setText(String.valueOf(manager.getEmployeeId()));
                employeeNameText.setText(manager.getEmployeeName());
                roleText.setText(manager.getRole().name());
                salaryText.setText(String.valueOf(manager.getSalary()));
                hireDatePicker.setValue(manager.getHireDate());
                managerIdText.setText(String.valueOf(manager.getManagerId().getId()));
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveManager() {
        try {
            Manager manager = Manager.builder()
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
                    .managerId(Employee.builder().id(Integer.parseInt(managerIdText.getText())).build())
                    .build();
            ManagerService.getService().save(manager);
            showAlert(Alert.AlertType.INFORMATION, "Manager Saved Successfully\n" + manager);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Manager Save Failed: " + e.getMessage());
        }
    }

    private void editManager() {
        try {
            Manager manager = Manager.builder()
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
                    .managerId(Employee.builder().id(Integer.parseInt(managerIdText.getText())).build())
                    .build();
            ManagerService.getService().edit(manager);
            showAlert(Alert.AlertType.INFORMATION, "Manager Edited Successfully\n" + manager);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Manager Edit Failed: " + e.getMessage());
        }
    }

    private void deleteManager() {
        try {
            int id = Integer.parseInt(idText.getText());
            ManagerService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Manager Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Manager Delete Failed: " + e.getMessage());
        }
    }

    private void searchByName() {
        try {
            List<Manager> filtered = ManagerService.getService().findAll();
            if (!findByNameText.getText().isEmpty()) {
                filtered.removeIf(m -> !m.getFirstName().toLowerCase().contains(findByNameText.getText().toLowerCase()));
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
                Manager manager = ManagerService.getService().findById(id);
                showDataOnTable(manager != null ?
                        java.util.Collections.singletonList(manager) :
                        new java.util.ArrayList<>());
            } else {
                showDataOnTable(ManagerService.getService().findAll());
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