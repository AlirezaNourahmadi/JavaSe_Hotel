package hotel.model.entity;

import hotel.model.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Task {
    private int taskId;
    private String description;
    private LocalDate dueDate;
    private Employee assignedEmployee;
    private TaskStatus status;

    public Task(int taskId, String description, TaskStatus status, LocalDate dueDate, Employee assignedEmployee) {

        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.assignedEmployee = assignedEmployee;
    }

    public void startTask(){
        this.status = TaskStatus.IN_PROGRESS;
    }
    public void endTask(){
        this.status = TaskStatus.DONE;
    }

    @Override
    public String toString(){
        return "TaskId: " + taskId + " Description: " + description + " Status: " + status + " Due Date: " + dueDate + " Assigned Employee: " + assignedEmployee;
    }
}
