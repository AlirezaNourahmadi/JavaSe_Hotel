package hotel.controller;


import hotel.model.entity.Property;
import hotel.model.service.BranchService;
import hotel.model.service.PropertyService;
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
public class PropertyController implements Initializable {

    @FXML
    private TextField nameText, quantityText, searchNameText;

    @FXML
    private TableView<Property> propertyTable;

    @FXML
    private TableColumn<Property, String> nameColumn, quantityColumn;

    @FXML
    private Button editButton, deleteButton, saveButton;




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
                Property property =
                        Property
                                .builder()
                                .name(nameText.getText())
                                .quantity(Integer.parseInt(quantityText.getText()))
                                .build();
                PropertyService.getService().save(property);
                log.info("Property saved: {}", property);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property saved successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Property!!!", ButtonType.OK);
                alert.show();
                log.error("Error Saving Property", e);

            }
        });

        editButton.setOnAction(event -> {
            try {
                Property property =
                        Property
                                .builder()
                                .name(nameText.getText())
                                .quantity(Integer.parseInt(quantityText.getText()))
                                .build();
                PropertyService.getService().update(property);
                log.info("Property updated: {}", property);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property updated successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Updating Property!!!", ButtonType.OK);
                alert.show();
                log.error("Error Updating Property", e);

            }
        });
        deleteButton.setOnAction(event -> {
            try {
                PropertyService.getService().delete(nameText.getText());
                log.info("Property deleted: {}", nameText.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property deleted successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Deleting Property!!!", ButtonType.OK);
                alert.show();
                log.error("Error Deleting Property", e);

            }
        });

        searchNameText.setOnKeyReleased(event -> searchByName());

        propertyTable.setOnMouseReleased(event -> searchByName());
        propertyTable.setOnKeyReleased(event -> searchByName());

    }
    private void resetForm() throws Exception {
        nameText.clear();
        quantityText.clear();
        searchNameText.clear();

        showDateOnTable(BranchService.getService().findAll());

    }

    private void showDateOnTable(List<Property> propertyList) {
        ObservableList<Property> observableList = FXCollections.observableList(propertyList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        propertyTable.setItems(observableList);
    }

    private void searchByName() {
        try {
            showDateOnTable(BranchService.getService().findAll());
            log.info("Property Find By Name: {}", searchNameText.getText());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Property By Name!!!", ButtonType.OK);
            alert.show();
            log.error("Error Searching Property By Name", e);

        }

    }
}
