package hotel.controller.api;


import hotel.model.entity.Guest;
import hotel.model.service.GuestService;
import java.util.List;

public class GuestController {
    private static final GuestController controller = new GuestController();

    private GuestController() {}

    public static GuestController getController() {
        return controller;
    }

    public void save(Guest guest) {
        try {
            GuestService.getService().save(guest);
            System.out.println("Guest saved successfully: " + guest);
        } catch (Exception e) {
            System.out.println("Error saving guest: " + e.getMessage());
        }
    }

    public void edit(Guest guest) {
        try {
            GuestService.getService().edit(guest);
            System.out.println("Guest updated successfully: " + guest);
        } catch (Exception e) {
            System.out.println("Error updating guest: " + e.getMessage());
        }
    }

    public void delete(int guestId) {
        try {
            GuestService.getService().delete(guestId);
            System.out.println("Guest deleted with ID: " + guestId);
        } catch (Exception e) {
            System.out.println("Error deleting guest: " + e.getMessage());
        }
    }

    public List<Guest> findAll() {
        try {
            return GuestService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching guests: " + e.getMessage());
            return null;
        }
    }

    public Guest findById(int guestId) {
        try {
            return GuestService.getService().findById(guestId);
        } catch (Exception e) {
            System.out.println("Error finding guest: " + e.getMessage());
            return null;
        }
    }
}
