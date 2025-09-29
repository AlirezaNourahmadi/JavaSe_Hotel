package hotel.model.tools;

import hotel.model.entity.Guest;

import java.sql.ResultSet;

public class GuestMapper {
    public Guest guestMapper( ResultSet resultSet) throws Exception{
        return Guest
                .builder()
                .id(resultSet.getInt("guest_id"))
                .firstName(resultSet.getString("firstName"))
                .lastName(resultSet.getString("lastName"))
                .birthDate(resultSet.getDate("birthDate").toLocalDate())
                .userName(resultSet.getString("UserName"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .address(resultSet.getString("address"))
                .email(resultSet.getString("email"))
                .build();
    }
}
