package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.jupiter.api.*;

import aaa.tavern.Entity.Player;
import aaa.tavern.utils.HibernateUtil;



public class PlayerTest {
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
    public void testCreatePlayer() {
        Player player = new Player("test@test.fr", "Test", "Test");
        session.persist(player);
        boolean assertPlayer = false ;
        if(player.getPlayerId() > 0){
            assertPlayer = true ;
        }
        assertEquals(assertPlayer,true);
    }

    @Test 
    public void testReadPlayer(){
        Player player = new Player("test@test.fr", "TestNickName", "Test");
        session.persist(player);
        Integer idplayer=player.getPlayerId();
        Player player2 = session.find(Player.class,idplayer);
        assertEquals(player2.getNickname(), "TestNickName");
    }

    @Test 
    public void testUpdatePlayer(){
        Player player = new Player("test@test.fr", "TestNickName", "Test");
        session.persist(player);
        Integer idPlayer=player.getPlayerId();
        Player player2 = session.find(Player.class,idPlayer);
        player2.setNickname("otherTest");
        session.persist(player2);
        Player player3=session.find(Player.class, idPlayer);
        assertEquals(player3.getNickname(), player2.getNickname());
    }

    @Test
    public void testDeletePlayer(){
        Player player = new Player("test@test.fr", "TestNickName", "Test");
        session.persist(player);
        Integer idPlayer=player.getPlayerId();
        Player player2 = session.find(Player.class,idPlayer);
        session.remove(player2);
        Player player3=session.find(Player.class, idPlayer);
        assertNull(player3);
    }
}
