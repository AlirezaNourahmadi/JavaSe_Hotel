package hotel.controller;

import hotel.model.entity.Person;
import hotel.model.service.PersonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private TextField FirstNameText, LastNameText, UserNametext, PasswordText, PhoneText, AddressText, Emailtext;
    @FXML
    private DatePicker BirthDateDatePicker;
    @FXML
    private Button SaveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaveButton.setOnAction(e -> savePerson());
    }

    private void savePerson() {
        try {
            Person person = Person.builder()
                    .firstName(FirstNameText.getText())
                    .lastName(LastNameText.getText())
                    .email(Emailtext.getText())
                    .userName(UserNametext.getText())
                    .password(PasswordText.getText())
                    .phone(PhoneText.getText())
                    .address(AddressText.getText())
                    .birthDate(BirthDateDatePicker.getValue())
                    .build();

            PersonService.getService().save(person);
            showAlert(Alert.AlertType.INFORMATION, "Sign Up Successful\nWelcome " + person.getFirstName());
            clearForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed: " + e.getMessage());
        }
    }

    private void clearForm() {
        FirstNameText.clear();
        LastNameText.clear();
        Emailtext.clear();
        UserNametext.clear();
        PasswordText.clear();
        PhoneText.clear();
        AddressText.clear();
        BirthDateDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg, ButtonType.OK);
        alert.show();
    }
}
