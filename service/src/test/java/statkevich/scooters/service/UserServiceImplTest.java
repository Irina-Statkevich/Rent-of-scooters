package statkevich.scooters.service;

import statkevich.scooters.dao.DAO.RoleDAO;
import statkevich.scooters.dao.DAO.UserDAO;
import statkevich.scooters.dao.repository.UsersRepository;
import statkevich.scooters.dto.UserDTO;
import statkevich.scooters.entity.entities.Roles;
import statkevich.scooters.entity.entities.Users;
import statkevich.scooters.service.mappers.IUserMapper;
import statkevich.scooters.service.serviceImpl.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends TestCase {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserDAO userDAO;
    @Mock
    private RoleDAO roleDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Spy
    IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
    @InjectMocks
    private UserServiceImpl userService;

    private static Users testUser;
    private static List<Users> testUsersList;
    private static Roles testRole;

    @BeforeClass
    public static void prepareTestData() {
        testUser = new Users("Ann");
        testUser.setId(1L);
        testUser.setPass("pass");
        testUser.setPhoneNumber("123ann");

        testRole = new Roles("User");
        testUser.setId(1L);

        testUsersList = new ArrayList<>();
        testUsersList.add(testUser);
    }

    //@Test
    public void testRead() {
        when(userDAO.read(any(Long.class))).thenReturn(testUser);

        UserDTO resultUserDTO = userService.read(1L);

        Mockito.verify(userDAO).read(1L);
        assertNotNull(resultUserDTO);
        assertEquals(resultUserDTO.getName(), userMapper.userToUserDto(testUser).getName());
    }

    @Test
    public void testReadByName() {
        //when(userDAO.readByName(any(String.class))).thenReturn(testUser);
        when(usersRepository.findByName(any(String.class))).thenReturn(testUser);

        UserDTO resultUserDTO = userService.readByName("Ann");

        Mockito.verify(usersRepository).findByName("Ann");
        assertNotNull(resultUserDTO);
        assertEquals(resultUserDTO.getName(), userMapper.userToUserDto(testUser).getName());
    }



    @Test
    public void testCreate() {
        when(userDAO.create(any(Users.class))).thenReturn(testUser);
        when(roleDAO.readByTitle(any(String.class))).thenReturn(testRole);
        when(passwordEncoder.encode(any(String.class))).thenReturn("pass");

        UserDTO testUserDTO = userMapper.userToUserDto(testUser);
        UserDTO resultUser = userService.create(testUserDTO);

        Mockito.verify(userDAO).create(any(Users.class));
        assertNotNull(resultUser);
    }

}