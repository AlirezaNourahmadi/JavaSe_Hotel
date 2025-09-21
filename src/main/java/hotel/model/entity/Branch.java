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
    private List<Room> roomList;
    private List<Employee> employeeList;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);


    }

}
