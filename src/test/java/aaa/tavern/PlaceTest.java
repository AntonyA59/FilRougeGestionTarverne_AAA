package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;
import aaa.tavern.entity.Player;
import aaa.tavern.utils.HibernateUtil;

public class PlaceTest {
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

    //test CRUD 

    //atention soucis

    @Test
    public void testCreatePlace(){
        Player player = new Player("test@test.com", "test", "test");
        session.persist(player);
        Manager manager = new Manager("Test", 10, 10, 1, 20, player);
        session.persist(manager);
        Place place=new Place();
        place.setManager(manager);
        place.setLevel(2);
        place.setName("test");
        place.setType(1);
        session.persist(place);
        Integer placeId= place.getPlaceId();
        assertTrue(placeId>0);
    }
}
