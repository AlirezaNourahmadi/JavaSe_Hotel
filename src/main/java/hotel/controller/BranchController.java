package hotel.controller;


import hotel.model.entity.Branch;
import hotel.model.service.BranchService;
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
public class BranchController implements Initializable {


    @FXML
    private TextField idText, addressText, employeeText, roomText, searchIdText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Branch> branchTable;

    @FXML
    private TableColumn<Branch, Integer> idColumn;

    @FXML
    private TableColumn<Branch, String> addressColumn, employeeColumn, roomColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Branch Data!!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Branch branch =
                        Branch
                                .builder()
                                .address(addressText.getText())
                                .employeeList(new java.util.ArrayList<>())
                                .roomList(new java.util.ArrayList<>())
                                .build();
                Branch savedBranch = hotel.model.service.BranchService.getService().save(branch);
                log.info("Branch saved: {}", savedBranch);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Branch saved successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error saving branch", e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving branch!", ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Branch branch =
                        Branch
                                .builder()
                                .branchId(Integer.parseInt(idText.getText()))
                                .address(addressText.getText())
                                .employeeList(new java.util.ArrayList<>())
                                .roomList(new java.util.ArrayList<>())
                                .build();
                Branch updatedBranch = BranchService.getService().update(branch);
                log.info("Branch updated: {}", updatedBranch);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Branch updated successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error updating branch", e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating branch!", ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction(event -> {
            try {
                int branchId = Integer.parseInt(searchIdText.getText());
                BranchService.getService().delete(branchId);
                log.info("Branch deleted with ID: {}", branchId);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Branch deleted successfully!", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error deleting branch", e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting branch!", ButtonType.OK);
                alert.show();
            }
        });

        searchIdText.setOnKeyReleased(event -> {
            try {
                searchById();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        branchTable.setOnMouseClicked(event -> selectFromTable());
        branchTable.setOnKeyReleased(event -> selectFromTable());

    }
    private void resetForm() throws Exception {
        idText.clear();
        addressText.clear();
        employeeText.clear();
        roomText.clear();
        searchIdText.clear();

        showDateOnTable(BranchService.getService().findAll());
    }

    private void showDateOnTable(List<Branch> branchList) {
        ObservableList<Branch> observableList = FXCollections.observableList(branchList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));

        branchTable.setItems(observableList);

    }
    public void selectFromTable() {
        try {


            Branch branch = branchTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(branch.getBranchId()));
            addressText.setText(branch.getAddress());
            employeeText.setText(String.valueOf(branch.getEmployeeList().size()));
            roomText.setText(String.valueOf(branch.getRoomList().size()));
        } catch (Exception e) {
            log.error("Error selecting branch from table", e);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error selecting branch!", ButtonType.OK);
            alert.show();
        }


    }
    public void searchById() {
        try {
            showDateOnTable(BranchService.getService().findAll());
            log.info("Searching by ID: {}", searchIdText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error searching branch by ID!", ButtonType.OK);
            alert.show();
            log.error("Error searching branch by ID", e);

        }
    }
}
