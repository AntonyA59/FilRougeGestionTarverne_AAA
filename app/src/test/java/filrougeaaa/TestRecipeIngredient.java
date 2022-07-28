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

public class TestRecipeIngredient {
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
    public void testSaveRecipeIngredient(){
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(new Ingredient(1));
        recipeIngredient.setQuantity(4);
        recipeIngredient.setRecipe(new Recipe(1));
        assertTrue(recipeIngredient.save());
        
    }

    @Test
    public void testUpdateRecipeIngredient(){
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(new Ingredient(1));
        recipeIngredient.setQuantity(4);
        recipeIngredient.setRecipe(new Recipe(1));
        recipeIngredient.save();
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(recipeIngredient.getId());
        recipeIngredient2.setQuantity(2);
        recipeIngredient2.save();
        recipeIngredient.get();
        assertEquals(recipeIngredient.getQuantity(), recipeIngredient2.getQuantity());
        
    }
}
