package hotel.model.entity;

import hotel.model.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Employee {
    private int employeeId;
    private String employeeName;
    private Role role;
    private double salary;
    private LocalDate hireDate;
    private List<Task>  tasks = new ArrayList<>();





    public Employee(int employeeId, String employeeName, Role role, double salary, LocalDate hireDate, List<Task> tasks) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
        this.hireDate = hireDate;
        this.tasks = tasks;
    }


    public void addTask(Task task) {
        if (this.tasks == null) {
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(task);
    }

    public void performTask(Task task) {
        if (tasks.contains(task)) {
            task.startTask();
            System.out.println(employeeName + "started task: " + task.getDescription());
        }else{
            System.out.println(employeeName + " has not been assigned task: ");
        }
    }


    @Override
    public String toString(){
        return "Employee Id: " + employeeId + " Employee: " + employeeName + " Role: " + role + " Salary: " + salary + " Hire Date: " + hireDate;

    }
}
