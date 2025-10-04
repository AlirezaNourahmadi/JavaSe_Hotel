package hotel.model.tools;

import hotel.model.entity.Branch;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BranchMapper {
    public Branch branchMapper(ResultSet resultSet) throws Exception {
        return Branch
                .builder()
                .branchId(resultSet.getInt("branch_id"))
                .address(resultSet.getString("address"))
                .roomList(resultSet.getObject("room_list", ArrayList.class))
                .employeeList(resultSet.getObject("employee_list", ArrayList.class))
                .build();
    }
    
}
