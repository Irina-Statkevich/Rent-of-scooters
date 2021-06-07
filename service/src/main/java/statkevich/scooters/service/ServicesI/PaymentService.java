package statkevich.scooters.service.ServicesI;

import statkevich.scooters.dto.PaymentDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    PaymentDTO create(final BigDecimal sum,String userName);

    List<PaymentDTO> readAll();

    List<PaymentDTO> readPage(int page,int sizeOfPage,String user, BigDecimal sum);

    List<PaymentDTO> getByUserName(String name);

}
