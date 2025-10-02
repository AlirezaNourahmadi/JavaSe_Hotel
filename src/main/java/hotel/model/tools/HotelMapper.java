package hotel.model.tools;

import hotel.model.entity.Branch;
import hotel.model.entity.Hotel;

import java.sql.ResultSet;
import java.util.List;

public class HotelMapper {
    public Hotel hotelMapper(ResultSet resultSet) throws Exception {
        return Hotel
                .builder()
                .name(resultSet.getString("name"))
                .build();
    }
}
