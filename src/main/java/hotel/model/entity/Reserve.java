package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.ReserveStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Reserve {
    private int reserveId;
    private String checkin;
    private String checkout;
    private int numberOfGuests;
    private ReserveStatus status;
    private Payment payment;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
