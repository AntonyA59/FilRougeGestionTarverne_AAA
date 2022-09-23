package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.sql.Time;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.entity.Category;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.utils.HibernateUtil;

public class RecipeTest {
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

    @Test
    public void testRecipeForSubCategory(){
        Recipe recipe = new Recipe() ;
        Category category = new Category() ;
        SubCategory subCategory = new SubCategory() ;

        category.setName("Boissons");
        session.persist(category);

        subCategory.setCategory(category);
        session.persist(subCategory);

        recipe.setSubCategory(subCategory);
        session.persist(recipe);

        Integer idRec = recipe.getIdRecipe() ;
        Recipe recipe2 = session.getReference(Recipe.class, idRec);

        assertEquals(recipe2.getSubCategory().getCategory().getName(), "Boissons");
    }

    //test CRUD
    @Test
    public void testCreateRecipe(){
        Recipe recipe = new Recipe();
        Category cat = new Category() ;
        SubCategory subCat = new SubCategory() ;

        session.persist(cat);
        subCat.setCategory(cat);
        session.persist(subCat);

        recipe.setSubCategory(subCat);
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Time(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);

        session.persist(recipe);

        boolean assertRecip = false ;
        if(recipe.getIdRecipe()>0){
            assertRecip = true ;
        }

        assertEquals(assertRecip,true);
    }
    @Test 
    public void testReadRecipe(){
        Recipe recipe = new Recipe();
        Category cat = new Category() ;
        SubCategory subCat = new SubCategory() ;

        session.persist(cat);
        subCat.setCategory(cat);
        session.persist(subCat);

        recipe.setSubCategory(subCat);
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Time(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);

        session.persist(recipe);
        Integer idRecipe= recipe.getIdRecipe();
        Recipe recipe2=session.find(Recipe.class,idRecipe);
        assertEquals(recipe2.getLevel(), recipe.getLevel());
    }
    @Test
    public void testUpdateRecipe(){
        Recipe recipe = new Recipe();
        Category cat = new Category() ;
        SubCategory subCat = new SubCategory() ;

        session.persist(cat);
        subCat.setCategory(cat);
        session.persist(subCat);

        recipe.setSubCategory(subCat);
        
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Time(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);
        session.persist(recipe);

        Integer idRec = recipe.getIdRecipe() ;
        Recipe recipe2 = session.getReference(Recipe.class, idRec);

        recipe2.setName("Tagliatelle");
        session.persist(recipe2);
        Recipe recipe3=session.find(Recipe.class,idRec);


        assertEquals(recipe2.getName(), recipe3.getName());
    }
    @Test 
    public void testDeleteRecipe(){
        Recipe recipe = new Recipe();
        Category cat = new Category() ;
        SubCategory subCat = new SubCategory() ;

        session.persist(cat);
        subCat.setCategory(cat);
        session.persist(subCat);

        recipe.setSubCategory(subCat);
        
        recipe.setName("Tagliatelle au Saumon");
        recipe.setSellingPrice(15);
        recipe.setLevel(3);
        recipe.setConsommationTime(new Time(10));
        recipe.setPreparationTime(new Time(10));
        recipe.setPeremptionDate(new Date(1000));
        recipe.setExpGiven(30);
        session.persist(recipe);

        Integer idRec = recipe.getIdRecipe() ;
        Recipe recipe2 = session.getReference(Recipe.class, idRec);
        session.remove(recipe2);
        Recipe recipe3=session.find(Recipe.class,idRec);
        assertNull(recipe3);
    }
    /*
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
        recipe.save();
        recipe.setTabIngredients(listeIngredient);
        recipe.save();
        Recipe recipe2=new Recipe(recipe.getId());
        assertEquals(recipe2.getTabIngredients().get(1), 2);
    }
    */
}
