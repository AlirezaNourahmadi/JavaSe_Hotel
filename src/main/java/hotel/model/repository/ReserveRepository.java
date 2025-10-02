package hotel.model.repository;

import hotel.model.entity.Reserve;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.ReserveMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReserveRepository implements Repository<Reserve, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final ReserveMapper reserveMapper = new ReserveMapper();

    public ReserveRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Reserve reserve) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO reserves (reserve_id, checkin, checkout, number_of_guests, status, payment)" +
                        " VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, reserve.getReserveId());
        preparedStatement.setDate(2, new Date(reserve.getCheckIn().getTime()));
        preparedStatement.setDate(3, new Date(reserve.getCheckOut().getTime()));
        preparedStatement.setInt(4, reserve.getNumberOfGuests());
        preparedStatement.setString(5, reserve.getStatus().name()); // Enum -> String
        preparedStatement.setDouble(6, reserve.getPayment().getAmount()); // Payment -> amount
        preparedStatement.execute();
    }

    @Override
    public void edit(Reserve reserve) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE reserves SET checkin=?, checkout=?, number_of_guests=?, status=?, payment=? WHERE reserve_id=?"
        );
        preparedStatement.setDate(1, new Date(reserve.getCheckIn().getTime()));
        preparedStatement.setDate(2, new Date(reserve.getCheckOut().getTime()));
        preparedStatement.setInt(3, reserve.getNumberOfGuests());
        preparedStatement.setString(4, reserve.getStatus().name()); // Enum -> String
        preparedStatement.setDouble(5, reserve.getPayment().getAmount()); // Payment -> amount
        preparedStatement.setInt(6, reserve.getReserveId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM reserves WHERE reserve_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        List<Reserve> reserveList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM reserves");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            reserveList.add(reserveMapper.reserveMapper(resultSet));
        }
        return reserveList;
    }

    @Override
    public Reserve findById(Integer id) throws Exception {
        Reserve reserve = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM reserves WHERE reserve_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            reserve = reserveMapper.reserveMapper(resultSet);
        }
        return reserve;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}
