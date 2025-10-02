package hotel.model.repository;

import hotel.model.entity.Guest;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.GuestMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository implements Repository<Guest, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final GuestMapper guestMapper = new GuestMapper();

    public GuestRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Guest guest) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO guests (id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, guest.getId());
        preparedStatement.setString(2, guest.getFirstName());
        preparedStatement.setString(3, guest.getLastName());
        preparedStatement.setString(4, guest.getEmail());
        preparedStatement.setString(5, guest.getPhone());
        preparedStatement.execute();
    }

    @Override
    public void edit(Guest guest) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE guests SET first_name=?, last_name=?, email=?, phone=? WHERE id=?"
        );
        preparedStatement.setString(1, guest.getFirstName());
        preparedStatement.setString(2, guest.getLastName());
        preparedStatement.setString(3, guest.getEmail());
        preparedStatement.setString(4, guest.getPhone());
        preparedStatement.setInt(5, guest.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM guests WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Guest> findAll() throws Exception {
        List<Guest> guestList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM guests");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            guestList.add(guestMapper.guestMapper(resultSet));
        }
        return guestList;
    }

    @Override
    public Guest findById(Integer id) throws Exception {
        Guest guest = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM guests WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            guest = guestMapper.guestMapper(resultSet);
        }
        return guest;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}
