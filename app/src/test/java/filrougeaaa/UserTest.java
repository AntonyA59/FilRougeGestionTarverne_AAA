package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    
    //test CRUD
    @Test
    public void testCreateUser() {
        User user = new User("test@test.fr", "Test", "Test");
        session.persist(user);
        boolean assertUser = false ;
        if(user.getUserId() > 0){
            assertUser = true ;
        }
        assertEquals(assertUser,true);
    }

    @Test 
    public void testReadUser(){
        User user = new User("test@test.fr", "TestNickName", "Test");
        session.persist(user);
        Integer idUser=user.getUserId();
        User user2 = session.find(User.class,idUser);
        assertEquals(user2.getNickname(), "TestNickName");
    }

    @Test 
    public void testUpdateUser(){
        User user = new User("test@test.fr", "TestNickName", "Test");
        session.persist(user);
        Integer idUser=user.getUserId();
        User user2 = session.find(User.class,idUser);
        user2.setNickname("otherTest");
        session.persist(user2);
        User user3=session.find(User.class, idUser);
        assertEquals(user3.getNickname(), user2.getNickname());
    }

    @Test
    public void testDeleteUser(){
        User user = new User("test@test.fr", "TestNickName", "Test");
        session.persist(user);
        Integer idUser=user.getUserId();
        User user2 = session.find(User.class,idUser);
        session.remove(user2);
        User user3=session.find(User.class, idUser);
        assertNull(user3);
    }
}
