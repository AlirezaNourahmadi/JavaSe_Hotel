package hotel.controller;

import hotel.model.entity.Task;
import hotel.model.service.TaskService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField descriptionField; // Example field


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void saveTask() {
        try {
            Task task = new Task();
            task.setTaskId(Integer.parseInt(idField.getText()));
            TaskService.getService().save(task);
            showAlert("Success", "Task saved successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error saving task: " + e.getMessage());
        }
    }

    @FXML
    private void editTask() {
        try {
            Task task = new Task();
            task.setTaskId(Integer.parseInt(idField.getText()));
            TaskService.getService().edit(task);
            showAlert("Success", "Task updated successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error updating task: " + e.getMessage());
        }
    }

    @FXML
    private void deleteTask() {
        try {
            int id = Integer.parseInt(idField.getText());
            TaskService.getService().delete(id);
            showAlert("Success", "Task deleted successfully.");
        } catch (Exception e) {
            showAlert("Error", "Error deleting task: " + e.getMessage());
        }
    }

    @FXML
    private void loadTask() {
        try {
            int id = Integer.parseInt(idField.getText());
            Task task = TaskService.getService().findById(id);
            if (task != null) {
            } else {
                showAlert("Info", "Task not found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading task: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}