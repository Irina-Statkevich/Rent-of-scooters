package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.RoleDTO;

public interface RolesService {
    RoleDTO readByTitle(final String title);

    RoleDTO read(final Long id);

}
