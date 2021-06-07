package statkevich.scooters.dao.IDao;

import java.util.List;

public interface IGenericDao<T> {

    T read(final Long id);

    T create(final T entity);

    List<T> readAll();

}
