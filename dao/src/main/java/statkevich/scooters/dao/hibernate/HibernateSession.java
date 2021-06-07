package statkevich.scooters.dao.hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSession {

    @Autowired
    private HibernateSessionFactoryUtil sessionUtil;

    @Autowired
    public Session getSession(){
        return sessionUtil.getSession();
    }

}
