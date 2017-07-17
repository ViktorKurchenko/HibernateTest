package hibernate;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class H2Test {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;

	@BeforeClass
	public static void init() throws FileNotFoundException, SQLException {
		emf = Persistence.createEntityManagerFactory("mnf-pu-test");
		em = emf.createEntityManager();
	}

	@Before
	public void initializeDatabase(){
		Session session = em.unwrap(Session.class);
		/*session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/data.sql").getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});*/
	}

	@AfterClass
	public static void tearDown(){
		em.clear();
		em.close();
		emf.close();
	}

	/*@Test
	public void testGetObjectById_success() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("123");
		em.persist(book);
		Book book2 = em.find(Book.class, 1);
		assertNotNull(book2);
	}*/

}
