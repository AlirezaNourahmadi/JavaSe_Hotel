package hotel.model.entity;

import com.google.gson.Gson;
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

    //processPayment()

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
