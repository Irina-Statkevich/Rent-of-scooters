package statkevich.scooters.dao.hibernate;

import statkevich.scooters.entity.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Users.class);
                configuration.addAnnotatedClass(Roles.class);
                configuration.addAnnotatedClass(Sellers.class);
                configuration.addAnnotatedClass(TypesProducers.class);
                configuration.addAnnotatedClass(TypeOfScooter.class);
                configuration.addAnnotatedClass(Scooters.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(TermOfRent.class);
                configuration.addAnnotatedClass(Rent.class);
                configuration.addAnnotatedClass(PriceList.class);
                configuration.addAnnotatedClass(Producer.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                //sessionFactory.getProperties().get("hibernate.dialect")
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public Session getSession(){
        return getSessionFactory().openSession();
    }
}
