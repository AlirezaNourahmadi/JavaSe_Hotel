package hotel.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Property {

    private String name;
    private int quantity;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
