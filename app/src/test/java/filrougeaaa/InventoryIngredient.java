package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import filrougeaaa.utils.HibernateUtil;

public class InventoryIngredient {
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


    //gros soucis sur inventoryIngredients !! a voir !
    @Test
    public void testCreateInventoryIng(){
        Category category=new Category();
        category.setName("TEST CAT");
        session.persist(category);

        SubCategory subCategory= new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName("test subcat");
        session.persist(subCategory);

        Ingredient ingredient=new Ingredient();
        ingredient.setBuyingPrice(2);
        ingredient.setLevel(1);
        ingredient.setName("test");
        ingredient.setSubCategory(subCategory);
        session.persist(ingredient);


        InventoryIngredient inventoryIngredient= new InventoryIngredient();
        assertTrue(false);
        
    }
    @Test
    public void testReadInventoryIng(){

    }
    @Test
    public void testUpdateInventoryIng(){
        
    }
    @Test
    public void testDeleteInventoryIng(){
        
    }
}
