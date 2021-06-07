package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.Roles;

public interface IRoleDao extends IGenericDao<Roles> {

    Roles readByTitle(final String title);
}
