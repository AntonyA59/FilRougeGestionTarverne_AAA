package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Time;
import org.hibernate.*;
import org.junit.jupiter.api.*;
import filrougeaaa.utils.HibernateUtil;


public class CustomerTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Session created");
    }
    
    @AfterEach
    public void closeSession() {
        if (session != null){
            session.getTransaction().rollback();
            session.close();
        }
        System.out.println("Session closed\n");
    }

    @Test
    public void testGetCustomer(){
        Customer customer = new Customer();
        customer.setAlcoholTolerance(30f);

        assertEquals(customer.getAlcoholTolerance(), 30f);
    }

    @Test
    public void saveCustomerAtDb(){
        Customer customer = new Customer();
        customer.setPurseOfGold(100);
        customer.setHappiness(10f);
        customer.setHunger(40f);
        customer.setThirst(30f);
        customer.setNauseaLevel(10f);
        customer.setToilet(10f);
        customer.setNauseaTolerance(40f);
        customer.setAlcoholTolerance(50f);
        customer.setTimeInTavern(new Time(20));
        customer.setGender(1);
        customer.setExpGiven(10);
        customer.setAlcoholLevel(10f);
        session.persist(customer);
        Integer custId=customer.getCustomerId();
        assertTrue(custId>0);
    }
    @Test
    public void updateCustomer(){
        TableRest table = new TableRest();
        Customer customer = new Customer();
        customer.setPurseOfGold(100);
        customer.setHappiness(10f);
        customer.setHunger(40f);
        customer.setThirst(30f);
        customer.setNauseaLevel(10f);
        customer.setToilet(10f);
        customer.setNauseaTolerance(40f);
        customer.setAlcoholTolerance(50f);
        customer.setTimeInTavern(new Time(20));
        customer.setGender(1);
        customer.setExpGiven(10);
        customer.setAlcoholLevel(10f);
        customer.setTableRest(table);
        session.persist(customer);
        Integer custId=customer.getCustomerId();

        Customer customer2 = session.getReference(Customer.class, custId);
        customer2.setAlcoholLevel(20f);
        session.persist(customer2);

        Customer customer3= session.getReference(Customer.class, custId);
        assertEquals(customer3.getAlcoholLevel(), 20f);
    }
    

}
