package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.*;
import statkevich.scooters.dto.RentDTO;
import statkevich.scooters.entity.entities.*;
import statkevich.scooters.service.ServicesI.RentService;
import statkevich.scooters.service.mappers.IRentMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.ServerException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    @Autowired
    private IRentDao rentDao;
    @Autowired
    private IPriceListDao priceListDao;
    @Autowired
    private IUserDao userDAO;
    @Autowired
    private IScooterDao scooterDao;
    @Autowired
    private ITermOfRentDao termOfRentDao;
    @Autowired
    private IPaymentDao paymentDao;

    @Autowired
    private IRentMapper rentMapper;

    private static final Logger logger = Logger.getLogger(RentServiceImpl.class);


    @Override
    public List<RentDTO> readAll() {
        return rentMapper.ListRentToListRentDTO(rentDao.readAll());
    }

    @Override
    public List<RentDTO> getByUserName(String name) {
        Users user = userDAO.readByName(name);
        List<Rent> rent = rentDao.readByUserId(user.getId());
        return rentMapper.ListRentToListRentDTO(rent);
    }

    @Override
    public RentDTO create(RentDTO rentDTO) {
        logger.info("00");
        Rent rent = rentMapper.RentDTOToRent(rentDTO);

        Users user = userDAO.readByName(rentDTO.getUserName());
        rent.setUser(user);
        logger.info("0");
        Scooters scooter = scooterDao.readByModel(rentDTO.getScooterModel());
        rent.setScooter(scooter);
        logger.info("1");
        TermOfRent termOfRent = termOfRentDao.readByTitle(rentDTO.getTermOfRent());
        PriceList priceList = priceListDao.readByTermAndScooter(termOfRent.getId(), scooter.getNumber());
        rent.setPrice(priceList);
        logger.info("2");
        //проверим, есть ли оплата
        List<Payment> paymentList = paymentDao.getFreePayment(user, priceList.getPrice());
        if (paymentList.size() > 0) {
            //тут оплату привязать
            Rent resultRent = rentDao.create(rent);
            System.out.println("Rent---" + resultRent);
            paymentDao.updateRentId(paymentList.get(0), rent);

            return rentMapper.RentToRentDto(resultRent);
        } else {
            try {
                throw new ServerException("Подходящая оплата не найдена");
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public RentDTO returnTheScooter(String scooterName, String userName) {

        Users user = userDAO.readByName(userName);
        Scooters scooter = scooterDao.readByModel(scooterName);

        Rent rent = rentDao.readByUserScooter(user.getId(), scooter.getNumber());
        rent.setDateEnd(new Date());

        return rentMapper.RentToRentDto(rentDao.updateDateEnd(rent));
    }
}
