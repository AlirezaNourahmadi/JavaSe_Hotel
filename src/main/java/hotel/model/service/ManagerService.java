package hotel.model.service;

import hotel.model.entity.Manager;
import hotel.model.repository.ManagerRepository;
import lombok.Getter;

import java.util.List;

public class ManagerService implements Service<Manager, Integer> {
    @Getter
    private static ManagerService service = new ManagerService();

    private ManagerService() {
    }

    @Override
    public void save(Manager manager) throws Exception {
        try (ManagerRepository managerRepository = new ManagerRepository()) {
            managerRepository.save(manager);
        }
    }

    @Override
    public void edit(Manager manager) throws Exception {
        try (ManagerRepository managerRepository = new ManagerRepository()) {
            managerRepository.edit(manager);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ManagerRepository managerRepository = new ManagerRepository()) {
            managerRepository.delete(id);
        }
    }

    @Override
    public List<Manager> findAll() throws Exception {
        try (ManagerRepository managerRepository = new ManagerRepository()) {
            return managerRepository.findAll();
        }
    }

    @Override
    public Manager findById(Integer id) throws Exception {
        try (ManagerRepository managerRepository = new ManagerRepository()) {
            return managerRepository.findById(id);
        }
    }
}