package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.UserDTO;

public interface UsersService extends GenericService<UserDTO> {

    UserDTO readByName(final String name);

    UserDTO create(UserDTO userDTO);

}
