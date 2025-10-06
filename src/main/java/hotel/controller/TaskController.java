package hotel.controller;

import hotel.model.entity.Task;
import hotel.model.service.TaskService;
import lombok.Getter;

import java.util.List;

public class TaskController {
    @Getter
    private static final TaskController controller = new TaskController();

    private TaskController() {}

    public void save(Task task) {
        try {
            TaskService.getService().save(task);
            System.out.println("Task saved successfully: " + task);
        } catch (Exception e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void edit(Task task) {
        try {
            TaskService.getService().edit(task);
            System.out.println("Task updated successfully: " + task);
        } catch (Exception e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    public void delete(int taskId) {
        try {
            TaskService.getService().delete(taskId);
            System.out.println("Task deleted with ID: " + taskId);
        } catch (Exception e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }

    public List<Task> findAll() {
        try {
            return TaskService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching tasks: " + e.getMessage());
            return null;
        }
    }

    public Task findById(int taskId) {
        try {
            return TaskService.getService().findById(taskId);
        } catch (Exception e) {
            System.out.println("Error finding task: " + e.getMessage());
            return null;
        }
    }
}