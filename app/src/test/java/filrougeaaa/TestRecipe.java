package filrougeaaa;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        recipe.setPreparationTime(new Time(10));
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
        recipe.setPreparationTime(new Time(10));
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

    @Test
    public void testListRecipeByLevel(){
        Recipe recipe = new Recipe() ;
        ArrayList<Recipe> listRecipe ;

        listRecipe = recipe.listRecipeByLevel(2) ;
        assertEquals(listRecipe.get(0).getName(),"Gruit") ;
    }
    @Test 
    public void updateRecipeBDD(){
        Recipe recipe= new Recipe();
        HashMap<Integer,Integer> listeIngredient=new HashMap<Integer,Integer>();
        listeIngredient.put(1,2);
        listeIngredient.put(2, 5);
        recipe.setName("fraise");
        recipe.setSellingPrice(5);
        recipe.setLevel(1);
        Time timeConsom= new Time(52321555);
        recipe.setConsommationTime(timeConsom);
        Time timePrepar=new Time(525623);
        recipe.setPreparationTime(timePrepar);
        recipe.setExpGiven(5);
        Category category= new Category();
        category.save();
        SubCategory subcat= new SubCategory();
        subcat.setCategory(category);
        subcat.save();
        recipe.setSubCategory(subcat);
        recipe.setTabIngredients(listeIngredient);
        recipe.save();
        Recipe recipe2=new Recipe(recipe.getId());
        assertEquals(recipe2.getTabIngredients().get(1), 2);

    }
}
