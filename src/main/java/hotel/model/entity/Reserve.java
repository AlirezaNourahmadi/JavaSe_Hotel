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

    public void confirm() { this.status = ReserveStatus.CONFIRMED; }
    public void cancel() { this.status = ReserveStatus.CANCELED; }
    public void update() { this.status = ReserveStatus.UPDATED; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
