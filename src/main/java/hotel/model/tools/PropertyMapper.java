package hotel.model.tools;

import hotel.model.entity.Property;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper {
    public Property propertyMapper(ResultSet resultSet) throws SQLException {
        return Property
                .builder()
                .name(resultSet.getString("name"))
                .quantity(resultSet.getInt("quantity"))
                .build();
    }
}
