package haba713;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyTest {

    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        sessionFactory.close();
    }

    private Session session;

    @Before
    public void setUp() throws Exception {
        session = sessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        session.close();
    }

    @Test
    public void test() {
        Transaction transaction = session.beginTransaction();
        MyEntity entity = new MyEntity();
        session.persist(entity);
        assertNotNull(entity.getId());
        transaction.commit();
    }

}
