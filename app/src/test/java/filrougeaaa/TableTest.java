package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class TableTest {
    //init and clean the connection BDD
    Savepoint save = null ;

    @BeforeAll
    static void testInitDBManager(){
        DBManager.init();
        DBManager.setAutoCommit(false);
    }
    @AfterAll
    public static void tearDown(){
        DBManager.close();
    }

    @BeforeEach
    void testSave(){
        save = DBManager.setSavePoint();
    }
    @AfterEach
    void testRollback(){
        DBManager.rollback(save);
    }
    @Test
    //test database
    public void testGetTable(){
        Table table= new Table(1);
        assertEquals(table.getNumberPlace(), 2);
    }

    @Test
    public void testSaveTable() {
        Table table = new Table();
        table.setHygiene(30);
        table.setNumberPlace(3);
        table.setPlace(new Place(1));
        table.setPosX(15);
        table.setPosY(30);
        assertTrue(table.save());
    }

    @Test
    public void testUpdateTable() {
        Table table = new Table();
        table.setHygiene(30);
        table.setNumberPlace(3);
        table.setPlace(new Place(1));
        table.setPosX(15);
        table.setPosY(30);
        table.save();
        Table table2 = new Table(table.getId());
        table2.setHygiene(20);
        table2.save();
        table.get();
        assertEquals(table.getHygiene(), table2.getHygiene());
    }

}
