package filrougeaaa;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class ReservationTest {

    //init and clean the connection BDD
    Savepoint save;
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


}
