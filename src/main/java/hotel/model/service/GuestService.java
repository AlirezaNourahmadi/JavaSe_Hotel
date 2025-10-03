package hotel.model.service;

import hotel.model.entity.Guest;
import hotel.model.repository.GuestRepository;
import lombok.Getter;

import java.util.List;

public class GuestService implements Service<Guest, Integer> {
    @Getter
    private static GuestService service = new GuestService();

    private GuestService() {
    }

    @Override
    public void save(Guest guest) throws Exception {
        try (GuestRepository guestRepository = new GuestRepository()) {
            guestRepository.save(guest);
        }
    }

    @Override
    public void edit(Guest guest) throws Exception {
        try (GuestRepository guestRepository = new GuestRepository()) {
            guestRepository.edit(guest);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (GuestRepository guestRepository = new GuestRepository()) {
            guestRepository.delete(id);
        }
    }

    @Override
    public List<Guest> findAll() throws Exception {
        try (GuestRepository guestRepository = new GuestRepository()) {
            return guestRepository.findAll();
        }
    }

    @Override
    public Guest findById(Integer id) throws Exception {
        try (GuestRepository guestRepository = new GuestRepository()) {
            return guestRepository.findById(id);
        }
    }
}
