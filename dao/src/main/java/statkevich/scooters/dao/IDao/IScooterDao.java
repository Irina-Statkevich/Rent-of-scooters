package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.Scooters;

import java.time.LocalDate;
import java.util.List;

public interface IScooterDao {

    Scooters readByModel(final String model);

    Scooters read(final Long number);

    List<Scooters> readAll();

    List<Scooters> readFree(LocalDate date);
}
