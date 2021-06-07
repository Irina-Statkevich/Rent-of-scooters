package statkevich.scooters.dao.DAO;

import statkevich.scooters.dao.IDao.IScooterDao;
import statkevich.scooters.entity.entities.Scooters;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ScootersDAO extends GenericDaoImpl<Scooters> implements IScooterDao {

    private static final Logger logger = Logger.getLogger(ScootersDAO.class);

    @Override
    public Scooters read(final Long number) {
        return entityManager.find(Scooters.class, number);
    }

    @Override
    public Scooters readByModel(String model) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooters> cq = cb.createQuery(Scooters.class);
        Root<Scooters> scooters = cq.from(Scooters.class);

        Predicate scooterByModel = cb.equal(scooters.get("model"), model);
        cq.where(scooterByModel);

        return entityManager.createQuery(cq).getSingleResult();
        //return entityManager.createQuery("Select r from Scooters r where r.model=?1",Scooters.class).setParameter(1,model).getSingleResult();
    }

    @Override
    public List<Scooters> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooters> cq = cb.createQuery(Scooters.class);
        Root<Scooters> root = cq.from(Scooters.class);

        CriteriaQuery<Scooters> all = cq.select(root);

        return entityManager.createQuery(all).getResultList();
        //return  entityManager.createQuery("Select s from Scooters s").getResultList();
    }

    @Override
    public List<Scooters> readFree(LocalDate date) {

//        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//        CriteriaQuery<Scooters> cq=cb.createQuery(Scooters.class);
//        Root<Scooters> root = cq.from(Scooters.class);
//
//        Join<Scooters, Rent> rent = root.join("scooter_number");
//
//        CriteriaQuery<Scooters> all = cq.select(root);
//        cq.where(cb.isNull(rentRoot.get("dateEnd")));
//
//        return Optional.ofNullable(getSingleResult(criteriaQuery));


//SQL - скутеры в аренде и дата ожидаемого возврата их
//        /**/
//        /*select scooters.model,alldoRent.* from scooters left join - сводная таблица, удобная для сравнения*/
//        select scooters.* from scooters left join
//        (
//                /*вся аренда, что еще не вернули с ожидаемым сроком*/
//                select rentWithTermId.scooter_number,rentWithTermId.date_start,rentWithTermId.date_end,
//                term_of_rent.count_of_days,rentWithTermId.date_start +term_of_rent.count_of_days*interval'1 day' as date_must_end from
//                (
//                        /*вся аренда, что еще не вернули с ид term*/
//                        select inRent.*,price_list.term_id from
//                                /*вся аренда, что еще не вернули*/
//                                        (
//                                                select * from rent where rent.date_end isnull
//                                        ) as inRent
//                        /**/
//                        left join price_list on inRent.price_id=price_list.id
//                        /**/
//                )as rentWithTermId
//        left join term_of_rent on rentWithTermId.term_id=term_of_rent.id
//        /**/
//		) as alldoRent on scooters.number=alldoRent.scooter_number
//        where ((alldoRent.date_start isnull)or(alldoRent.date_must_end<='2020-01-04')or(alldoRent.date_start>'2020-01-04'))

        logger.info(date);

        String myQuery =
                "select scooters.* from scooters left join" +
                        "(" +
                        "        select rentWithTermId.scooter_number,rentWithTermId.date_start,rentWithTermId.date_end," +
                        "        term_of_rent.count_of_days,rentWithTermId.date_start +term_of_rent.count_of_days*interval'1 day' as date_must_end from" +
                        "        (" +
                        "                select inRent.*,price_list.term_id from" +
                        "                                (" +
                        "                                        select * from rent where rent.date_end isnull" +
                        "                                ) as inRent " +
                        "                left join price_list on inRent.price_id=price_list.id" +
                        "        )as rentWithTermId " +
                        "left join term_of_rent on rentWithTermId.term_id=term_of_rent.id" +
                        ") as alldoRent on scooters.number=alldoRent.scooter_number" +
                        " where ((alldoRent.date_start isnull)or(alldoRent.date_must_end<='" + date + "')or(alldoRent.date_start>'" + date + "'))";


        logger.info(myQuery);
        Query query = entityManager.createNativeQuery(myQuery);

        List<Object[]> resultList = query.getResultList();

        List<Scooters> result = new ArrayList<>(resultList.size());
        for (Object[] row : resultList) {
            result.add(readByModel((String) row[1]));
        }

        return result;

        //return entityManager.createQuery(all).getResultList();

    }


}
