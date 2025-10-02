package hotel.model.repository;

import hotel.model.entity.Room;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.RoomMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements Repository<Room, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final RoomMapper roomMapper = new RoomMapper();

    public RoomRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Room room) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO rooms (room_id, type, status, price_per_night, capacity) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, room.getRoomId());
        preparedStatement.setString(2, room.getType().name());
        preparedStatement.setString(3, room.getStatus().name());
        preparedStatement.setDouble(4, room.getPricePerNight());
        preparedStatement.setInt(5, room.getCapacity());
        preparedStatement.execute();
    }

    @Override
    public void edit(Room room) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE rooms SET type=?, status=?, price_per_night=?, capacity=? WHERE room_id=?"
        );
        preparedStatement.setString(1, room.getType().name());
        preparedStatement.setString(2, room.getStatus().name());
        preparedStatement.setDouble(3, room.getPricePerNight());
        preparedStatement.setInt(4, room.getCapacity());
        preparedStatement.setInt(5, room.getRoomId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM rooms WHERE room_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Room> findAll() throws Exception {
        List<Room> roomList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM rooms");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            roomList.add(roomMapper.roomMapper(resultSet));
        }
        return roomList;
    }

    @Override
    public Room findById(Integer id) throws Exception {
        Room room = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM rooms WHERE room_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            room = roomMapper.roomMapper(resultSet);
        }
        return room;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}
