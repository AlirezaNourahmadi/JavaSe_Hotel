package hotel.model.entity;

import hotel.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Employee {
    private int employeeId;
    private String employeeName;
    private Role role;
    private List<Task>  tasks = new ArrayList<>();


    public Employee(int employeeId, String employeeName, Role role) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
    }

    public void addTask(Task task) {
        if (this.tasks == null) {
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(task);
    }

    @Override
    public String toString(){
        return "Employee Id: " + employeeId + " Employee: " + employeeName + " Role: " + role;

    }
}
