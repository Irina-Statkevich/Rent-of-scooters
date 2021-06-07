package statkevich.scooters.dao;

import statkevich.scooters.dao.IDao.*;
import statkevich.scooters.entity.entities.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ContextConfiguration(classes = {JPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RentDAOTest extends TestCase {

    @Autowired
    private IRentDao rentDao;

    @Autowired
    private IScooterDao scooterDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ITermOfRentDao termOfRentDAO;

    @Autowired
    private IPriceListDao priceListDao;

    @Test
    public void testReadByUserId() {
        Users resultUser = userDao.readAll().get(0);
        List<Rent> listRent = rentDao.readByUserId(resultUser.getId());

        if (listRent.size() > 0) {
            assertNotNull(listRent.get(0));
            assertEquals(listRent.get(0).getUser().getId(), resultUser.getId());
        }
    }

    @Test
    public void testReadByUserScooter() {
        Scooters testScooter = scooterDao.readAll().get(0);
        Users testUser = userDao.readAll().get(0);

        Rent resultRent = rentDao.readByUserScooter(testUser.getId(), testScooter.getNumber());

        if (resultRent != null) {
            assertNotNull(resultRent);
            assertEquals(resultRent.getUser().getId(), testUser.getId());
            assertEquals(resultRent.getScooter().getModel(), testScooter.getModel());
        }
    }

    @Test
    public void testUpdateDateEnd() {
        List<Rent> testListRent = rentDao.readAll();
        if (!testListRent.isEmpty()) {
            Rent testRent=testListRent.get(0);
            if (testRent != null) {
                try {
                    testRent.setDateEnd((new SimpleDateFormat("dd-MM-yy")).parse("01-01-2022"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Rent resultRent = rentDao.updateDateEnd(testRent);

                assertEquals(resultRent.getDateEnd(), testRent.getDateEnd());
                assertEquals(resultRent.getId(), testRent.getId());
            }
        }
    }

    @Test
    public void testReadAll() {
        List<Rent> listRent = rentDao.readAll();

        if (listRent.size() != 0) {
            assertNotNull(listRent.get(0));
        }
    }

    @Test
    public void testCreate() {

        Users user = userDao.readAll().get(0);
        Scooters scooter = scooterDao.readAll().get(0);
        TermOfRent termOfRent = termOfRentDAO.readByTitle("Day");
        PriceList priceList = priceListDao.readByTermAndScooter(termOfRent.getId(), scooter.getNumber());

        Rent testRent = new Rent(user, scooter, priceList);
        try {
            testRent.setDateStart((new SimpleDateFormat("dd-MM-yy")).parse("01-01-2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent resultRent = rentDao.create(testRent);

        assertNotNull(resultRent);
        assertEquals(resultRent.getDateStart(), testRent.getDateStart());
        assertEquals(resultRent.getUser(), testRent.getUser());
        assertEquals(resultRent.getScooter(), testRent.getScooter());

    }
}