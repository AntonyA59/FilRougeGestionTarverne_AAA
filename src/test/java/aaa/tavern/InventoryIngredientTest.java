package aaa.tavern;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.Entity.Category;
import aaa.tavern.Entity.Ingredient;
import aaa.tavern.Entity.InventoryIngredient;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.SubCategory;
import aaa.tavern.Entity.Player;
import aaa.tavern.utils.HibernateUtil;


public class InventoryIngredientTest {
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

        Player player = new Player("test@test.com", "test", "test");
        session.persist(player);

        Manager manager = new Manager("test", 10, 10, 10, 10, player);
        session.persist(manager);
        InventoryIngredient ingredient2 = new InventoryIngredient();
        ingredient2.setIngredient(ingredient);
        ingredient2.setManager(manager);
        ingredient2.setQuantity(2);
        session.persist(ingredient2);
        Integer id = ingredient2.getId().getIngredientId();
        Integer id2 = ingredient2.getId().getManagerId();

        boolean idCreate = false;
        if(id > 0 && id2 > 0){
            idCreate = true;
        }

        assertTrue(idCreate);

    

        
        

        
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
