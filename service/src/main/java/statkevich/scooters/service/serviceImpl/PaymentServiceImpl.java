package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.IPaymentDao;
import statkevich.scooters.dao.IDao.IUserDao;
import statkevich.scooters.dto.PaymentDTO;
import statkevich.scooters.entity.entities.Payment;
import statkevich.scooters.entity.entities.Users;
import statkevich.scooters.service.ServicesI.PaymentService;
import statkevich.scooters.service.mappers.IPaymentMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);

    @Autowired
    private IPaymentDao paymentDao;

    @Autowired
    private IPaymentMapper paymentMapper;

    @Autowired
    private IUserDao userDao;

    @Override
    public PaymentDTO create(BigDecimal sum, String userName) {
        Payment payment = new Payment(sum, userDao.readByName(userName));
        return paymentMapper.paymentToPaymentDTO(paymentDao.create(payment));
    }

    @Override
    public List<PaymentDTO> readAll() {
        return paymentMapper.listPaymentToListPaymentDto(paymentDao.readAll());
    }

    @Override
    public List<PaymentDTO> readPage(int page, int sizeOfPage, String userName, BigDecimal sum) {
        int firstResult = (page - 1) * sizeOfPage;
        Users user = null;
        if (userName != null) {
            if ((!userName.isEmpty()) && (userName.length() > 0)) {
                user = userDao.readByName(userName);
            }
        }
        return paymentMapper.listPaymentToListPaymentDto(paymentDao.readPage(firstResult, sizeOfPage, user, sum));
    }

    @Override
    public List<PaymentDTO> getByUserName(String name) {
        return paymentMapper.listPaymentToListPaymentDto(paymentDao.getByUser(userDao.readByName(name)));
    }
}
