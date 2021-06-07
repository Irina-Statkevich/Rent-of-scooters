package statkevich.scooters.service;

import statkevich.scooters.dao.DAO.PriceListDAO;
import statkevich.scooters.dao.DAO.ScootersDAO;
import statkevich.scooters.dao.DAO.TermOfRentDAO;
import statkevich.scooters.dto.PriceListDTO;
import statkevich.scooters.entity.entities.PriceList;
import statkevich.scooters.entity.entities.Scooters;
import statkevich.scooters.entity.entities.TermOfRent;
import statkevich.scooters.service.mappers.IPriceListMapper;
import statkevich.scooters.service.serviceImpl.PriceListServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceListServiceImplTest extends TestCase {

    @Mock
    private ScootersDAO scootersDAO;
    @Mock
    private TermOfRentDAO termOfRentDao;
    @Mock
    private PriceListDAO priceListDAO;
    @Spy
    IPriceListMapper priceListMapper = Mappers.getMapper(IPriceListMapper.class);
    @InjectMocks
    private PriceListServiceImpl priceListService;

    private static Scooters testScooter;
    private static TermOfRent testTerm;
    private static PriceList testPrice1;
    private static PriceList testPrice2;
    private static List<PriceList> testListPrice;

    @BeforeClass
    public static void prepareTestData() {
        testScooter = new Scooters();
        testScooter.setModel("Model1");

        testTerm = new TermOfRent("Day", 1);

        testPrice1 = new PriceList(testScooter, testTerm);
        testPrice2 = new PriceList(testScooter, new TermOfRent("Week", 7));

        testListPrice = new ArrayList<>();
        testListPrice.add(testPrice1);
        testListPrice.add(testPrice2);

    }

    @Test
    public void testReadAll() {
        when(priceListDAO.readAll()).thenReturn(testListPrice);

        List<PriceListDTO> resultListPriceDTO = priceListService.readAll();

        Mockito.verify(priceListDAO).readAll();
        assertFalse(resultListPriceDTO.isEmpty());
        assertEquals(2, resultListPriceDTO.size());

    }

    @Test
    public void testReadByTermIdAndScooter() {
        when(scootersDAO.readByModel(any(String.class))).thenReturn(testScooter);
        when(priceListDAO.readByTermAndScooter(any(Long.class), any(Long.class))).thenReturn(testPrice1);

        PriceListDTO resultPriceListDTO = priceListService.readByTermIdAndScooter(1L, "Model1");

        Mockito.verify(scootersDAO).readByModel(testScooter.getModel());
        Mockito.verify(priceListDAO).readByTermAndScooter(any(Long.class), any(Long.class));
        assertNotNull(resultPriceListDTO);
        assertEquals(resultPriceListDTO.getScooter(), priceListMapper.PriceListToPriceListDto(testPrice1).getScooter());
    }

    @Test
    public void testReadByTermAndScooter() {
        when(scootersDAO.readByModel(any(String.class))).thenReturn(testScooter);
        when(termOfRentDao.readByTitle(any(String.class))).thenReturn(testTerm);
        when(priceListDAO.readByTermAndScooter(any(Long.class), any(Long.class))).thenReturn(testPrice1);

        PriceListDTO resultPriceListDTO = priceListService.readByTermAndScooter("Day", "Model1");

        Mockito.verify(scootersDAO).readByModel(testScooter.getModel());
        Mockito.verify(termOfRentDao).readByTitle(testTerm.getTitle());
        Mockito.verify(priceListDAO).readByTermAndScooter(any(Long.class), any(Long.class));
        assertNotNull(resultPriceListDTO);
        assertEquals(resultPriceListDTO.getScooter(), testPrice1.getScooter().getModel());
    }
}