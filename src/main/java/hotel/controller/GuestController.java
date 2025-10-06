package hotel.controller;

import hotel.model.entity.Guest;

import hotel.model.service.GuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class GuestController implements Initializable {

    @FXML
    private TextField idText, nameText, familyText, emailText, BirthDateDatePicker, phoneText, addressText, userNameText, passwordText;
    @FXML
    private TextField findByNameText, findByIdText;
    @FXML
    private TableView<Guest> guestTable;
    @FXML
    private TableColumn<Guest, Integer> idColumn;
    @FXML
    private TableColumn<Guest, String> nameColumn, familyColumn, emailColumn, phoneColumn;
    @FXML
    private Button saveButton, editButton, deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        saveButton.setOnAction(e -> saveGuest());
        editButton.setOnAction(e -> editGuest());
        deleteButton.setOnAction(e -> deleteGuest());

        findByNameText.setOnKeyReleased(e -> searchByName());
        findByIdText.setOnKeyReleased(e -> searchById());

        guestTable.setOnMouseClicked(e -> selectFromTable());
        guestTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        nameText.clear();
        familyText.clear();
        emailText.clear();
        BirthDateDatePicker.clear();
        phoneText.clear();
        addressText.clear();
        userNameText.clear();
        passwordText.clear();

        showDataOnTable(GuestService.getService().findAll());
    }

    private void showDataOnTable(List<Guest> guestList) {
        ObservableList<Guest> observableList = FXCollections.observableList(guestList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        familyColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        guestTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Guest guest = guestTable.getSelectionModel().getSelectedItem();
            if (guest != null) {
                idText.setText(String.valueOf(guest.getId()));
                nameText.setText(guest.getFirstName());
                familyText.setText(guest.getLastName());
                emailText.setText(guest.getEmail());
                BirthDateDatePicker.setText(guest.getBirthDate() != null ? guest.getBirthDate().toString() : "");
                phoneText.setText(guest.getPhone());
                addressText.setText(guest.getAddress());
                userNameText.setText(guest.getUserName());
                passwordText.setText(guest.getPassword());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveGuest() {
        try {
            Guest guest = Guest.builder()
                    .firstName(nameText.getText())
                    .lastName(familyText.getText())
                    .email(emailText.getText())
                    .phone(phoneText.getText())
                    .address(addressText.getText())
                    .userName(userNameText.getText())
                    .password(passwordText.getText())
                    .build();
            GuestService.getService().save(guest);
            showAlert(Alert.AlertType.INFORMATION, "Guest Saved Successfully\n" + guest);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Guest Save Failed: " + e.getMessage());
        }
    }

    private void editGuest() {
        try {
            Guest guest = Guest.builder()
                    .id(Integer.parseInt(idText.getText()))
                    .firstName(nameText.getText())
                    .lastName(familyText.getText())
                    .email(emailText.getText())
                    .phone(phoneText.getText())
                    .address(addressText.getText())
                    .userName(userNameText.getText())
                    .password(passwordText.getText())
                    .build();
            GuestService.getService().edit(guest);
            showAlert(Alert.AlertType.INFORMATION, "Guest Edited Successfully\n" + guest);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Guest Edit Failed: " + e.getMessage());
        }
    }

    private void deleteGuest() {
        try {
            int id = Integer.parseInt(idText.getText());
            GuestService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Guest Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Guest Delete Failed: " + e.getMessage());
        }
    }

    private void searchByName() {
        try {
            List<Guest> filtered = GuestService.getService().findAll();
            if (!findByNameText.getText().isEmpty()) {
                filtered.removeIf(g -> !g.getFirstName().toLowerCase().contains(findByNameText.getText().toLowerCase()));
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
                Guest guest = GuestService.getService().findById(id);
                showDataOnTable(guest != null ?
                        java.util.Collections.singletonList(guest) :
                        new java.util.ArrayList<Guest>());
            } else {
                showDataOnTable(GuestService.getService().findAll());
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
