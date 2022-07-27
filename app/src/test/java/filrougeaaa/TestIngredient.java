package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filrougeaaa.utils.DBManager;

public class TestIngredient {
    Savepoint save = null ;

    @BeforeAll
    static void testInitDBManager(){
        DBManager.init();
        DBManager.setAutoCommit(false);
    }
    @AfterAll
    public static void tearDown(){
        DBManager.setAutoCommit(true);
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
    void testConstucteurIngredient(){
        Ingredient ingredient = new Ingredient(1) ;
        assertEquals(ingredient.getName() , "Pâte");
    }
    @Test
    void testIngredientForSubCategory(){
        Ingredient ingredient = new Ingredient(1) ;
        assertEquals(ingredient.getSubCategory().getCategory().getName(), "Autres");
    }
    @Test
    void testIngredientIdFalse(){
        Ingredient ingredient = new Ingredient(1000) ;
        assertNull(ingredient.getName());
    }
    @Test
    void testGetIngredient(){
        Ingredient ingredient = new Ingredient() ;
        ingredient.get(2) ;
        assertEquals(ingredient.getName() , "Gruit");
    } 
    
    @Test
    public void testSaveIngredient(){
        Ingredient ingredient= new Ingredient();
        ingredient.setName("Camenbert");
        ingredient.setLevel(2);
        ingredient.setBuyingPrice(3);
        ingredient.setSubCategory(new SubCategory(7));
        assertTrue(ingredient.save());
    }
    @Test
    public void testUpdateIngredient(){
        Ingredient ingredient= new Ingredient();
        ingredient.setName("Camenbert");
        ingredient.setLevel(2);
        ingredient.setBuyingPrice(3);
        ingredient.setSubCategory(new SubCategory(7));
        ingredient.save();
        Ingredient ingredient2 = new Ingredient(ingredient.getId());
        ingredient2.setName("Chévre chaud");
        ingredient2.save();
        ingredient.get();
        assertEquals(ingredient.getName(), ingredient2.getName());

    }
}
