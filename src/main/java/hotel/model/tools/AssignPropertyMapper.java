package hotel.model.tools;

import hotel.model.entity.AssignProperty;
import hotel.model.entity.Employee;
import hotel.model.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class AssignPropertyMapper {
    public AssignProperty assignPropertyMapper(ResultSet resultSet) throws SQLException {
        return AssignProperty
                .builder()
                .propertyId(resultSet.getInt("property_id"))
                .quantity(resultSet.getInt("quantity"))
                .room(
                        Room.builder()
                                .roomId(resultSet.getInt("room_id"))
                                .build()

                )
                .assignedBy(Collections.singletonList(
                        Employee.builder()
                                .employeeId(resultSet.getInt("employee_id"))
                                .build()
                ))
                .build();
    }
}
