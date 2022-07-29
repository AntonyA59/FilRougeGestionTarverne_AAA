package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Map<Integer,Integer> inventoryManager ;

        inventoryManager = manager.listInventoryIngredient() ;
        assertEquals(inventoryManager.get(5),2) ;
      
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
    public void testRecipeOrderDeleteInInventory(){
        Manager manager= new Manager(1);
        HashMap<Integer,Integer> newInventaire=new HashMap<Integer,Integer>();
        newInventaire.put(1,10);
        newInventaire.put(2,6);
        manager.setInventory(newInventaire);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(1,5);
        recipe.setTabIngredients(ingredientsRecipe);        
        manager.requestRecipe(recipe);
        assertEquals(manager.getInventory().get(1), 5);
    }

    @Test
    public void testBuyIngredient(){
        Manager manager = new Manager(2);
        Ingredient ingredient1 = new Ingredient(2);
        Ingredient ingredient2 = new Ingredient(6);

        manager.listInventoryIngredient() ;
        manager.buyIngredient(ingredient1.getId()) ;
        manager.buyIngredient(ingredient2.getId()) ;
        assertEquals(manager.getInventory().get(6),4) ;
    }
    @Test
    public void testRecipeOrderUpdateInInventoryInBDD(){
        Manager manager= new Manager(1);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(1,2);
        recipe.setTabIngredients(ingredientsRecipe);
        manager.requestRecipe(recipe);
        Manager manager2=new Manager(1);
        assertEquals(manager2.getInventory().get(1), 1);
    }
    @Test
    public void testRecipeOrderDeleteInInventoryInBDD(){
        Manager manager= new Manager(1);
        Recipe recipe=new Recipe();
        HashMap<Integer,Integer> ingredientsRecipe=new HashMap<Integer,Integer>();
        ingredientsRecipe.put(1,3);
        recipe.setTabIngredients(ingredientsRecipe);
        manager.requestRecipe(recipe);
        assertNull(manager.getInventory().get(1));
    }
    
    
}
