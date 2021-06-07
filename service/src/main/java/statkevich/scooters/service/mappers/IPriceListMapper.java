package statkevich.scooters.service.mappers;

import statkevich.scooters.dto.PriceListDTO;
import statkevich.scooters.entity.entities.PriceList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPriceListMapper {

    @Mapping(target = "scooter", source = "scooter.model")
    @Mapping(target = "scooterNumber", source = "scooter.number")
    @Mapping(target = "termOfRent", source = "termOfRent.title")
    PriceListDTO PriceListToPriceListDto(final PriceList priceList);

    List<PriceListDTO> listPriceListToListPriceListDto(List<PriceList> priceLists);

}

