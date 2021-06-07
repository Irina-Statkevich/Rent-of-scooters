package statkevich.scooters.service.mappers;


import statkevich.scooters.dto.PaymentDTO;
import statkevich.scooters.entity.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPaymentMapper {

    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "rentId", expression ="java(payment.getRent()==null?null:payment.getRent().getId())")
   // @Mapping(target = "rentId", source = "rent.id")
  //  expression = "java(UUID.randomUUID().toString())")
    PaymentDTO paymentToPaymentDTO(final  Payment payment);

    //PaymentDTO paymentDTOToPayment(final  PaymentDTO paymentDTO);

    List<PaymentDTO> listPaymentToListPaymentDto(List<Payment> paymentList);

}

