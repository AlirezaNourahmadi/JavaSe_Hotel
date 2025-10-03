package hotel.model.repository;

import hotel.model.entity.Payment;
import hotel.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;

    public PaymentRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (id, amount, method, date) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, payment.getAmount());
        preparedStatement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
        preparedStatement.execute();
    }

    public void edit(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET amount = ?, date = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, payment.getAmount());
        preparedStatement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM payments WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Payment> findAll() throws SQLException {
        String sql = "SELECT * FROM payments";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            paymentList.add(payment);
        }
        return paymentList;
    }

    public Payment findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Payment payment = new Payment();
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            return payment;
        }
        return null;
    }
}
