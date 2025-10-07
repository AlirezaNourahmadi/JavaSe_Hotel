package hotel.controller;

import hotel.model.entity.Person;
import hotel.model.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


@Log4j2
public class PersonController implements Initializable {
    @FXML
    private TextField nameText, familyText, phoneText, emailText, addressText, userNameText, passwordText, idText, searchNameText, searchFamilyText;

    @FXML
    private DatePicker birthDateText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;

    @FXML
    private TableColumn<Person, String> nameColumn, familyColumn, phoneColumn, emailColumn, addressColumn;

    @FXML
    private TableColumn<Person, DatePicker> birthDateColumn;










    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();

        }
        saveButton.setOnAction(event -> {
            try {
                Person person =
                        Person
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .firstName(nameText.getText())
                                .lastName(familyText.getText())
                                .birthDate(birthDateText.getValue())
                                .phone(phoneText.getText())
                                .email(emailText.getText())
                                .address(addressText.getText())
                                .userName(userNameText.getText())
                                .password(passwordText.getText())
                                .build();
                PersonService.getService().save(person);
                log.info("Person Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Saved Successfully\n" + person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {

                log.error("Person Save Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Data !!!", ButtonType.OK);
                alert.show();

            }
        });

        editButton.setOnAction(event -> {
            try {
                Person person =
                        Person
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .firstName(nameText.getText())
                                .lastName(familyText.getText())
                                .birthDate(birthDateText.getValue())
                                .phone(phoneText.getText())
                                .email(emailText.getText())
                                .address(addressText.getText())
                                .userName(userNameText.getText())
                                .password(passwordText.getText())
                                .build();
                PersonService.getService().update(person);
                log.info("Person Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edited Successfully\n" + person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Edit Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Editing Data !!!", ButtonType.OK);
                alert.show();

            }
        });

        deleteButton.setOnAction(event -> {
            try {
                PersonService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Person Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Delete Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Deleting Data !!!", ButtonType.OK);
                alert.show();

            }
        });


        searchNameText.setOnKeyReleased((event) -> searchByNameAndFamily());
        searchFamilyText.setOnKeyReleased((event) -> searchByNameAndFamily());

        personTable.setOnMouseReleased((event) -> selectFromTable());

        personTable.setOnKeyReleased((event) -> selectFromTable());

    }




    private void resetForm() throws Exception {
        idText.clear();
        nameText.clear();
        familyText.clear();
        phoneText.clear();
        emailText.clear();
        addressText.clear();
        userNameText.clear();
        passwordText.clear();

        birthDateText.setValue(LocalDate.now());


        showDateOnTable(PersonService.getService().findAll());
    }

    private void showDateOnTable(List<Person> personList) {
        ObservableList<Person> observableList = FXCollections.observableList(personList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));


        personTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Person person = personTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(person.getId()));
            nameText.setText(person.getFirstName());
            familyText.setText(person.getLastName());
            birthDateText.setValue(person.getBirthDate());
            phoneText.setText(person.getPhone());
            addressText.setText(person.getAddress());
            emailText.setText(person.getEmail());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }



    }

    public void searchByNameAndFamily () {
        try {
            showDateOnTable(PersonService.getService().findByNameAndFamily(searchNameText.getText(), searchFamilyText.getText()));
            log.info("Persons FindByNameAndFamily :{} {}", searchNameText.getText(), searchFamilyText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Person FindNameFamily {} {} Failed {}", searchNameText.getText(), searchFamilyText.getText(), e.getMessage());
        }
    }
}
