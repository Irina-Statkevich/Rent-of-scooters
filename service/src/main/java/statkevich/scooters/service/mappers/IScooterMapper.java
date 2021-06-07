package statkevich.scooters.service.mappers;

import statkevich.scooters.dto.ScooterDTO;
import statkevich.scooters.entity.entities.Scooters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IScooterMapper {
    //пока запись из браузера не нужна
    //Scooters scooterDtoToScooter(final ScooterDTO scooterDTO);

    @Mapping(target = "seller", source = "seller.name")
    //@Mapping(target = "typeProducer", source = "typeProducer.id")
    @Mapping(target = "producer", source = "typeProducer.producer.name")
    @Mapping(target = "type", source = "typeProducer.scootersType.title")
    ScooterDTO scooterToScooterDto(final Scooters scooter);

    List<ScooterDTO> listScooterToListScooterDto(List<Scooters> scooter);

}

