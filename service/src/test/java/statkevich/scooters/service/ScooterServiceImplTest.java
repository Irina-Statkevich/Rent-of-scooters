package statkevich.scooters.service;

import statkevich.scooters.dao.DAO.ScootersDAO;
import statkevich.scooters.dto.ScooterDTO;
import statkevich.scooters.entity.entities.Scooters;
import statkevich.scooters.service.mappers.IScooterMapper;

import statkevich.scooters.service.serviceImpl.ScooterServiceImpl;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScooterServiceImplTest extends TestCase {

    @Mock
    private ScootersDAO scootersDAO;
    @Spy
    IScooterMapper scooterMapper = Mappers.getMapper(IScooterMapper.class);
    @InjectMocks
    private ScooterServiceImpl scooterService;

    private static Scooters testScooter;
    private static Scooters testScooter1;
    private static List<Scooters> testScootersList;
    private static LocalDate testDate;

    @BeforeClass
    public static void prepareTestData() {
        testScooter = new Scooters();
        testScooter.setModel("Model1");

        testScooter1 = new Scooters();
        testScooter1.setModel("Model2");

        testScootersList = new ArrayList<>();
        testScootersList.add(testScooter);
        testScootersList.add(testScooter1);

        testDate=LocalDate.of(2021, 7, 1);
    }

    @Test
    public void testRead() {
        when(scootersDAO.read(any(Long.class))).thenReturn(testScooter);

        ScooterDTO resultScooterDTO = scooterService.read(1L);

        Mockito.verify(scootersDAO).read(1L);
        assertNotNull(resultScooterDTO);
        assertEquals(resultScooterDTO.getModel(), scooterMapper.scooterToScooterDto(testScooter).getModel());
    }

    @Test
    public void testReadAll() {
        when(scootersDAO.readAll()).thenReturn(testScootersList);

        List<ScooterDTO> resultListScooterDTO = scooterService.readAll();

        Mockito.verify(scootersDAO).readAll();
        assertFalse(resultListScooterDTO.isEmpty());
        assertEquals(2, resultListScooterDTO.size());
    }

    @Test
    public void testReadFreeScooters() {
        when(scootersDAO.readFree(any(LocalDate.class))).thenReturn(testScootersList);

        List<ScooterDTO> resultListScooterDTO = scooterService.readFreeScooters(testDate);

        Mockito.verify(scootersDAO).readFree(testDate);
        assertFalse(resultListScooterDTO.isEmpty());
    }

    @Test
    public void testReadByModel() {
        when(scootersDAO.readByModel(any(String.class))).thenReturn(testScooter);

        ScooterDTO resultScooterDTO = scooterService.readByModel("Model1");

        Mockito.verify(scootersDAO).readByModel("Model1");
        assertNotNull(resultScooterDTO);
        assertEquals(resultScooterDTO.getModel(), scooterMapper.scooterToScooterDto(testScooter).getModel());
    }
}