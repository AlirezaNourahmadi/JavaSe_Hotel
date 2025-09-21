package hotel.model.entity;

import hotel.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Payment {
    private int receiptId;
    private double amount;
    private Date date;
    private PaymentStatus paymentStatus;

    public boolean processPayment() {
        if (this.paymentStatus == PaymentStatus.PENDING) {
            this.paymentStatus = PaymentStatus.PAID;
            return true;
        }
        return false;
    }
}
