package hotel.model.tools;

import hotel.model.entity.Payment;
import hotel.model.entity.enums.PaymentStatus;
import hotel.model.entity.enums.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {

    public Payment paymentMapper(ResultSet resultSet) throws SQLException {
        return Payment
                .builder()
                .receiptId(resultSet.getInt("receipt_id"))
                .amount(resultSet.getDouble("amount"))
                .date(resultSet.getDate("date"))
                .status(PaymentStatus.valueOf(resultSet.getString("status")))
                .paymentType(PaymentType.valueOf(resultSet.getString("payment_type")))
                .build();

    }
}
