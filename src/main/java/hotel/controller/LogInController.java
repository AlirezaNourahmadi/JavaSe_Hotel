package hotel.controller;

import hotel.model.entity.Person;
import hotel.model.service.PersonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private TextField UserNameText, PasswordText;
    @FXML
    private Button DoneButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoneButton.setOnAction(e -> login());
    }

    private void login() {
        try {
            String userName = UserNameText.getText();
            String password = PasswordText.getText();
            List<Person> persons = PersonService.getService().findByUserNameAndPassWord(userName, password);


            if (persons != null && !persons.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful\nWelcome " + persons.get(0).getFirstName());
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed: Invalid username or password");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed: " + e.getMessage());
        }
    }

    private void clearForm() {
        UserNameText.clear();
        PasswordText.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg, ButtonType.OK);
        alert.show();
    }
}
