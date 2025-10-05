package hotel.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;




@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Hotel {
    private int id;
    private String name;
    private Branch branch;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
