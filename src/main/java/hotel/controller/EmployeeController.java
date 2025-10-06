package hotel.controller;

import hotel.model.entity.Employee;
import hotel.model.service.EmployeeService;
import lombok.Getter;

import java.util.List;

public class EmployeeController {
    @Getter
    private static final EmployeeController controller = new EmployeeController();

    private EmployeeController() {}

    public void save(Employee employee) {
        try {
            EmployeeService.getService().save(employee);
            System.out.println("Employee saved successfully: " + employee);
        } catch (Exception e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    public void edit(Employee employee) {
        try {
            EmployeeService.getService().edit(employee);
            System.out.println("Employee updated successfully: " + employee);
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    public void delete(int employeeId) {
        try {
            EmployeeService.getService().delete(employeeId);
            System.out.println("Employee deleted with ID: " + employeeId);
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    public List<Employee> findAll() {
        try {
            return EmployeeService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching employees: " + e.getMessage());
            return null;
        }
    }

    public Employee findById(int employeeId) {
        try {
            return EmployeeService.getService().findById(employeeId);
        } catch (Exception e) {
            System.out.println("Error finding employee: " + e.getMessage());
            return null;
        }
    }
}