package hotel.model.entity;

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

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELED;
    }

    public void update() {
        this.status = ReservationStatus.UPDATED;
    }
}
