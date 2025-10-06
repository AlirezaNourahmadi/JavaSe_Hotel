package hotel.controller;

import hotel.model.entity.Manager;
import hotel.model.service.ManagerService;
import lombok.Getter;

import java.util.List;

public class ManagerController {
    @Getter
    private static final ManagerController controller = new ManagerController();

    private ManagerController() {}

    public void save(Manager manager) {
        try {
            ManagerService.getService().save(manager);
            System.out.println("Manager saved successfully: " + manager);
        } catch (Exception e) {
            System.out.println("Error saving manager: " + e.getMessage());
        }
    }

    public void edit(Manager manager) {
        try {
            ManagerService.getService().edit(manager);
            System.out.println("Manager updated successfully: " + manager);
        } catch (Exception e) {
            System.out.println("Error updating manager: " + e.getMessage());
        }
    }

    public void delete(int managerId) {
        try {
            ManagerService.getService().delete(managerId);
            System.out.println("Manager deleted with ID: " + managerId);
        } catch (Exception e) {
            System.out.println("Error deleting manager: " + e.getMessage());
        }
    }

    public List<Manager> findAll() {
        try {
            return ManagerService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error fetching managers: " + e.getMessage());
            return null;
        }
    }

    public Manager findById(int managerId) {
        try {
            return ManagerService.getService().findById(managerId);
        } catch (Exception e) {
            System.out.println("Error finding manager: " + e.getMessage());
            return null;
        }
    }
}