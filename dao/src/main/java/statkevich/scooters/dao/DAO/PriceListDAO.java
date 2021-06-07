package statkevich.scooters.dao.DAO;

import statkevich.scooters.dao.IDao.IPriceListDao;
import statkevich.scooters.entity.entities.PriceList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PriceListDAO extends GenericDaoImpl<PriceList> implements IPriceListDao {

    private static final Logger logger = Logger.getLogger(PriceListDAO.class);

    @Override
    public PriceList readByTermAndScooter(Long term_id, Long scootersNumber) {
//        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//        CriteriaQuery<PriceList> cq=cb.createQuery(PriceList.class);
//        Root<PriceList> priceListRoot = cq.from(PriceList.class);
//
//        Predicate priceByTitle = cb.equal(priceListRoot.get("termOfRent"), term_id);
//        Predicate priceByScooter = cb.equal(priceListRoot.get("scooter"), scootersNumber);
//        Predicate all=cb.and(priceByTitle,priceByScooter);
//
//        cq.where(all);
//
//        return entityManager.createQuery(cq).getSingleResult();

        //JPQL
        Query query = entityManager.createQuery("SELECT p FROM PriceList p WHERE p.termOfRent.id=?1 and p.scooter.number=?2", PriceList.class);
        query.setParameter(1, term_id);
        query.setParameter(2, scootersNumber);

        return (PriceList) query.getSingleResult();
    }

    @Override
    public PriceList read(Long id) {
        return entityManager.find(PriceList.class, id);
    }

    @Override
    public List<PriceList> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PriceList> cq = cb.createQuery(PriceList.class);
        Root<PriceList> priceListRoot = cq.from(PriceList.class);

        CriteriaQuery<PriceList> all = cq.select(priceListRoot);

        return entityManager.createQuery(all).getResultList();
    }


}
