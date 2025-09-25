package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.ReserveStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Reserve {
    private int reserveId;
    private Date checkin;
    private Date checkout;
    private int numberOfGuests;
    private ReserveStatus status;
    private Payment payment;

    //confirm(), cancel(), update()

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }




}
