package hotel.model.tools;

import hotel.model.entity.Reserve;
import hotel.model.entity.enums.ReserveStatus;

import java.sql.ResultSet;
public class ReserveMapper {
    public Reserve reserveMapper(ResultSet resultSet) throws Exception {
        return Reserve
                .builder()
                .reserveId(resultSet.getInt("reserveId"))
                .checkIn(resultSet.getDate("checkIn"))
                .checkOut(resultSet.getDate("checkOut"))
                .numberOfGuests(resultSet.getInt("numberOfGuests"))
                .status(ReserveStatus.valueOf(resultSet.getString("status")))
                .build();
    }
}
