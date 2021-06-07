package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.TermOfRent;

public interface ITermOfRentDao {

    TermOfRent read(final Long id);

    TermOfRent readByTitle(final String title);

    //List<TermOfRent> readAll();
}
