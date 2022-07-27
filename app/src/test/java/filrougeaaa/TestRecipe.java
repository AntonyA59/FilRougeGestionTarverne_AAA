package filrougeaaa;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class TestRecipe {
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
    void testConstucteurRecipe(){
        Recipe recipe = new Recipe(1) ;
        assertEquals(recipe.getName() , "Gruit");
    }
    @Test
    void testRecipeForSubCategory(){
        Recipe recipe = new Recipe(1) ;
        assertEquals(recipe.getSubCategory().getCategory().getName(), "Boissons");
    }
    @Test
    void testRecipeIdFalse(){
        Recipe recipe = new Recipe(1000) ;
        assertNull(recipe.getName());
    }
    @Test
    void testGetRecipe(){
        Recipe recipe = new Recipe() ;
        recipe.get(2) ;
        assertEquals(recipe.getName() , "Cervoise");
    }
}
