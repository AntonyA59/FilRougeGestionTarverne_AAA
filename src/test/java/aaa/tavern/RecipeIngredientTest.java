package aaa.tavern;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.entity.Category;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.utils.HibernateUtil;

public class RecipeIngredientTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Session created");
    }
     
    @AfterEach
    public void closeSession() {
        if (session != null){
            session.getTransaction().rollback();
            session.close();
        }
        System.out.println("Session closed\n");
    } 

    //test CRUD
    @Test
    void testCreateRecipeIngredient(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;
        Recipe recipe = new Recipe() ;
        Ingredient ingredient = new Ingredient() ;

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        recipe.setSubCategory(subCategory);
        ingredient.setSubCategory(subCategory);
        session.persist(subCategory);
        session.persist(subCategory);

        RecipeIngredient recipeIngredient = new RecipeIngredient() ;
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setQuantity(1);
        session.persist(recipeIngredient);
        Integer recipeId = recipeIngredient.getId().getIdRecipe();
        Integer ingredientId = recipeIngredient.getId().getIdIngredient();

        boolean idCreate = false;
        if(recipeId > 0 && ingredientId > 0){
            idCreate = true;
        }

        assertTrue(idCreate);
    }
    @Test
    public void testReadRecipeIngredient(){

    }
}
