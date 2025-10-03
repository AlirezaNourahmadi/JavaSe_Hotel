package hotel.model.repository;

import hotel.model.entity.Manager;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.ManagerMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepository implements Repository<Manager, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final ManagerMapper managerMapper = new ManagerMapper();

    public ManagerRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Manager manager) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO managers (id, first_name, last_name, email, username, password, phone, address, birth_date, person_id, employee_id, employee_name, role, salary, hire_date, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, manager.getId());
        preparedStatement.setString(2, manager.getFirstName());
        preparedStatement.setString(3, manager.getLastName());
        preparedStatement.setString(4, manager.getEmail());
        preparedStatement.setString(5, manager.getUserName());
        preparedStatement.setString(6, manager.getPassword());
        preparedStatement.setString(7, manager.getPhone());
        preparedStatement.setString(8, manager.getAddress());
        preparedStatement.setDate(9, Date.valueOf(manager.getBirthDate()));
        preparedStatement.setInt(10, manager.getPersonId().getId());
        preparedStatement.setInt(11, manager.getEmployeeId());
        preparedStatement.setString(12, manager.getEmployeeName());
        preparedStatement.setString(13, manager.getRole().name());
        preparedStatement.setDouble(14, manager.getSalary());
        preparedStatement.setDate(15, Date.valueOf(manager.getHireDate()));
        preparedStatement.setInt(16, manager.getManagerId().getId());
        preparedStatement.execute();
    }

    @Override
    public void edit(Manager manager) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE managers SET first_name=?, last_name=?, email=?, username=?, password=?, phone=?, address=?, birth_date=?, person_id=?, employee_id=?, employee_name=?, role=?, salary=?, hire_date=?, manager_id=? WHERE id=?"
        );
        preparedStatement.setString(1, manager.getFirstName());
        preparedStatement.setString(2, manager.getLastName());
        preparedStatement.setString(3, manager.getEmail());
        preparedStatement.setString(4, manager.getUserName());
        preparedStatement.setString(5, manager.getPassword());
        preparedStatement.setString(6, manager.getPhone());
        preparedStatement.setString(7, manager.getAddress());
        preparedStatement.setDate(8, Date.valueOf(manager.getBirthDate()));
        preparedStatement.setInt(9, manager.getPersonId().getId());
        preparedStatement.setInt(10, manager.getEmployeeId());
        preparedStatement.setString(11, manager.getEmployeeName());
        preparedStatement.setString(12, manager.getRole().name());
        preparedStatement.setDouble(13, manager.getSalary());
        preparedStatement.setDate(14, Date.valueOf(manager.getHireDate()));
        preparedStatement.setInt(15, manager.getManagerId().getId());
        preparedStatement.setInt(16, manager.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM managers WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Manager> findAll() throws Exception {
        List<Manager> managerList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM managers");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            managerList.add(managerMapper.managerMapper(resultSet));
        }
        return managerList;
    }

    @Override
    public Manager findById(Integer id) throws Exception {
        Manager manager = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM managers WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            manager = managerMapper.managerMapper(resultSet);
        }
        return manager;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}