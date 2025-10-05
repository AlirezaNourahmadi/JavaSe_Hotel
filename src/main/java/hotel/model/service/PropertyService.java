package hotel.model.service;

import hotel.model.entity.Property;
import hotel.model.repository.PropertyRepository;
import lombok.Getter;

import java.util.List;

public final class PropertyService {
    @Getter
    private static PropertyService service = new PropertyService();

    private PropertyService() {}

    public void save(Property property) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.save(property);
        }
    }

    public void update(Property property) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.edit(property);
        }
    }

    public void delete(Integer id) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.delete(id);
        }
    }

    public void edit(Property property) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.edit(property);
        }
    }

    public Property findById(Integer id) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findById(id);
        }
    }

    public List<Property> findAll() throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findAll();
        }
    }

    public List<Property> findByName(String name) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findByName(name);
        }
    }
}
