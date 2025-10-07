package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.entity.Task;
import hotel.model.entity.enums.TaskStatus;
import hotel.model.service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    private TextField taskIdText, descriptionText, assignedEmployeeIdText, statusText;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private TextField findByDescriptionText, findByIdText;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn, statusColumn;
    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;
    @FXML
    private Button saveButton, editButton, deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }

        saveButton.setOnAction(e -> saveTask());
        editButton.setOnAction(e -> editTask());
        deleteButton.setOnAction(e -> deleteTask());

        findByDescriptionText.setOnKeyReleased(e -> searchByDescription());
        findByIdText.setOnKeyReleased(e -> searchById());

        taskTable.setOnMouseClicked(e -> selectFromTable());
        taskTable.setOnKeyReleased(e -> selectFromTable());
    }

    private void resetForm() throws Exception {
        taskIdText.clear();
        descriptionText.clear();
        dueDatePicker.setValue(null);
        assignedEmployeeIdText.clear();
        statusText.clear();

        showDataOnTable(TaskService.getService().findAll());
    }

    private void showDataOnTable(List<Task> taskList) {
        ObservableList<Task> observableList = FXCollections.observableList(taskList);

        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        taskTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Task task = taskTable.getSelectionModel().getSelectedItem();
            if (task != null) {
                taskIdText.setText(String.valueOf(task.getTaskId()));
                descriptionText.setText(task.getDescription());
                dueDatePicker.setValue(task.getDueDate());
                assignedEmployeeIdText.setText(String.valueOf(task.getAssignedEmployee().getId()));
                statusText.setText(task.getStatus().name());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Loading Data !!!");
        }
    }

    private void saveTask() {
        try {
            Task task = Task.builder()
                    .description(descriptionText.getText())
                    .dueDate(dueDatePicker.getValue())
                    .assignedEmployee(Employee.builder().id(Integer.parseInt(assignedEmployeeIdText.getText())).build())
                    .status(TaskStatus.valueOf(statusText.getText().toUpperCase()))
                    .build();
            TaskService.getService().save(task);
            showAlert(Alert.AlertType.INFORMATION, "Task Saved Successfully\n" + task);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Task Save Failed: " + e.getMessage());
        }
    }

    private void editTask() {
        try {
            Task task = Task.builder()
                    .taskId(Integer.parseInt(taskIdText.getText()))
                    .description(descriptionText.getText())
                    .dueDate(dueDatePicker.getValue())
                    .assignedEmployee(Employee.builder().id(Integer.parseInt(assignedEmployeeIdText.getText())).build())
                    .status(TaskStatus.valueOf(statusText.getText().toUpperCase()))
                    .build();
            TaskService.getService().edit(task);
            showAlert(Alert.AlertType.INFORMATION, "Task Edited Successfully\n" + task);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Task Edit Failed: " + e.getMessage());
        }
    }

    private void deleteTask() {
        try {
            int id = Integer.parseInt(taskIdText.getText());
            TaskService.getService().delete(id);
            showAlert(Alert.AlertType.INFORMATION, "Task Deleted Successfully\nID: " + id);
            resetForm();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Task Delete Failed: " + e.getMessage());
        }
    }

    private void searchByDescription() {
        try {
            List<Task> filtered = TaskService.getService().findAll();
            if (!findByDescriptionText.getText().isEmpty()) {
                filtered.removeIf(t -> !t.getDescription().toLowerCase().contains(findByDescriptionText.getText().toLowerCase()));
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
                Task task = TaskService.getService().findById(id);
                showDataOnTable(task != null ?
                        java.util.Collections.singletonList(task) :
                        new java.util.ArrayList<Task>());
            } else {
                showDataOnTable(TaskService.getService().findAll());
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