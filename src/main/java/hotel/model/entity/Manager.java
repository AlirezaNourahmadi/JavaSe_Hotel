package hotel.model.entity;

import hotel.model.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Manager extends Employee{
    private String department;

    public Manager(int employeeId, String employeeName, Role role, double salary , LocalDate hireDate,Task task, String department) {
        super(employeeId, employeeName, role,salary,hireDate, (List<Task>) task);
        this.department = department;
    }

    public void assignTask(Employee employee, Task task){
        if (employee == null || task == null) return;
        employee.addTask(task);
    }
    public void manageReservation() {
        System.out.println("Manager " + getEmployeeName() + " is managing reservations...");

    }

    public void manageRooms() {
        System.out.println("Manager " + getEmployeeName() + " is managing rooms...");
    }

    @Override
    public String toString(){
        return "Manager{" +
                "id="+ getEmployeeId() +
                ", name=" + getEmployeeName()  +
                ", department= "+ getDepartment()+
                "}";

    }
}
