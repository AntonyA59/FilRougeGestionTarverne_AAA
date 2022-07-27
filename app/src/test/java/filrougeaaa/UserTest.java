package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
}
