package hotel.model.entity;

import hotel.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Manager extends Employee{
    private String department;

    public Manager(int employeeId, String employeeName, Role role, String department) {
        super(employeeId, employeeName, role);
        this.department = department;
    }

    public void assignTask(Employee employee, Task task){
        if (employee == null || task == null) return;
        employee.addTask(task);
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
