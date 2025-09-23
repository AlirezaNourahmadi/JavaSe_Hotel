package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class Reserve {
    private int reserveId;
    private Date checkin;
    private Date checkout;
    private int numberOfGuests;
    private ReservationStatus status;
    private Payment payment;

    //confirm(), cancel(), update()

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }




}
