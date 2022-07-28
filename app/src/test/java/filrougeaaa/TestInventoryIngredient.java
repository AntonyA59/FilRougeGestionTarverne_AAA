package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filrougeaaa.utils.DBManager;

public class TestInventoryIngredient {
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
    public void testSaveInventoryIngredient(){
        InventoryIngredient inventoryIngredient = new InventoryIngredient();
        inventoryIngredient.setIngredient(new Ingredient(1));
        inventoryIngredient.setManager(new Manager(1));
        inventoryIngredient.setQuantity(3);
        assertTrue(inventoryIngredient.save());
    }

    @Test
    public void testUpdateInventoryIngredient(){
        InventoryIngredient inventoryIngredient = new InventoryIngredient();
        inventoryIngredient.setIngredient(new Ingredient(1));
        inventoryIngredient.setManager(new Manager(1));
        inventoryIngredient.setQuantity(3);
        inventoryIngredient.save();
        InventoryIngredient inventoryIngredient2 = new InventoryIngredient(inventoryIngredient.getId());
        inventoryIngredient2.setQuantity(5);
        inventoryIngredient2.save();
        inventoryIngredient.get();
        assertEquals(inventoryIngredient.getQuantity(), inventoryIngredient2.getQuantity());
    }
}
