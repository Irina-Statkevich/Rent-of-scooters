package statkevich.scooters.dao;

import statkevich.scooters.dao.IDao.IRoleDao;
import statkevich.scooters.dao.IDao.IUserDao;
import statkevich.scooters.entity.entities.Roles;
import statkevich.scooters.entity.entities.Users;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(classes = {JPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDAOTest extends TestCase {

    private Users resultUser;
    private List<Users> resultListUser;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    private Users testCreatedUser;

    @Test
    public void testCreate() {
        Roles testRole = roleDao.readByTitle("USER");
        Users testUser = new Users("Test", "123", "test",testRole);

        resultUser = userDao.create(testUser);
        System.out.println(resultUser.toString());

        assertNotNull(resultUser);
        assertEquals(resultUser.getName(), testUser.getName());
    }

    @Test
    public void testReadByPhone() {
        Roles testRole = roleDao.readByTitle("USER");
        Users testUser = userDao.create(new Users("Test", "123", "test", testRole));

        resultUser = userDao.readByPhone(testUser.getPhoneNumber());
        assertNotNull(resultUser);
        assertEquals(resultUser, testUser);
    }

    @Test
    public void testRead() {
        Roles testRole = roleDao.readByTitle("USER");
        Users testUser = userDao.create(new Users("Test", "123", "test", testRole));

        resultUser = userDao.read(testUser.getId());

        assertNotNull(resultUser);
        assertEquals(resultUser, testUser);
    }

    @Test
    public void testReadByName() {
        Roles testRole = roleDao.readByTitle("USER");
        Users testUser = userDao.create(new Users("Test", "123", "test", testRole));

        resultUser = userDao.readByName(testUser.getName());

        assertNotNull(resultUser);
        assertEquals(resultUser, testUser);
    }

    @Test
    public void testReadAll() {
        resultListUser = userDao.readAll();

        assertNotSame(resultListUser.size(), 0);
    }
}