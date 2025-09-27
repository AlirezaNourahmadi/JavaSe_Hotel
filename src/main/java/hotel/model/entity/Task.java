package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Task {
    private int taskId;
    private String description;
    private LocalDate dueDate;
    private Employee assignedEmployee;
    private TaskStatus status;




    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
