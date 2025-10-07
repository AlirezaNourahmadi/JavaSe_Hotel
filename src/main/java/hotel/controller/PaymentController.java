package hotel.controller;

import hotel.model.entity.Payment;
import hotel.model.service.PaymentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField amountField; // Example field


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void savePayment() {
        try {
            Payment payment = new Payment();
            payment.setReceiptId(Integer.parseInt(idField.getText()));
            PaymentService.getService().save(payment);
            showAlert("Success", "Payment saved successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error saving payment: " + e.getMessage());
        }
    }

    @FXML
    private void editPayment() {
        try {
            Payment payment = new Payment();
            payment.setReceiptId(Integer.parseInt(idField.getText()));
            PaymentService.getService().edit(payment);
            showAlert("Success", "Payment updated successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error updating payment: " + e.getMessage());
        }
    }

    @FXML
    private void deletePayment() {
        try {
            int id = Integer.parseInt(idField.getText());
            PaymentService.getService().delete(id);
            showAlert("Success", "Payment deleted successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error deleting payment: " + e.getMessage());
        }
    }

    @FXML
    private void loadPayment() {
        try {
            int id = Integer.parseInt(idField.getText());
            Payment payment = PaymentService.getService().findById(id);
            if (payment != null) {
            } else {
                showAlert("Info", "Payment not found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading payment: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}