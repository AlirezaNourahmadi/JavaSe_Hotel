package hotel.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Branch {
    private int branchId;
    private String address;
    private List<Employee> employeeList;
    private List<Room> roomList;

    public int getEmployeeCount() {
        return employeeList == null ? 0 : employeeList.size();
    }
    public int getRoomCount() {
        return roomList == null ? 0 : roomList.size();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
