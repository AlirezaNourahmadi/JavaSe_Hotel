package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.ReserveStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Reserve {
    private int reserveId;
    private Date checkIn;
    private Date checkOut;
    private int numberOfGuests;
    private ReserveStatus status;
    private Payment payment;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
