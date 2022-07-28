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

public class TestRecipeCustomer {
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
    public void testSaveRecipeCustomer(){
        RecipeCustomer recipeCustomer = new RecipeCustomer();
        recipeCustomer.setCustomer(new Customer(1));
        recipeCustomer.setQuantity(4);
        recipeCustomer.setRecipe(new Recipe(1));
        assertTrue(recipeCustomer.save());
    }

    @Test
    public void testUpdateRecipeCustomer(){
        RecipeCustomer recipeCustomer = new RecipeCustomer();
        recipeCustomer.setCustomer(new Customer(1));
        recipeCustomer.setQuantity(4);
        recipeCustomer.setRecipe(new Recipe(1));
        recipeCustomer.save();
        RecipeCustomer recipeCustomer2 = new RecipeCustomer(recipeCustomer.getId());
        recipeCustomer2.setQuantity(2);
        recipeCustomer2.save();
        recipeCustomer.get();
        assertEquals(recipeCustomer2.getQuantity(), recipeCustomer.getQuantity());
    }

}
