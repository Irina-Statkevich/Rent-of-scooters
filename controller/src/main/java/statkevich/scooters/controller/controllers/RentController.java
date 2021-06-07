package statkevich.scooters.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import statkevich.scooters.dto.PaymentDTO;
import statkevich.scooters.dto.PriceListDTO;
import statkevich.scooters.dto.RentDTO;
import statkevich.scooters.dto.ScooterDTO;
import statkevich.scooters.service.ServicesI.PaymentService;
import statkevich.scooters.service.ServicesI.PriceListService;
import statkevich.scooters.service.ServicesI.RentService;
import statkevich.scooters.service.ServicesI.ScootersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class RentController {

    private static final Logger logger = Logger.getLogger(RentController.class);

    @Autowired
    public ScootersService scooterService;

    @Autowired
    public PriceListService priceListService;

    @Autowired
    public RentService rentService;

    @Autowired
    public PaymentService paymentService;

    //payment
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/payments/all",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PaymentDTO> allPayments() {
        return paymentService.readAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/payments/all/pages",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PaymentDTO> allPaymentsWithPagination(@RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "size", defaultValue = "2") int sizeOfPage,
                                                      @RequestParam(name = "user", required = false) String user,
                                                      @RequestParam(name = "sum", required = false) BigDecimal sum) {
        if (sizeOfPage < 1) {
            sizeOfPage = 2;
        }
        if (page < 1) {
            page = 1;
        }
        logger.info(user);
        logger.info(sum);
        return paymentService.readPage(page, sizeOfPage, user, sum);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/payments/pay",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public PaymentDTO addPayment(@RequestParam(name = "sum", required = true) BigDecimal sum, Principal principal) {
        return paymentService.create(sum, principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "/payments/my",
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PaymentDTO> allPaymentsForUser(Principal principal) {
        return paymentService.getByUserName(principal.getName());
    }

    //rent a scooter
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rent/all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RentDTO> getAll() {
        return rentService.readAll();
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/rent/my",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RentDTO> getByUser(Principal principal) {
        return rentService.getByUserName(principal.getName());
    }

    //price
    @RequestMapping(value = "/price/all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PriceListDTO> getAllPrice() {
        return priceListService.readAll();
    }

    @RequestMapping(value = "/price/scooter-term",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public PriceListDTO getPriceByTermAndScootersModel(@RequestParam(name = "term", required = true) String term,
                                                       @RequestParam(name = "scooter", required = true) String scooter) {
        return priceListService.readByTermAndScooter(term, scooter);
    }

    //about scooters
    @RequestMapping(value = "/scooters/number/{number}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    protected ScooterDTO getScooter(@PathVariable("number") Long number) {
        return scooterService.read(number);
    }

    @RequestMapping(value = "/scooters/model/{model}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    protected ScooterDTO getByModel(@PathVariable("model") String model) {
        return scooterService.readByModel(model);
    }

    @RequestMapping(value = "/scooters/all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ScooterDTO> getAllScooters() {
        return scooterService.readAll();
    }

    //ожидается, что в эту дату будут свободны
    //http://localhost:8081/controller-1.0-SNAPSHOT/scooters/free?date=2020-07-01
    @RequestMapping(value = "/scooters/free",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ScooterDTO> getFreeScooters(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        //logger.info(date);
        return scooterService.readFreeScooters(date);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/scooters/rent",
            method = {RequestMethod.POST, RequestMethod.GET},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object rentTheScooter(@RequestBody RentDTO rentDTO, Principal principal) {
        rentDTO.setUserName(principal.getName());
        RentDTO resultRentDTO = rentService.create(rentDTO);
        logger.info(resultRentDTO);
        if (resultRentDTO == null) {
            ObjectMapper mapper = new ObjectMapper();
            return Collections.singletonMap("message", "no payment");
        } else {
            return resultRentDTO;
        }
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/scooters/return",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RentDTO returnTheScooter(@RequestParam(name = "scooter", required = true) String scooter, Principal principal) {

        return rentService.returnTheScooter(scooter, principal.getName());
    }

}
