package hotel.model.tools;

import hotel.model.entity.Guest;

import java.sql.ResultSet;

public class GuestMapper {
    public Guest guestMapper( ResultSet resultSet) throws Exception{
        return Guest
                .builder()
                .id(resultSet.getInt("guest_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .userName(resultSet.getString("User_name"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .address(resultSet.getString("address"))
                .email(resultSet.getString("email"))
                .build();
    }
}
