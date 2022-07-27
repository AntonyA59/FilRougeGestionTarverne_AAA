package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Savepoint;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filrougeaaa.utils.DBManager;

public class TestManager {
    Savepoint save = null;

    @BeforeAll
    static void testInitDBManager() {
        DBManager.init();
        DBManager.setAutoCommit(false);
    }

    @AfterAll
    public static void tearDown() {
        DBManager.setAutoCommit(false);
        DBManager.close();
    }

    @BeforeEach
    public void testSave() {
        save = DBManager.setSavePoint();
    }

    @AfterEach
    public void testRollback() {
        DBManager.rollback(save);
    }

    @Test
    public void testGetCustomer() {
        Manager manager = new Manager(1);

        assertEquals(manager.getName(), "Théodebald");
    }

    // Test des Map by Adrien // Inventaire du Manager
    @Test
    public void testInventoryIngredient(){
        Manager manager = new Manager(1) ;
        Map<Integer,Integer> inventory ;
        inventory = manager.listIngredientByManager() ;
        inventory.get(1) ;
    }

}
