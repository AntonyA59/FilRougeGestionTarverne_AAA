package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;
import java.util.HashMap;
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
    //Test check and delete the ingredients related to the recipe order
    @Test
    public void testRecipeOrderTrue(){
        Manager manager= new Manager(1);
        HashMap<Integer,Integer> newInventaire=new HashMap<Integer,Integer>();
        newInventaire.put(1,10);
        newInventaire.put(2,5);
        manager.setInventory(newInventaire);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(2,4);
        recipe.setTabIngredients(ingredientsRecipe);
        assertTrue(manager.requestRecipe(recipe));
    }
    @Test
    public void testRecipeOrderFalse(){
        Manager manager= new Manager(1);
        HashMap<Integer,Integer> newInventaire=new HashMap<Integer,Integer>();
        newInventaire.put(1,10);
        newInventaire.put(2,5);
        manager.setInventory(newInventaire);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(2,6);
        recipe.setTabIngredients(ingredientsRecipe);
        assertFalse(manager.requestRecipe(recipe));
    }
    @Test
    public void testRecipeOrderDeleteInInventaire(){
        Manager manager= new Manager(1);
        HashMap<Integer,Integer> newInventaire=new HashMap<Integer,Integer>();
        newInventaire.put(1,10);
        newInventaire.put(2,6);
        manager.setInventory(newInventaire);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(2,5);
        recipe.setTabIngredients(ingredientsRecipe);
        assertEquals(manager.getInventory().get(2), 1);
    }
}
