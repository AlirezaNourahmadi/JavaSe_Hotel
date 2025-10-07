package hotel.model.service;

import hotel.model.entity.AssignProperty;
import hotel.model.repository.AssignPropertyRepository;
import lombok.Getter;

import java.util.List;

public final class AssignPropertyService {
    @Getter
    private static AssignPropertyService service = new AssignPropertyService();

    private AssignPropertyService() {}

    public void save(AssignProperty assignProperty) throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.save(assignProperty);
        }
    }

    public void update(AssignProperty assignProperty) throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.edit(assignProperty);
        }
    }

    public void delete(Integer id) throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.delete(id);
        }
    }

    public void edit(AssignProperty assignProperty) throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.edit(assignProperty);
        }
    }

    public List<AssignProperty> findById(Integer id) throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.findById(id);
        }
        return null;
    }

    public List<AssignProperty> findAll() throws Exception {
        try(AssignPropertyRepository assignPropertyRepository = new AssignPropertyRepository()) {
            assignPropertyRepository.findAll();
        }
        return null;
    }
}
