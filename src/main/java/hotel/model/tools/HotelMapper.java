package hotel.model.tools;

import hotel.model.entity.Branch;
import hotel.model.entity.Hotel;

import java.sql.ResultSet;
import java.util.Collections;


public class HotelMapper {
    public Hotel hotelMapper(ResultSet resultSet) throws Exception {
        return Hotel
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .branch((Branch) Collections.singletonList(
                        Branch.builder()
                                .branchId(resultSet.getInt("branch_id"))
                                .address(resultSet.getString("address"))
                                .build()
                ))
                .build();
    }
}
