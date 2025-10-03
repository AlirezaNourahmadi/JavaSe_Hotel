package hotel.model.repository;

import hotel.model.entity.Employee;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.EmployeeMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    public EmployeeRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Employee employee) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO employees (id, first_name, last_name, email, username, password, phone, address, birth_date, person_id, employee_id, employee_name, role, salary, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setString(2, employee.getFirstName());
        preparedStatement.setString(3, employee.getLastName());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, employee.getUserName());
        preparedStatement.setString(6, employee.getPassword());
        preparedStatement.setString(7, employee.getPhone());
        preparedStatement.setString(8, employee.getAddress());
        preparedStatement.setDate(9, Date.valueOf(employee.getBirthDate()));
        preparedStatement.setInt(10, employee.getPersonId().getId());
        preparedStatement.setInt(11, employee.getEmployeeId());
        preparedStatement.setString(12, employee.getEmployeeName());
        preparedStatement.setString(13, employee.getRole().name());
        preparedStatement.setDouble(14, employee.getSalary());
        preparedStatement.setDate(15, Date.valueOf(employee.getHireDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Employee employee) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE employees SET first_name=?, last_name=?, email=?, username=?, password=?, phone=?, address=?, birth_date=?, person_id=?, employee_id=?, employee_name=?, role=?, salary=?, hire_date=? WHERE id=?"
        );
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setString(4, employee.getUserName());
        preparedStatement.setString(5, employee.getPassword());
        preparedStatement.setString(6, employee.getPhone());
        preparedStatement.setString(7, employee.getAddress());
        preparedStatement.setDate(8, Date.valueOf(employee.getBirthDate()));
        preparedStatement.setInt(9, employee.getPersonId().getId());
        preparedStatement.setInt(10, employee.getEmployeeId());
        preparedStatement.setString(11, employee.getEmployeeName());
        preparedStatement.setString(12, employee.getRole().name());
        preparedStatement.setDouble(13, employee.getSalary());
        preparedStatement.setDate(14, Date.valueOf(employee.getHireDate()));
        preparedStatement.setInt(15, employee.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM employees");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            employeeList.add(employeeMapper.employeeMapper(resultSet));
        }
        return employeeList;
    }

    @Override
    public Employee findById(Integer id) throws Exception {
        Employee employee = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            employee = employeeMapper.employeeMapper(resultSet);
        }
        return employee;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}