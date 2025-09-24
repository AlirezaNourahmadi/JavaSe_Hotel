package hotel.model.entity;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Guest {
    private Person guestId;
    private String name;
    private String phone;
    private List<Reserve> reserves;
    private List<Payment> payments;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}