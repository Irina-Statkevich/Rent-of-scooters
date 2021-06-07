package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.IScooterDao;
import statkevich.scooters.dto.ScooterDTO;
import statkevich.scooters.entity.entities.Scooters;
import statkevich.scooters.service.ServicesI.ScootersService;
import statkevich.scooters.service.mappers.IScooterMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class ScooterServiceImpl implements ScootersService {

    private static final Logger logger = Logger.getLogger(ScooterServiceImpl.class);

    @Autowired
    private IScooterDao scootersDAO;

    @Autowired
    private IScooterMapper scooterMapper;

    @Override
    public ScooterDTO read(Long number) {
        return scooterMapper.scooterToScooterDto(scootersDAO.read(number));
    }

    @Override
    public List<ScooterDTO> readAll() {
        return scooterMapper.listScooterToListScooterDto(scootersDAO.readAll());
    }

    @Override
    public List<ScooterDTO> readFreeScooters(LocalDate date) {
        //перевести дату
        List<Scooters> result = scootersDAO.readFree(date);
        return scooterMapper.listScooterToListScooterDto(result);
    }


    @Override
    public ScooterDTO readByModel(String model) {
        return scooterMapper.scooterToScooterDto(scootersDAO.readByModel(model));
    }

}
