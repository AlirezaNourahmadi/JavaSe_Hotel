package hotel.model.tools;

import hotel.model.entity.Room;
import hotel.model.entity.enums.RoomStatus;
import hotel.model.entity.enums.RoomType;

import java.sql.ResultSet;

public class RoomMapper {
    public Room roomMapper(ResultSet resultSet) throws Exception {
        return Room
                .builder()
                .roomId(resultSet.getInt("room_id"))
                .type(RoomType.valueOf(resultSet.getString("type")))
                .status(RoomStatus.valueOf(resultSet.getString("status")))
                .pricePerNight(resultSet.getInt("price_per_night"))
                .capacity(resultSet.getInt("capacity"))
                .build();
    }
}
