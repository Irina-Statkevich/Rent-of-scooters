package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.IPriceListDao;
import statkevich.scooters.dao.IDao.IScooterDao;
import statkevich.scooters.dao.IDao.ITermOfRentDao;
import statkevich.scooters.dto.PriceListDTO;
import statkevich.scooters.entity.entities.Scooters;
import statkevich.scooters.entity.entities.TermOfRent;
import statkevich.scooters.service.ServicesI.PriceListService;
import statkevich.scooters.service.mappers.IPriceListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private IPriceListDao priceListDao;
    @Autowired
    private IScooterDao scooterDao;
    @Autowired
    private ITermOfRentDao termOfRentDao;

    @Autowired
    private IPriceListMapper priceListMapper;

    @Override
    public List<PriceListDTO> readAll() {
        return priceListMapper.listPriceListToListPriceListDto(priceListDao.readAll());
    }

    @Override
    public PriceListDTO readByTermIdAndScooter(Long term, String scootersModel) {
        Scooters scooter = scooterDao.readByModel(scootersModel);
        return priceListMapper.PriceListToPriceListDto(priceListDao.readByTermAndScooter(term, scooter.getNumber()));
    }

    @Override
    public PriceListDTO readByTermAndScooter(String term, String scootersModel) {
        TermOfRent termOfRent = termOfRentDao.readByTitle(term);
        return readByTermIdAndScooter(termOfRent.getId(), scootersModel);
        //return priceListMapper.PriceListToPriceListDto(priceListDao.readByTermAndScooter(termOfRent.getId(),scooter.getNumber()));
    }
}
