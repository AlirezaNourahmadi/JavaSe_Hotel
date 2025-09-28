package hotel.model.entity;

import com.google.gson.Gson;
import hotel.model.entity.enums.PaymentStatus;
import hotel.model.entity.enums.PaymentType;
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
    private PaymentStatus status;
    private PaymentType paymentType;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
