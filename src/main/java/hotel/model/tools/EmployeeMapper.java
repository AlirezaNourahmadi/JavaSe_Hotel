package hotel.model.tools;

import hotel.model.entity.Employee;
import hotel.model.entity.Person;
import hotel.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper {
    public Employee employeeMapper(ResultSet resultSet) throws SQLException {
        return Employee
                .builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .userName(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .address(resultSet.getString("address"))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .personId(Person.builder().id(resultSet.getInt("person_id")).build())
                .employeeId(resultSet.getInt("employee_id"))
                .employeeName(resultSet.getString("employee_name"))
                .role(Role.valueOf(resultSet.getString("role")))
                .salary(resultSet.getDouble("salary"))
                .hireDate(resultSet.getDate("hire_date").toLocalDate())
                .build();

    }
}
