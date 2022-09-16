package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.Entity.Category;
import aaa.tavern.Entity.Ingredient;
import aaa.tavern.Entity.SubCategory;
import aaa.tavern.utils.HibernateUtil;

public class IngredientTest {
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
    void testCreateIngredient(){
        Ingredient ingredient = new Ingredient() ;
        Category category = new Category() ;
        SubCategory subCategory = new SubCategory() ;

        category.setName("Boissons");
        session.persist(category);

        subCategory.setCategory(category);
        session.persist(subCategory);

        ingredient.setSubCategory(subCategory);
        ingredient.setName("haricot");
        session.persist(ingredient);
        Integer idIng=ingredient.getIdIngredient();

        Ingredient ingredient2=session.find(Ingredient.class, idIng);
        assertEquals(ingredient2.getName() , "haricot");
    }
    @Test
    void testIngredientForSubCategory(){
        Ingredient ingredient = new Ingredient() ;
        Category category = new Category() ;
        SubCategory subCategory = new SubCategory() ;

        category.setName("Boissons");
        session.persist(category);

        subCategory.setCategory(category);
        session.persist(subCategory);

        ingredient.setSubCategory(subCategory);
        assertEquals(ingredient.getSubCategory().getCategory().getName(), "Boissons");
    }
    
    @Test
    public void testSaveIngredient(){
        SubCategory subCategory = new SubCategory();
        subCategory.getCategory().setName("Laitier");
        session.persist(subCategory.getCategory());
        subCategory.setName("Fromage");
        session.persist(subCategory);
        Ingredient ingredient= new Ingredient();
        ingredient.setName("Camenbert");
        ingredient.setLevel(2);
        ingredient.setBuyingPrice(3);
        ingredient.setSubCategory(subCategory);
        session.persist(ingredient);

        boolean assertIngr = false ;
        if(ingredient.getIdIngredient()>0){
            assertIngr = true ;
        }

        assertEquals(assertIngr,true);
    }
    @Test
    public void testUpdateIngredient(){
        SubCategory subCategory = new SubCategory();
        subCategory.getCategory().setName("Laitier");
        session.persist(subCategory.getCategory());
        subCategory.setName("Fromage");
        session.persist(subCategory);

        Ingredient ingredient= new Ingredient();
        ingredient.setName("Camenbert");
        ingredient.setLevel(2);
        ingredient.setBuyingPrice(3);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.getCategory().setName("Laitier");
        session.persist(subCategory2.getCategory());
        subCategory2.setName("Fromage");
        session.persist(subCategory2);

        ingredient.setSubCategory(subCategory2);
        session.persist(ingredient);

        Integer idIng = ingredient.getIdIngredient() ;
        Ingredient ingredient2 = session.getReference(Ingredient.class, idIng);

        ingredient2.setName("Chévre chaud");
        session.persist(ingredient2);

        assertEquals(ingredient.getName(), ingredient2.getName());
    }
    @Test 
    public void ingredientDelete(){
        Ingredient ingredient= new Ingredient();
        ingredient.setName("Camenbert");
        ingredient.setLevel(2);
        ingredient.setBuyingPrice(3);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.getCategory().setName("Laitier");
        session.persist(subCategory2.getCategory());
        subCategory2.setName("Fromage");
        session.persist(subCategory2);

        ingredient.setSubCategory(subCategory2);
        session.persist(ingredient);

        Integer idIng = ingredient.getIdIngredient() ;
        Ingredient ingredient2 = session.getReference(Ingredient.class, idIng);
        session.remove(ingredient2);
        Ingredient ingredient3=session.find(Ingredient.class,idIng);

        assertNull(ingredient3);
    }
}
