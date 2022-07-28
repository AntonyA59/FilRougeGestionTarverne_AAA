package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        DBManager.close();
    }

    @BeforeEach
    void testSave() {
        save = DBManager.setSavePoint();
    }

    @AfterEach
    void testRollback() {
        DBManager.rollback(save);
    }

    @Test
    public void testGetCustomer() {
        Manager manager = new Manager(1);

        assertEquals(manager.getName(), "Théodebald");
    }

    @Test
    public void testSaveManager() {
        Manager manager = new Manager();
        manager.setName("Norman");
        manager.setReputation(3);
        manager.setChest(400);
        manager.setLevel(1);
        manager.setExp(2);
        manager.setUser(new User(1));
        assertTrue(manager.save());
    }

    @Test
    public void testUpdateManager() {
        Manager manager = new Manager();
        manager.setName("Norman");
        manager.setReputation(3);
        manager.setChest(400);
        manager.setLevel(1);
        manager.setExp(2);
        manager.setUser(new User(2));
        manager.save();
        Manager manager2 = new Manager(manager.getId());
        manager2.setExp(4);
        manager2.save();
        manager.get();
        assertEquals(manager.getExp(), manager2.getExp());
    }
    // Test des Map by Adrien // Inventaire du Manager
    @Test
    public void testInventoryIngredient(){
        Manager manager = new Manager(1) ;
        Map<Integer,Integer> inventoryManager ;

        inventoryManager = manager.listInventoryIngredient() ;
        assertEquals(inventoryManager.get(4),2) ;
    }
}
