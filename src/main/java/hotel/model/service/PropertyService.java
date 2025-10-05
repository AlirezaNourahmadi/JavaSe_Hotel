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

    public void delete(String name) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.delete(name);
        }
    }

    public void edit(Property property) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.edit(property);
        }
    }

    public Property findByName(String name) throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findById(name);
        }
    }

    public List<Property> findAll() throws Exception {
        try(PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findAll();
        }
    }
}
