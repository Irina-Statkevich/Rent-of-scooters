package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.Users;

public interface IUserDao extends IGenericDao<Users> {

    Users readByName(final String name);

    Users readByPhone(String phone);
}
