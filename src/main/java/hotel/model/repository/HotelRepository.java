package hotel.model.repository;

import hotel.model.entity.Hotel;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.HotelMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class HotelRepository implements Repository<Hotel, Integer> ,AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final HotelMapper hotelMapper = new HotelMapper();

    public HotelRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }





    @Override
    public void save(Hotel hotel) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO hotels (name, branch) VALUES (?, ?)"
        );
        preparedStatement.setString(1, hotel.getName());
        preparedStatement.execute();

    }

    @Override
    public void edit(Hotel hotel) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE hotels SET name=?, branch=? WHERE id=?"
        );

    }

    @Override
    public void delete(Integer integer) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM hotels WHERE id=?"
        );
        preparedStatement.setInt(1, integer);
        preparedStatement.execute();

    }

    @Override
    public List<Hotel> findAll() throws Exception {
        List<Hotel> hotels = Collections.emptyList();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM hotels"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (hotels.isEmpty()) {
                hotels = new java.util.ArrayList<>();
            }
            hotels.add(hotelMapper.hotelMapper(resultSet));
        }
        return hotels;
    }

    @Override
    public Hotel findById(Integer integer) throws Exception {
        Hotel hotel = null;
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM hotels WHERE id=?"
        );
        preparedStatement.setInt(1, integer);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            hotel = hotelMapper.hotelMapper(resultSet);
        }
        return hotel;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
