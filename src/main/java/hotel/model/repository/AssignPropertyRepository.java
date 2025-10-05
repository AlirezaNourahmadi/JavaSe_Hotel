package hotel.model.repository;

import hotel.model.entity.AssignProperty;
import hotel.model.tools.AssignPropertyMapper;
import hotel.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AssignPropertyRepository implements Repository<AssignProperty,Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final AssignPropertyMapper assignPropertyMapper = new AssignPropertyMapper();

    public AssignPropertyRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(AssignProperty assignProperty) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO assign_properties (property_id, employee_id, assign_date, return_date, status) VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, assignProperty.getPropertyId());
        preparedStatement.setInt(2, assignProperty.getEmployeeId());
        preparedStatement.setDate(3, Date.valueOf(assignProperty.getAssignDate()));
        preparedStatement.setDate(4, Date.valueOf(assignProperty.getReturnDate()));
        preparedStatement.setString(5, assignProperty.getStatus().toString());
        preparedStatement.execute();
    }

    @Override
    public void edit(AssignProperty assignProperty) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE assign_properties SET property_id=?, employee_id=?, assign_date=?, return_date=?, status=? WHERE id=?"
        );
        preparedStatement.setInt(1, assignProperty.getPropertyId());
        preparedStatement.setInt(2, assignProperty.getEmployeeId());
        preparedStatement.setDate(3, Date.valueOf(assignProperty.getAssignDate()));
        preparedStatement.setDate(4, Date.valueOf(assignProperty.getReturnDate()));
        preparedStatement.setString(5, assignProperty.getStatus().toString());
        preparedStatement.setInt(6, assignProperty.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM assign_properties WHERE id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<AssignProperty> findAll() throws Exception {
        List<AssignProperty> assignPropertyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM assign_properties");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            assignPropertyList.add(assignPropertyMapper.assignPropertyMapper(resultSet));
        }
        return assignPropertyList;
    }

    @Override
    public AssignProperty findById(Integer id) throws Exception {
        AssignProperty assignProperty = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM assign_properties WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            assignProperty = assignPropertyMapper.assignPropertyMapper(resultSet);
        }
        return assignProperty;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

    public List<AssignProperty> findByEmployeeId(Integer employeeId) throws Exception {
        List<AssignProperty> assignPropertyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM assign_properties WHERE employee_id=?");
        preparedStatement.setInt(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            assignPropertyList.add(assignPropertyMapper.assignPropertyMapper(resultSet));
        }
        return assignPropertyList;
    }
}
