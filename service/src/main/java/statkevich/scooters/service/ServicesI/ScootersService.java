package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.ScooterDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScootersService extends GenericService<ScooterDTO> {

    ScooterDTO readByModel(final String model);

    List<ScooterDTO> readFreeScooters(LocalDate date);
}
