package hotel.controller;

import hotel.model.entity.Payment;
import hotel.model.entity.enums.PaymentStatus;
import hotel.model.entity.enums.PaymentType;
import hotel.model.service.PaymentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField receiptIdText, amountText, statusText, paymentTypeText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField findByAmountText, findByIdText;
    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, Integer> receiptIdColumn;
    @FXML
    private TableColumn<Payment, Double> amountColumn;
    @FXML
    private TableColumn<Payment, Date> dateColumn;
    @FXML
    private TableColumn<Payment, PaymentStatus> statusColumn;
    @FXML
    private TableColumn<Payment, PaymentType> paymentTypeColumn;
    @FXML
    private Button saveButton, editButton, deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        saveButton.setOnAction(e -> savePayment());
        editButton.setOnAction(e -> editPayment());
        deleteButton.setOnAction(e -> deletePayment());

        findByAmountText.setOnKeyReleased(e -> searchByAmount());
        findByIdText.setOnKeyReleased(e -> searchById());

        paymentTable.setOnMouseClicked(e -> selectFromTable());
        paymentTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        receiptIdText.clear();
        amountText.clear();
        datePicker.setValue(null);
        statusText.clear();
        paymentTypeText.clear();

        showDataOnTable(PaymentService.getService().findAll());
    }

    private void showDataOnTable(List<Payment> paymentList) {
        ObservableList<Payment> observableList = FXCollections.observableList(paymentList);

        receiptIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        paymentTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Payment payment = paymentTable.getSelectionModel().getSelectedItem();
            if (payment != null) {
                receiptIdText.setText(String.valueOf(payment.getReceiptId()));
                amountText.setText(String.valueOf(payment.getAmount()));
                LocalDate localDate = payment.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                datePicker.setValue(localDate);
                statusText.setText(payment.getStatus().name());
                paymentTypeText.setText(payment.getPaymentType().name());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void savePayment() {
        try {
            LocalDate localDate = datePicker.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Payment payment = Payment.builder()
                    .amount(Double.parseDouble(amountText.getText()))
                    .date(date)
                    .status(PaymentStatus.valueOf(statusText.getText().toUpperCase()))
                    .paymentType(PaymentType.valueOf(paymentTypeText.getText().toUpperCase()))
                    .build();
            PaymentService.getService().save(payment);
            showAlert(Alert.AlertType.INFORMATION, "Payment Saved Successfully\n" + payment);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Payment Save Failed: " + e.getMessage());
        }
    }

    private void editPayment() {
        try {
            LocalDate localDate = datePicker.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Payment payment = Payment.builder()
                    .receiptId(Integer.parseInt(receiptIdText.getText()))
                    .amount(Double.parseDouble(amountText.getText()))
                    .date(date)
                    .status(PaymentStatus.valueOf(statusText.getText().toUpperCase()))
                    .paymentType(PaymentType.valueOf(paymentTypeText.getText().toUpperCase()))
                    .build();
            PaymentService.getService().edit(payment);
            showAlert(Alert.AlertType.INFORMATION, "Payment Edited Successfully\n" + payment);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Payment Edit Failed: " + e.getMessage());
        }
    }

    private void deletePayment() {
        try {
            int id = Integer.parseInt(receiptIdText.getText());
            PaymentService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Payment Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Payment Delete Failed: " + e.getMessage());
        }
    }

    private void searchByAmount() {
        try {
            List<Payment> filtered = PaymentService.getService().findAll();
            if (!findByAmountText.getText().isEmpty()) {
                double amount = Double.parseDouble(findByAmountText.getText());
                filtered.removeIf(p -> p.getAmount() != amount);
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
                Payment payment = PaymentService.getService().findById(id);
                showDataOnTable(payment != null ?
                        java.util.Collections.singletonList(payment) :
                        new java.util.ArrayList<>());
            } else {
                showDataOnTable(PaymentService.getService().findAll());
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