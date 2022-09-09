package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    /**
     * 
     */
    @Test
    public void insertManager(){
        Customer customer = new Customer(100, 
        100f, 
        100f, 
        100f,
        100f, 
        100f, 
        100f, 
        100f, 
        100f, 
        true, 
        100);

        session.persist(customer);
        boolean assertCustomer = false ;
        if(customer.getCustomerId() > 0){
            assertCustomer = true ;
        }
        assertEquals(assertCustomer,true);

}
}