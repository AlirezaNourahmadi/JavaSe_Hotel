package hotel.controller;


import hotel.model.entity.AssignProperty;
import hotel.model.entity.Employee;
import hotel.model.entity.Property;
import hotel.model.entity.Room;
import hotel.model.service.AssignPropertyService;
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
public class AssignPropertyController implements Initializable {

    @FXML
    private TextField idText, quantityText, roomText, assignedByText, searchIdText;

    @FXML
    private DatePicker assignedDateText;

    @FXML
    private Button deleteButton, saveButton, editButton;

    @FXML
    private TableView<AssignProperty> assignedPropertyTable;

    @FXML
    private TableColumn<Property, Integer> idColumn, quantityColumn;

    @FXML
    private  TableColumn<Property, String> roomColumn, assignedByColumn;

    @FXML
    private  TableColumn<AssignProperty, DatePicker> assignedDateColumn;












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
               Room room = hotel.model.entity.Room.builder()
                   .roomId(Integer.parseInt(roomText.getText()))
                   .build();
               Employee employee = hotel.model.entity.Employee.builder()
                   .employeeId(Integer.parseInt(assignedByText.getText()))
                   .build();
               AssignProperty assignProperty =
                          AssignProperty
                                 .builder()
                                 .propertyId(Integer.parseInt(idText.getText()))
                                 .quantity(Integer.parseInt(quantityText.getText()))
                                 .room(room)
                                 .assignedBy(employee)
                                 .assignedDate(assignedDateText.getValue())
                                 .build();
                AssignPropertyService.getService().save(assignProperty);
                log.info("Assigned Property saved: {}", assignProperty);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assigned Property saved successfully!", ButtonType.OK);
                alert.show();
                resetForm();
           }catch (Exception e){

               log.error("Error Saving Assigned Property", e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Assigned Property!!!", ButtonType.OK);
                alert.show();
           }
        });

        editButton.setOnAction(event -> {
           try {
               AssignProperty assignProperty =
                       AssignProperty
                               .builder()
                               .propertyId(Integer.parseInt(idText.getText()))
                               .quantity(Integer.parseInt(quantityText.getText()))
                               .room(hotel.model.entity.Room.builder()
                                   .roomId(Integer.parseInt(roomText.getText()))
                                   .build())
                               .assignedBy(hotel.model.entity.Employee.builder()
                                   .employeeId(Integer.parseInt(assignedByText.getText()))
                                   .build())
                               .assignedDate(assignedDateText.getValue())
                               .build();
                AssignPropertyService.getService().update(assignProperty);
                log.info("Assigned Property updated: {}", assignProperty);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assigned Property updated successfully!", ButtonType.OK);
                alert.show();
                resetForm();
           }catch (Exception e){
                log.error("Error Updating Assigned Property", e);
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Error Updating Assigned Property!!!", ButtonType.OK);
                 alert.show();
           }
        });

        deleteButton.setOnAction(event -> {
            try {
                AssignPropertyService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Assigned Property deleted: {}", idText.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assigned Property deleted successfully!", ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {
                log.error("Error Deleting Assigned Property", e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Deleting Assigned Property!!!", ButtonType.OK);
                alert.show();
            }
        });

        assignedPropertyTable.setOnMouseReleased(event -> selectFromTable());
        assignedPropertyTable.setOnKeyReleased(event -> selectFromTable());
        searchIdText.setOnKeyReleased(event -> searchById());


    }
    public void resetForm() throws Exception {
        idText.clear();
        quantityText.clear();
        roomText.clear();
        assignedByText.clear();
        assignedDateText.setValue(LocalDate.now());

        showDateOnTable(AssignPropertyService.getService().findAll());
    }
    public void showDateOnTable(List<AssignProperty> assignPropertyList) {
        ObservableList<AssignProperty> observableList = FXCollections.observableList(assignPropertyList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        assignedByColumn.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
        assignedDateColumn.setCellValueFactory(new PropertyValueFactory<>("assignedDate"));


        assignedPropertyTable.setItems(observableList);
    }

    public void selectFromTable(){
        try {
            AssignProperty assignProperty = assignedPropertyTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(assignProperty.getPropertyId()));
            quantityText.setText(String.valueOf(assignProperty.getQuantity()));
            roomText.setText(String.valueOf(assignProperty.getRoom().getRoomId()));
            assignedByText.setText(String.valueOf(assignProperty.getAssignedBy().getEmployeeId()));
            assignedDateText.setValue(assignProperty.getAssignedDate());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

    }
    public void searchById() {
        try {
            showDateOnTable(AssignPropertyService.getService().findById(Integer.parseInt(idText.getText())));
            log.info("Search results for ID: {}", idText.getText());


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching By ID !!!", ButtonType.OK);
            alert.show();
            log.error("Error Searching By ID", e);

        }
    }


}
