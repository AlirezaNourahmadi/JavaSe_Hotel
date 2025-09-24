package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
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
