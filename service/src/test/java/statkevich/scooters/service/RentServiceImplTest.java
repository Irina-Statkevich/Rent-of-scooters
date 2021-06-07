package statkevich.scooters.service;

import statkevich.scooters.dao.DAO.*;
import statkevich.scooters.dao.IDao.IPaymentDao;
import statkevich.scooters.dto.RentDTO;
import statkevich.scooters.entity.entities.*;
import statkevich.scooters.service.mappers.IRentMapper;
import statkevich.scooters.service.serviceImpl.RentServiceImpl;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceImplTest extends TestCase {


    @Mock
    private RentDAO rentDAO;
    @Mock
    private PriceListDAO priceListDAO;
    @Mock
    private UserDAO userDAO;
    @Mock
    private ScootersDAO scootersDAO;
    @Mock
    private TermOfRentDAO termOfRentDAO;
    @Mock
    private IPaymentDao paymentDao;

    @Spy
    IRentMapper rentMapper = Mappers.getMapper(IRentMapper.class);

    @InjectMocks
    private RentServiceImpl rentService;

    private static Rent testRent;
    private static Users testUser;
    private static Scooters testScooter;
    private static PriceList testPrice;
    private static TermOfRent testTerm;
    private static List<Rent> testListRent;
    private static RentDTO testRentDTO;
    private static Payment testPayment;
    private static List<Payment> testListPayment;

    @BeforeClass
    public static void prepareTestData() {
        testUser = new Users("Ann");
        testUser.setId(1L);

        testScooter = new Scooters();
        testScooter.setModel("Model1");

        testTerm = new TermOfRent("Day", 1);

        testPrice = new PriceList(testScooter, testTerm);

        testRent = new Rent(testUser, testScooter, testPrice);

        testListRent = new ArrayList<>();
        testListRent.add(testRent);

        testRentDTO = new RentDTO("Model1", "01-01-2022", "Day");

        testPayment = new Payment(new BigDecimal("8"), testUser);

        testListPayment = new ArrayList<>();
        testListPayment.add(testPayment);
    }

    @Test
    public void testReadAll() {
        when(rentDAO.readAll()).thenReturn(testListRent);

        List<RentDTO> resultRentDTO = rentService.readAll();

        Mockito.verify(rentDAO).readAll();
        assertFalse(resultRentDTO.isEmpty());
        assertEquals(1, resultRentDTO.size());
    }

    @Test
    public void testGetByUserName() {
        when(userDAO.readByName(any(String.class))).thenReturn(testUser);
        when(rentDAO.readByUserId(any(Long.class))).thenReturn(testListRent);

        List<RentDTO> resultRentDTO = rentService.getByUserName(testUser.getName());

        Mockito.verify(rentDAO).readByUserId(testUser.getId());
        Mockito.verify(userDAO).readByName(testUser.getName());
        assertFalse(resultRentDTO.isEmpty());
        assertEquals(1, resultRentDTO.size());
    }

    @Test
    public void testCreate() {
        when(userDAO.readByName(any(String.class))).thenReturn(testUser);
        when(scootersDAO.readByModel(any(String.class))).thenReturn(testScooter);
        when(termOfRentDAO.readByTitle(any(String.class))).thenReturn(testTerm);
        when(priceListDAO.readByTermAndScooter(any(Long.class), any(Long.class))).thenReturn(testPrice);

        when(paymentDao.getFreePayment(any(Users.class), any(BigDecimal.class))).thenReturn(testListPayment);
        when(rentDAO.create(any(Rent.class))).thenReturn(testRent);
        when(paymentDao.updateRentId(any(Payment.class),any(Rent.class))).thenReturn(testPayment);

        RentDTO testRentDTO = rentMapper.RentToRentDto(testRent);
        RentDTO resultRentDTO = rentService.create(testRentDTO);

        Mockito.verify(userDAO).readByName(testUser.getName());
        Mockito.verify(scootersDAO).readByModel(testScooter.getModel());
        Mockito.verify(termOfRentDAO).readByTitle(testTerm.getTitle());
        Mockito.verify(priceListDAO).readByTermAndScooter(any(Long.class), any(Long.class));

        Mockito.verify(paymentDao).getFreePayment(any(Users.class), any(BigDecimal.class));
        Mockito.verify(rentDAO).create(any(Rent.class));
        Mockito.verify(paymentDao).updateRentId(any(Payment.class),any(Rent.class));

        assertNotNull(resultRentDTO);
        assertEquals(resultRentDTO.getId(), testRentDTO.getId());
    }

    @Test
    public void testReturnTheScooter() {
        when(userDAO.readByName(any(String.class))).thenReturn(testUser);
        when(scootersDAO.readByModel(any(String.class))).thenReturn(testScooter);
        when(rentDAO.readByUserScooter(any(Long.class), any(Long.class))).thenReturn(testRent);
        when(rentDAO.updateDateEnd(any(Rent.class))).thenReturn(testRent);

        RentDTO resultRentDTO = rentService.returnTheScooter("Model1", "Ann");

        Mockito.verify(userDAO).readByName(testUser.getName());
        Mockito.verify(scootersDAO).readByModel(testScooter.getModel());
        Mockito.verify(rentDAO).readByUserScooter(any(Long.class), any(Long.class));
        Mockito.verify(rentDAO).updateDateEnd(testRent);
        assertNotNull(resultRentDTO);
        assertEquals(resultRentDTO.getScooterModel(), testScooter.getModel());
    }
}