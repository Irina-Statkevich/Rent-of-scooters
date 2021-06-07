package statkevich.scooters.dao;

import statkevich.scooters.dao.IDao.*;
import statkevich.scooters.entity.entities.*;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = {JPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PaymentDaoTest extends TestCase {

    @Autowired
    private IPaymentDao paymentDao;

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IScooterDao scooterDao;

    @Autowired
    private IRentDao rentDao;

    @Autowired
    private IPriceListDao priceListDao;

    private static BigDecimal testSum;
    private static String testScootersModel;
    private static int testFirstResult;
    private static int testSizeOfPage;

    private Payment resultPayment;
    private List<Payment> resultListPayment;

    @BeforeClass
    public static void prepareTestData() {
        testSum = BigDecimal.valueOf(6);
        testScootersModel = "Model1";
        testSizeOfPage = 2;
        testFirstResult = 1;
    }

    @Test
    public void testCreate() {
        Users testUser = userDao.create(new Users("Test", "123", "test", roleDao.readByTitle("USER")));
        Payment testPayment = new Payment(testSum, testUser);

        resultPayment = paymentDao.create(testPayment);

        assertNotNull(resultPayment);
        assertEquals(resultPayment, testPayment);
    }

    @Test
    public void testReadAll() {
        resultListPayment = paymentDao.readAll();

        if (resultListPayment.size() != 0) {
            assertNotNull(resultListPayment.get(0));
        }
    }

    @Test
    public void testGetByUser() {
        Users testUser = userDao.create(new Users("Test", "123", "test", roleDao.readByTitle("USER")));

        resultPayment = paymentDao.create(new Payment(testSum, testUser));

        resultListPayment = paymentDao.getByUser(testUser);

        assertFalse(resultListPayment.isEmpty());
        assertNotNull(resultListPayment.get(0));
        assertEquals(resultListPayment.get(0), resultPayment);
    }

    @Test
    public void testGetFreePayment() {
        Users testUser = userDao.create(new Users("Test", "123", "test", roleDao.readByTitle("USER")));
        Payment testPayment = paymentDao.create(new Payment(testSum, testUser));

        List<Payment> resultListPayment = paymentDao.getFreePayment(testUser, testSum);

        assertFalse(resultListPayment.isEmpty());
        assertNotNull(resultListPayment.get(0));
        assertEquals(testPayment, resultListPayment.get(0));
    }

    @Test
    public void testUpdateRentId() {
        Users testUser = userDao.create(new Users("Test", "123", "test", roleDao.readByTitle("USER")));
        PriceList testPrice = priceListDao.read(1L);
        Payment testPayment = paymentDao.create(new Payment(testSum, testUser));
        Rent testRent = rentDao.create(new Rent(testUser, testPrice.getScooter(), testPrice, new Date()));

        resultPayment = paymentDao.updateRentId(testPayment, testRent);

        assertNotNull(resultPayment);
        assertEquals(resultPayment.getRent(), testRent);
    }

    @Test
    public void testReadPage() {
        Users testUser = userDao.create(new Users("Test", "123", "test", roleDao.readByTitle("USER")));

        List<Payment> resultListPayment = paymentDao.readPage(testFirstResult, testSizeOfPage, testUser, testSum);
        if (!resultListPayment.isEmpty()) {
            assertEquals(resultListPayment.get(0).getUser().getName(), testUser.getName());
            assertEquals(resultListPayment.get(0).getSum(), BigDecimal.valueOf(6));
        }
    }

}