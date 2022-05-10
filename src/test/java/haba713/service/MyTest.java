package haba713.service;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import haba713.MyEntity;

public class MyTest {

    private static org.hibernate.cfg.Configuration hibernateConfig;
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        hibernateConfig = new org.hibernate.cfg.Configuration().configure();
        sessionFactory = hibernateConfig.buildSessionFactory();
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
