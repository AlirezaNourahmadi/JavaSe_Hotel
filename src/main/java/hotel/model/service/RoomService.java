package hotel.model.service;

import hotel.model.entity.Room;
import hotel.model.repository.RoomRepository;
import lombok.Getter;

import java.util.List;

public class RoomService implements Service<Room, Integer> {
    @Getter
    private static RoomService service = new RoomService();

    private RoomService() {
    }

    @Override
    public void save(Room room) throws Exception {
        try (RoomRepository roomRepository = new RoomRepository()) {
            roomRepository.save(room);
        }
    }

    @Override
    public void edit(Room room) throws Exception {
        try (RoomRepository roomRepository = new RoomRepository()) {
            roomRepository.edit(room);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (RoomRepository roomRepository = new RoomRepository()) {
            roomRepository.delete(id);
        }
    }

    @Override
    public List<Room> findAll() throws Exception {
        try (RoomRepository roomRepository = new RoomRepository()) {
            return roomRepository.findAll();
        }
    }

    @Override
    public Room findById(Integer id) throws Exception {
        try (RoomRepository roomRepository = new RoomRepository()) {
            return roomRepository.findById(id);
        }
    }
}
