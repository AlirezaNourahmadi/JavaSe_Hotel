package hotel.model.repository;

import java.util.List;

public interface Repository<T, ID> {
    void save(T t) throws Exception;

    void edit(T t) throws Exception;

    void delete(ID id) throws Exception;

    List<T> findAll() throws Exception;

    T findById(ID id) throws Exception;
}
