package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.RentDTO;

import java.util.List;

public interface RentService {

    RentDTO create(final RentDTO rentDTO);

    List<RentDTO> readAll();

    List<RentDTO> getByUserName(String name);

    RentDTO returnTheScooter(String scooter, String name);
}
