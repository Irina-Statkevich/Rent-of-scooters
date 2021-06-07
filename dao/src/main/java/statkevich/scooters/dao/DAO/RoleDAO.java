package statkevich.scooters.dao.DAO;

import statkevich.scooters.dao.IDao.IRoleDao;
import statkevich.scooters.entity.entities.Roles;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
//public class RoleDAO  implements IRoleDao{

public class RoleDAO extends GenericDaoImpl<Roles> implements IRoleDao { //

    @Override
    public Roles read(final Long id) {
        //native query
        Query query = entityManager.createNativeQuery("SELECT * FROM roles WHERE id=?1", Roles.class);
        query.setParameter(1, id);
        return (Roles) query.getSingleResult();
        //       return entityManager.find(Roles.class, id);
    }

    @Override
    public Roles readByTitle(final String title) {

//        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//        CriteriaQuery<Roles> cq=cb.createQuery(Roles.class);//тип возвращаемых данных
//        Root<Roles> role = cq.from(Roles.class);//корневой объект, от которого производится обход дерева свойств при накладывании
//                                                // ограничений или указании что выбирать.
//        Predicate roleByTitle = cb.equal(role.get("title"), title);
//        cq.where(roleByTitle);
//
//        return entityManager.createQuery(cq).getSingleResult();

        //native query
        Query query = entityManager.createNativeQuery("SELECT * FROM roles WHERE title=?1", Roles.class);
        query.setParameter(1, title);

        return (Roles) query.getSingleResult();

        //return (Roles) entityManager.createQuery("Select r from Roles r where r.title=?1").setParameter(1,title).getSingleResult();
    }

    @Override
    public Roles create(Roles role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public List<Roles> readAll() {
        return null;
    }
}
