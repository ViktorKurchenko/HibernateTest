package hibernate;

import db.demo.security.Action;
import db.demo.security.Group;
import db.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by viktor on 17.07.17.
 */
public class HibernateTest {

    @Test
    public void test() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Group book = new Group("admins", "");

        System.out.println(session.save(book));
        Group book1 = session.get(Group.class, 1L);
        assertNotNull(book1);

        Action action = new Action("login", "123");
        System.out.println(session.save(action));
        assertNotNull(session.get(Action.class, 1L));

        HibernateUtil.shutdown();
    }

}
