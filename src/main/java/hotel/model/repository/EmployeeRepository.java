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
            "INSERT INTO employees (first_name, last_name, phone, email, branch_id) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getPhone());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setInt(5, employee.getBranch().getBranchId());
        preparedStatement.execute();
    }

    @Override
    public void edit(Employee employee) throws Exception {
        preparedStatement = connection.prepareStatement(
            "UPDATE employees SET first_name=?, last_name=?, phone=?, email=?, branch_id=? WHERE employee_id=?"
        );
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getPhone());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setInt(5, employee.getBranch().getBranchId());
        preparedStatement.setInt(6, employee.getEmployeeId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE employee_id=?");
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
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            employee = employeeMapper.employeeMapper(resultSet);
        }
        return employee;
    }

    public List<Employee> findByBranchId(int branchId) throws Exception {
        List<Employee> employees = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
            "SELECT * FROM employees WHERE branch_id=?"
        );
        preparedStatement.setInt(1, branchId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            employees.add(employeeMapper.employeeMapper(resultSet));
        }
        return employees;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}