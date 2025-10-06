package hotel.controller;


import hotel.model.entity.Reserve;
import hotel.model.service.ReserveService;
import lombok.Getter;

import java.util.List;

public class ReserveController {
    @Getter
    private static final ReserveController controller = new ReserveController();

    private ReserveController() {}

    public void save(Reserve reserve) {
        try {
            ReserveService.getService().save(reserve);
            System.out.println("Reserve saved successfully: " + reserve);
        } catch (Exception e) {
            System.out.println("Error saving reserve: " + e.getMessage());
        }
    }

    public void edit(Reserve reserve) {
        try {
            ReserveService.getService().edit(reserve);
            System.out.println("Reserve updated successfully: " + reserve);
        } catch (Exception e) {
            System.out.println("Error updating reserve: " + e.getMessage());
        }
    }

    public void delete(int reserveId) {
        try {
            ReserveService.getService().delete(reserveId);
            System.out.println("Reserve deleted with ID: " + reserveId);
        } catch (Exception e) {
            System.out.println("Error deleting reserve: " + e.getMessage());
        }
    }

    public List<Reserve> findAll() {
        try {
            return ReserveService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching reserves: " + e.getMessage());
            return null;
        }
    }

    public Reserve findById(int reserveId) {
        try {
            return ReserveService.getService().findById(reserveId);
        } catch (Exception e) {
            System.out.println("Error finding reserve: " + e.getMessage());
            return null;
        }
    }
}

