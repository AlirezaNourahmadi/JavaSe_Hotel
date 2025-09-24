package hotel.model.tools;

import hotel.model.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper {
    public Person personMapper(ResultSet resultSet) throws SQLException {
        return  Person
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
                .build();
    }
}