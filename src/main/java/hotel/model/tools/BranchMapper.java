package hotel.model.tools;

import hotel.model.entity.Branch;
import hotel.model.entity.Employee;
import hotel.model.entity.Room;
import hotel.model.entity.enums.RoomStatus;
import hotel.model.entity.enums.RoomType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class BranchMapper {
    public Branch branchMapper(ResultSet resultSet) throws Exception {
        return Branch
                .builder()
                .branchId(resultSet.getInt("branch_id"))
                .address(resultSet.getString("address"))
                .roomList(new ArrayList<>())
                .employeeList(new ArrayList<>())
                .build();
    }


}

