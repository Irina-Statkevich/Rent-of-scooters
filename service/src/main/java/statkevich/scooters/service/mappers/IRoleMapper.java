package statkevich.scooters.service.mappers;


import statkevich.scooters.dto.RoleDTO;
import statkevich.scooters.entity.entities.Roles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    Roles roleDtoToRole(final RoleDTO roleDTO);

    RoleDTO roleToRoleDto(final Roles role);

    Roles roleTitleToRole(final String title);

}

