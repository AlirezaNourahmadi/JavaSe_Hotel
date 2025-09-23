package hotel.model.entity;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Guest {
    private int guestId;
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