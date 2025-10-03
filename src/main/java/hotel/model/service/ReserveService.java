package hotel.model.service;

import hotel.model.entity.Reserve;
import hotel.model.repository.ReserveRepository;
import lombok.Getter;

import java.util.List;

public class ReserveService implements Service<Reserve, Integer> {
    @Getter
    private static ReserveService service = new ReserveService();

    private ReserveService() {
    }

    @Override
    public void save(Reserve reserve) throws Exception {
        try (ReserveRepository reserveRepository = new ReserveRepository()) {
            reserveRepository.save(reserve);
        }
    }

    @Override
    public void edit(Reserve reserve) throws Exception {
        try (ReserveRepository reserveRepository = new ReserveRepository()) {
            reserveRepository.edit(reserve);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ReserveRepository reserveRepository = new ReserveRepository()) {
            reserveRepository.delete(id);
        }
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        try (ReserveRepository reserveRepository = new ReserveRepository()) {
            return reserveRepository.findAll();
        }
    }

    @Override
    public Reserve findById(Integer id) throws Exception {
        try (ReserveRepository reserveRepository = new ReserveRepository()) {
            return reserveRepository.findById(id);
        }
    }
}
