package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;





import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.HibernateUtil;



public class UserTest {
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
    public void testInsertUser() {
        User user = new User("test@test.fr", "Test", "Test");
        session.persist(user);
        boolean assertUser = false ;
        if(user.getUserId() > 0){
            assertUser = true ;
        }
        assertEquals(assertUser,true);
    }


}
