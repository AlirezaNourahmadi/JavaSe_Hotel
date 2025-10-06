package hotel.controller;

import hotel.model.entity.Room;
import hotel.model.service.RoomService;
import lombok.Getter;

import java.util.List;

public class RoomController {
    @Getter
    private static final RoomController controller = new RoomController();

    private RoomController() {}

    public void save(Room room) {
        try {
            RoomService.getService().save(room);
            System.out.println("Room saved successfully: " + room);
        } catch (Exception e) {
            System.out.println("Error saving room: " + e.getMessage());
        }
    }

    public void edit(Room room) {
        try {
            RoomService.getService().edit(room);
            System.out.println("Room updated successfully: " + room);
        } catch (Exception e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
    }

    public void delete(int roomId) {
        try {
            RoomService.getService().delete(roomId);
            System.out.println("Room deleted with ID: " + roomId);
        } catch (Exception e) {
            System.out.println("Error deleting room: " + e.getMessage());
        }
    }

    public List<Room> findAll() {
        try {
            return RoomService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
            return null;
        }
    }

    public Room findById(int roomId) {
        try {
            return RoomService.getService().findById(roomId);
        } catch (Exception e) {
            System.out.println("Error finding room: " + e.getMessage());
            return null;
        }
    }
}
