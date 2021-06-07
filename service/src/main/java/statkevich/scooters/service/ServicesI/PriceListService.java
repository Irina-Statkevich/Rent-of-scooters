package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.PriceListDTO;

import java.util.List;

public interface PriceListService {

    PriceListDTO readByTermIdAndScooter(Long term, String scootersModel);

    PriceListDTO readByTermAndScooter(String term, String scootersModel);

    List<PriceListDTO> readAll();

}
