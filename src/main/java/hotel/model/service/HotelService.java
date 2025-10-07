package hotel.model.service;

import hotel.model.entity.Hotel;
import hotel.model.repository.HotelRepository;
import lombok.Getter;

import java.util.List;

public final class HotelService {
    @Getter
    private static HotelService service = new HotelService();

    private HotelService() {
    }

    public void save(Hotel hotel) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            hotelRepository.save(hotel);
        }
    }

    public void update(Hotel hotel) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            hotelRepository.edit(hotel);
        }
    }

    public void delete(Integer id) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            hotelRepository.delete(id);
        }
    }

    public void edit(Hotel hotel) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            hotelRepository.edit(hotel);
        }
    }

    public Hotel findById(Integer id) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            return hotelRepository.findById(id);
        }
    }

    public List<Hotel> findAll() throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            return hotelRepository.findAll();
        }
    }
    public List<Hotel> findByName(String name) throws Exception {
        try (HotelRepository hotelRepository = new HotelRepository()) {
            return hotelRepository.findByName(name);
        }
    }

}


