package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class UserTest {
    Savepoint save;
    //init and clean the connection BDD
    @BeforeAll
    public static void setup(){
        DBManager.init();
        DBManager.setAutoCommit(false);
    }

    @BeforeEach
    public void init(){
        save=DBManager.setSavePoint();
    }

    @AfterEach
    public void done(){
        DBManager.rollback(save);
    }

    @AfterAll
    public static void tearDown(){
        DBManager.close();
    }
    //test database
    @Test
    public void initUserById(){
        User userTest= new User(1);
        assertEquals(userTest.getNickName(), "bg_du_59");
    }

    @Test
    public void TestSaveUser() {
        User user = new User();
        user.setEmail("Example@mail.com");
        user.setNickName("Test");
        user.setPassword("azerty");
        assertTrue(user.save());
    }

    @Test
    public void TestUpdateUser() {
        User user = new User();
        user.setEmail("Example@mail.com");
        user.setNickName("Test");
        user.setPassword("azerty");
        user.save();
        User user2 = new User(user.getId());
        user2.setNickName("ModifTest");
        user2.save();
        user.get();
        assertEquals(user.getNickName(), user2.getNickName());
    }
}
