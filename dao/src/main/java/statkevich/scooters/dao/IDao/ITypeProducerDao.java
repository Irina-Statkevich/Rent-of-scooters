package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.TypesProducers;

public interface ITypeProducerDao {

    TypesProducers read(final Long id);
}
