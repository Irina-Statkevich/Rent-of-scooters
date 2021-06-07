package statkevich.scooters.dao.IDao;

import statkevich.scooters.entity.entities.Payment;
import statkevich.scooters.entity.entities.Rent;
import statkevich.scooters.entity.entities.Users;

import java.math.BigDecimal;
import java.util.List;

public interface IPaymentDao {

    Payment create(Payment payment);

    List<Payment> readAll();

    List<Payment> readPage(int firstResult, int sizeOfPage, Users user, BigDecimal sum);

    List<Payment> getByUser(Users user);

    List<Payment> getFreePayment(Users user, BigDecimal sum);

    Payment updateRentId(Payment payment, Rent rent);
}
