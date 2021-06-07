package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.IRoleDao;
import statkevich.scooters.dao.IDao.IUserDao;
import statkevich.scooters.dao.repository.UsersRepository;
import statkevich.scooters.entity.entities.Roles;
import statkevich.scooters.service.ServicesI.UsersService;
import statkevich.scooters.service.mappers.IUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import statkevich.scooters.dto.UserDTO;
import statkevich.scooters.entity.entities.Users;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IUserDao userDAO;
    @Autowired
    private IRoleDao roleDAO;


    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Override
    public UserDTO read(Long id) {
        return userMapper.userToUserDto(usersRepository.findById(id).get());
        //return userMapper.userToUserDto(userDAO.read(id));
    }

    @Override
    public UserDTO readByName(String name) {
        //return userMapper.userToUserDto(userDAO.readByName(name));
        return userMapper.userToUserDto(usersRepository.findByName(name));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        Roles role = roleDAO.readByTitle("USER");

        Users user = userMapper.userDtoToUser(userDTO);
        user.setRole(role);
        user.setPass(passwordEncoder.encode(userDTO.getPass()));

        return userMapper.userToUserDto(userDAO.create(user));
    }

    @Override
    public List<UserDTO> readAll() {
        //userDAO.readAll()
        //List<Users> usersList=usersRepository.findAll();
        return null;
    }

    //    @Override
//    public UserDTO read(Long id) {
//        return UserDTO.getUserDTO(usersRepository.findById(id).orElse(new Users()));
//    }

//    @Override
//    public Users create(UserDTO userDTO) {
//
//        //user = userRepository.findOne(userDTO.getId()); - добавить проверку?
//        //Users user= userMapper.userDtoToUser(userDTO);
//        //Roles role= userMapper.roleDtoToRole(userDTO.getRoleDTO());
//
//        Users user = usersRepository.saveAndFlush(userMapper.userDtoToUser(userDTO));
//
//        return user;
//    }
}
