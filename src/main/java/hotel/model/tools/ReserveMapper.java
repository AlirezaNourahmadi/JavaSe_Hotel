package hotel.model.tools;

import hotel.model.entity.Reserve;
import hotel.model.entity.enums.ReserveStatus;

import java.sql.ResultSet;
public class ReserveMapper {
    public Reserve reserveMapper(ResultSet resultSet) throws Exception {
        return Reserve
                .builder()

                .reserveId(resultSet.getInt("reserve_id"))
                .checkIn(resultSet.getDate("check_in"))
                .checkOut(resultSet.getDate("check_out"))
                .numberOfGuests(resultSet.getInt("number_of_guests"))
                .status(ReserveStatus.valueOf(resultSet.getString("status")))
                .build();
    }
}
