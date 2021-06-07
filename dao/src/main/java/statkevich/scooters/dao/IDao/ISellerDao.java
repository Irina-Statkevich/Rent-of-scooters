package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.Sellers;

public interface ISellerDao {

    Sellers read(final Long id);
}
