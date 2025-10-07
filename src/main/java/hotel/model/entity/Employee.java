package hotel.model.entity;

import hotel.model.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Employee extends Person {
    private Person personId;
    private int employeeId;
    private String employeeName;
    private Role role;
    private double salary;
    private LocalDate hireDate;
    private List<Task>  tasks = new ArrayList<>();
    private Branch branch;






}
