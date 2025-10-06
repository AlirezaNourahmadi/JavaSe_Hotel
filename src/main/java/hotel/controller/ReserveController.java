package hotel.controller;

import hotel.model.entity.Payment;
import hotel.model.entity.Reserve;
import hotel.model.entity.enums.ReserveStatus;
import hotel.model.service.ReserveService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {

    @FXML
    private DatePicker CheckIndatePicker, CheckOutdatePicker;
    @FXML
    private TextField GuestNumberText, PaymentText, SearchByIdText;
    @FXML
    private TableView<Reserve> ReserveTable;
    @FXML
    private TableColumn<Reserve, Integer> reserveIdColumn, numberOfGuestsColumn;
    @FXML
    private TableColumn<Reserve, String> statusColumn;
    @FXML
    private TableColumn<Reserve, Date> checkInColumn, checkOutColumn;
    @FXML
    private TableColumn<Reserve, Double> paymentColumn;
    @FXML
    private Button SaveButton, EditButton, DeleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        SaveButton.setOnAction(e -> saveReserve());
        EditButton.setOnAction(e -> editReserve());
        DeleteButton.setOnAction(e -> deleteReserve());

        SearchByIdText.setOnKeyReleased(e -> searchById());

        ReserveTable.setOnMouseClicked(e -> selectFromTable());
        ReserveTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        CheckIndatePicker.setValue(null);
        CheckOutdatePicker.setValue(null);
        GuestNumberText.clear();
        PaymentText.clear();
        SearchByIdText.clear();

        showDataOnTable(ReserveService.getService().findAll());
    }

    private void showDataOnTable(List<Reserve> reserveList) {
        ObservableList<Reserve> observableList = FXCollections.observableList(reserveList);

        reserveIdColumn.setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        numberOfGuestsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        paymentColumn.setCellValueFactory(c -> new SimpleDoubleProperty(
                c.getValue().getPayment() != null ? c.getValue().getPayment().getAmount() : 0
        ).asObject());

        ReserveTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Reserve reserve = ReserveTable.getSelectionModel().getSelectedItem();
            if (reserve != null) {
                CheckIndatePicker.setValue(reserve.getCheckIn() != null ?
                        new java.sql.Date(reserve.getCheckIn().getTime()).toLocalDate() : null);
                CheckOutdatePicker.setValue(reserve.getCheckOut() != null ?
                        new java.sql.Date(reserve.getCheckOut().getTime()).toLocalDate() : null);
                GuestNumberText.setText(String.valueOf(reserve.getNumberOfGuests()));
                PaymentText.setText(reserve.getPayment() != null ? String.valueOf(reserve.getPayment().getAmount()) : "");
                SearchByIdText.setText(String.valueOf(reserve.getReserveId()));
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveReserve() {
        try {
            Payment payment = Payment.builder()
                    .amount(Double.parseDouble(PaymentText.getText()))
                    .build();

            Reserve reserve = Reserve.builder()
                    .checkIn(CheckIndatePicker.getValue() != null ? Date.valueOf(CheckIndatePicker.getValue()) : null)
                    .checkOut(CheckOutdatePicker.getValue() != null ? Date.valueOf(CheckOutdatePicker.getValue()) : null)
                    .numberOfGuests(Integer.parseInt(GuestNumberText.getText()))
                    .status(ReserveStatus.PENDING)
                    .payment(payment)
                    .build();

            ReserveService.getService().save(reserve);
            showAlert(Alert.AlertType.INFORMATION, "Reserve Saved Successfully\n" + reserve);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Reserve Save Failed: " + e.getMessage());
        }
    }

    private void editReserve() {
        try {
            Payment payment = Payment.builder()
                    .amount(Double.parseDouble(PaymentText.getText()))
                    .build();

            Reserve reserve = Reserve.builder()
                    .reserveId(Integer.parseInt(SearchByIdText.getText()))
                    .checkIn(CheckIndatePicker.getValue() != null ? Date.valueOf(CheckIndatePicker.getValue()) : null)
                    .checkOut(CheckOutdatePicker.getValue() != null ? Date.valueOf(CheckOutdatePicker.getValue()) : null)
                    .numberOfGuests(Integer.parseInt(GuestNumberText.getText()))
                    .status(ReserveStatus.CONFIRMED)
                    .payment(payment)
                    .build();

            ReserveService.getService().edit(reserve);
            showAlert(Alert.AlertType.INFORMATION, "Reserve Edited Successfully\n" + reserve);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Reserve Edit Failed: " + e.getMessage());
        }
    }

    private void deleteReserve() {
        try {
            int id = Integer.parseInt(SearchByIdText.getText());
            ReserveService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Reserve Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Reserve Delete Failed: " + e.getMessage());
        }
    }

    private void searchById() {
        try {
            if (!SearchByIdText.getText().isEmpty()) {
                int id = Integer.parseInt(SearchByIdText.getText());
                Reserve reserve = ReserveService.getService().findById(id);
                showDataOnTable(reserve != null ?
                        java.util.Collections.singletonList(reserve) :
                        new java.util.ArrayList<Reserve>());
            } else {
                showDataOnTable(ReserveService.getService().findAll());
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
