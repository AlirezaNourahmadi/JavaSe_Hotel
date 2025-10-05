package hotel.model.repository;

import hotel.model.entity.Property;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.PropertyMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository implements Repository<Property, String>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final PropertyMapper propertyMapper = new PropertyMapper();

    public PropertyRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO properties (name, quantity) VALUES (?, ?)"
        );
        preparedStatement.setString(1, property.getName());
        preparedStatement.setInt(2, property.getQuantity());
        preparedStatement.execute();
    }

    @Override
    public void edit(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE properties SET quantity=? WHERE name=?"
        );
        preparedStatement.setInt(1, property.getQuantity());
        preparedStatement.setString(2, property.getName());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(String name) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM properties WHERE name=?"
        );
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }

    @Override
    public List<Property> findAll() throws Exception {
        List<Property> propertyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM properties");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            propertyList.add(propertyMapper.propertyMapper(resultSet));
        }
        return propertyList;
    }

    @Override
    public Property findById(String name) throws Exception {
        Property property = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM properties WHERE name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            property = propertyMapper.propertyMapper(resultSet);
        }
        return property;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
