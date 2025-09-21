package hotel.model.entity;

import com.google.gson.Gson;
import lombok.*;


import java.util.List;




@Getter
@Setter
@NoArgsConstructor
public class Hotel {
    private String name;
    private List<Branch> branchList;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
