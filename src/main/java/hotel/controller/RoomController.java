package hotel.controller;

import hotel.model.entity.Room;
import hotel.model.entity.enums.RoomStatus;
import hotel.model.entity.enums.RoomType;
import hotel.model.service.RoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    @FXML
    private TextField serachByIdText;
    @FXML
    private ComboBox<Integer> CapacityComboBox;
    @FXML
    private RadioButton TypeSingleRadioButton;
    @FXML
    private TableView<Room> RoomTable;
    @FXML
    private TableColumn<Room, Integer> roomIdColumn, priceColumn, capacityColumn;
    @FXML
    private TableColumn<Room, RoomType> typeColumn;
    @FXML
    private TableColumn<Room, RoomStatus> statusColumn;
    @FXML
    private Button SaveButton, DeleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        SaveButton.setOnAction(e -> saveRoom());
        DeleteButton.setOnAction(e -> deleteRoom());

        serachByIdText.setOnKeyReleased(e -> searchById());

        RoomTable.setOnMouseClicked(e -> selectFromTable());
        RoomTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        serachByIdText.clear();
        CapacityComboBox.getItems().clear();
        for (int i = 1; i <= 10; i++) CapacityComboBox.getItems().add(i);
        CapacityComboBox.getSelectionModel().selectFirst();

        showDataOnTable(RoomService.getService().findAll());
    }

    private void showDataOnTable(List<Room> roomList) {
        ObservableList<Room> observableList = FXCollections.observableList(roomList);

        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        RoomTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Room room = RoomTable.getSelectionModel().getSelectedItem();
            if (room != null) {
                serachByIdText.setText(String.valueOf(room.getRoomId()));
                CapacityComboBox.getSelectionModel().select(Integer.valueOf(room.getCapacity()));
                TypeSingleRadioButton.setSelected(room.getType() == RoomType.SINGLE);
                // برای چند نوع، می‌تونی RadioButtonهای دیگه هم اضافه کنی
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveRoom() {
        try {
            Room room = Room.builder()
                    .roomId(Integer.parseInt(serachByIdText.getText()))
                    .capacity(CapacityComboBox.getSelectionModel().getSelectedItem())
                    .type(TypeSingleRadioButton.isSelected() ? RoomType.SINGLE : RoomType.DOUBLE)
                    .status(RoomStatus.AVAILABLE) // می‌تونی حالت دیگه اضافه کنی یا RadioButton جدا
                    .pricePerNight(100) // می‌تونی قیمت رو از TextField بگیری
                    .build();

            RoomService.getService().save(room);
            showAlert(Alert.AlertType.INFORMATION, "Room Saved Successfully\n" + room);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Room Save Failed: " + e.getMessage());
        }
    }

    private void deleteRoom() {
        try {
            int id = Integer.parseInt(serachByIdText.getText());
            RoomService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Room Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Room Delete Failed: " + e.getMessage());
        }
    }

    private void searchById() {
        try {
            if (!serachByIdText.getText().isEmpty()) {
                int id = Integer.parseInt(serachByIdText.getText());
                Room room = RoomService.getService().findById(id);
                showDataOnTable(room != null ?
                        java.util.Collections.singletonList(room) :
                        new java.util.ArrayList<Room>());
            } else {
                showDataOnTable(RoomService.getService().findAll());
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
