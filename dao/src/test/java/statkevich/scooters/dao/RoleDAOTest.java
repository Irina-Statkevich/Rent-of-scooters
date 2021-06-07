package statkevich.scooters.dao;

import statkevich.scooters.dao.IDao.IRoleDao;
import statkevich.scooters.entity.entities.Roles;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {JPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RoleDAOTest extends TestCase {

    @Autowired
    private IRoleDao roleDAO;

    private static Roles testRole;

    @BeforeClass
    public static void prepareTestData() {

        testRole = new Roles("USER");
    }

    @Test
    public void testReadByTitle() {
        Roles resultRole = roleDAO.readByTitle(testRole.getTitle());

        assertNotNull(resultRole);
        assertEquals(resultRole.getTitle(), testRole.getTitle());

    }
}
