package hotel.model.tools;

import hotel.model.entity.Employee;
import hotel.model.entity.Task;
import hotel.model.entity.enums.TaskStatus;

import java.sql.ResultSet;

public class TaskMapper {
    public Task taskMapper(ResultSet resultSet) throws Exception{
        return Task
                .builder()
                .taskId(resultSet.getInt("task_id"))
                .description(resultSet.getString("description"))
                .dueDate(resultSet.getDate("due_date").toLocalDate())
                .assignedEmployee(Employee.builder().id(resultSet.getInt("assigned_employee_id")).build())
                .status(TaskStatus.valueOf(resultSet.getString("status")))
                .build();

    }
}
