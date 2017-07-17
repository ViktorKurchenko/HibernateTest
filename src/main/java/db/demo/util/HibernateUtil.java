package db.demo.util;

import db.demo.security.Action;
import db.demo.security.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Group.class)
                    .addAnnotatedClass(Action.class)
                    .buildSessionFactory();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
