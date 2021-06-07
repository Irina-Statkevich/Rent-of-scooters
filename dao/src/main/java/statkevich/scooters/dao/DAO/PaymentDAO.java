package statkevich.scooters.dao.DAO;

import statkevich.scooters.dao.IDao.IPaymentDao;
import statkevich.scooters.entity.entities.Payment;
import statkevich.scooters.entity.entities.Rent;
import statkevich.scooters.entity.entities.Users;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class PaymentDAO extends GenericDaoImpl<Payment> implements IPaymentDao {

    private static final Logger logger=Logger.getLogger(PaymentDAO.class);

    @Override
    public Payment create(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public List<Payment> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> paymentRoot = cq.from(Payment.class);

        CriteriaQuery<Payment> all = cq.select(paymentRoot);

        return entityManager.createQuery(all).getResultList();
    }

    @Override
    public List<Payment> readPage(int firstResult, int sizeOfPage, Users user, BigDecimal sum) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> paymentRoot = cq.from(Payment.class);

        CriteriaQuery<Payment> all = cq.select(paymentRoot);

        //если есть фильтры, то добавить
        Predicate[] predicates;
        if ((user != null) && (sum != null)) {
            predicates = new Predicate[2];
            predicates[0] = cb.equal(paymentRoot.get("user"), user.getId());
            predicates[1] = cb.equal(paymentRoot.get("sum"), sum);
            all.where(predicates);
        }else if ((user != null) || (sum != null)){
            predicates = new Predicate[1];
            if(user != null){
                predicates[0] = cb.equal(paymentRoot.get("user"), user.getId());
            }else{
                predicates[0] = cb.equal(paymentRoot.get("sum"), sum);
            }
            all.where(predicates);
        }

        return entityManager.createQuery(all).setFirstResult(firstResult).setMaxResults(sizeOfPage).getResultList();
    }

    @Override
    public List<Payment> getByUser(Users user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> paymentRoot = cq.from(Payment.class);

        Predicate userById = cb.equal(paymentRoot.get("user"), user.getId());
        cq.where(userById);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Payment> getFreePayment(Users user, BigDecimal sum) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> paymentRoot = cq.from(Payment.class);


        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.equal(paymentRoot.get("user"), user.getId());
        predicates[1] = cb.equal(paymentRoot.get("sum"), sum);
        predicates[2] = cb.isNull(paymentRoot.get("rent"));

        cq.select(paymentRoot).where(predicates);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Payment updateRentId(Payment payment, Rent rent) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Payment> criteriaUpdate = cb.createCriteriaUpdate(Payment.class);
        Root<Payment> root = criteriaUpdate.from(Payment.class);

        payment.setRent(rent);
        criteriaUpdate.set("rent", payment.getRent());
        criteriaUpdate.where(cb.equal(root.get("id"), payment.getId()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        return payment;
    }
}
