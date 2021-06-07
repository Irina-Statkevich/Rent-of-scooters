package statkevich.scooters.dao.DAO;

import statkevich.scooters.dao.IDao.ITypeProducerDao;
import statkevich.scooters.entity.entities.TypesProducers;
import org.springframework.stereotype.Repository;

@Repository
public class TypeProducerDAO extends GenericDaoImpl<TypesProducers> implements ITypeProducerDao {

    @Override
    public TypesProducers read(final Long id) {
        return entityManager.find(TypesProducers.class, id);
    }
}

