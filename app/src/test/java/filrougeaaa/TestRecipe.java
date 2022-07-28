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

    @Test
    public void testSaveRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Timestamp(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);
        recipe.setSubCategory(new SubCategory(1));
        assertTrue(recipe.save());
    }

    @Test
    public void testUpdateRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Timestamp(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);
        recipe.setSubCategory(new SubCategory(1));
        recipe.save();
        Recipe recipe2 = new Recipe(recipe.getId());
        recipe2.setName("Tagliatelle");
        recipe2.save();
        recipe.get();
        assertEquals(recipe.getName(), recipe2.getName());
    }
}
