package hotel.model.entity;

import hotel.model.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Task {
    private int taskId;
    private String description;
    private TaskStatus status;

    public Task(int taskId, String description, TaskStatus status) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
    }

    public void startTask(){
        this.status = TaskStatus.IN_PROGRESS;
    }
    public void endTask(){
        this.status = TaskStatus.DONE;
    }

    @Override
    public String toString(){
        return "TaskId: " + taskId + " Description: " + description + " Status: " + status;
    }
}
