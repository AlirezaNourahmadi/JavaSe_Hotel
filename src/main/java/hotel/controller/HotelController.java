package hotel.controller;


import hotel.model.entity.Branch;
import hotel.model.entity.Hotel;
import hotel.model.service.BranchService;
import hotel.model.service.HotelService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class HotelController implements Initializable {


    @FXML
    private TextField nameText, branchText, idText, searchNameText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Hotel> hotelTable;

    @FXML
    private TableColumn<Hotel, Integer> idColumn;

    @FXML
    private TableColumn<Hotel, String> nameColumn, branchColumn;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Hotel Data!!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Branch branch = BranchService.getService().findById(Integer.parseInt(branchText.getText()));
                Hotel hotel =
                        Hotel
                                .builder()
                                .name(nameText.getText())
                                .branch(branch)
                                .id(Integer.parseInt(idText.getText()))
                                .build();
                HotelService.getService().save(hotel);
                log.info("Hotel saved successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hotel saved successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving hotel: " + e.getMessage(), ButtonType.OK);
                alert.show();

            }
        });
        editButton.setOnAction(event -> {
            try {
                Branch branch = BranchService.getService().findById(Integer.parseInt(branchText.getText()));
                Hotel hotel =
                        Hotel
                                .builder()
                                .name(nameText.getText())
                                .branch(branch)
                                .id(Integer.parseInt(idText.getText()))
                                .build();
                HotelService.getService().edit(hotel);
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error editing hotel: " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction(event -> {
            try {
                HotelService.getService().delete(Integer.parseInt(idText.getText()));
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting hotel: " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        searchNameText.setOnKeyReleased(event -> searchByName());
        hotelTable.setOnMouseClicked(event -> selectFromTable());
        hotelTable.setOnKeyReleased(event -> selectFromTable());


    }
    public void resetForm() throws Exception {
        nameText.clear();
        branchText.clear();
        idText.clear();
        searchNameText.clear();

        showDataOnTable(HotelService.getService().findAll());
    }
    public void showDataOnTable(List<Hotel> hotels){
        ObservableList<Hotel> hotelObservableList = FXCollections.observableList(hotels);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));


        hotelTable.setItems(hotelObservableList);

    }

    public void selectFromTable(){
        try {
            Hotel hotel = hotelTable.getSelectionModel().getSelectedItem();
            if (hotel != null) {
                idText.setText(String.valueOf(hotel.getId()));
                nameText.setText(hotel.getName());
                branchText.setText(String.valueOf(hotel.getBranch().getBranchId()));
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error selecting hotel from table: " + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
    public void searchByName(){
        try {
            String name = searchNameText.getText();
            List<Hotel> hotels = HotelService.getService().findByName(name);
            showDataOnTable(hotels);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error searching hotel by name: " + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
}
