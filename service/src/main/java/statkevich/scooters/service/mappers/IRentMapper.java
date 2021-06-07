package statkevich.scooters.service.mappers;

import statkevich.scooters.dto.RentDTO;
import statkevich.scooters.entity.entities.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRentMapper {

    @Mapping(target = "scooterModel", source = "scooter.model")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "price", source = "price.price", numberFormat = "#.00")
    @Mapping(target = "termOfRent", source = "price.termOfRent.title")
    @Mapping(target = "dateStart", source = "dateStart", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "dateEnd", source = "dateEnd", dateFormat = "dd-MM-yyyy")
    RentDTO RentToRentDto(final Rent rent);

    @Mapping(target = "user", expression = "java(null)")
    @Mapping(target = "price", expression = "java(null)")
    @Mapping(target = "scooter", expression = "java(null)")
    @Mapping(target = "dateEnd", expression = "java(null)")
    @Mapping(target = "dateStart", source = "dateStart", dateFormat = "dd-MM-yyyy")
    Rent RentDTOToRent(final RentDTO rentDTO);

    List<RentDTO> ListRentToListRentDTO(final List<Rent> rent);

}

