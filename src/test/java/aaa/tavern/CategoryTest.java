package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.entity.Category;
import aaa.tavern.utils.HibernateUtil;

public class CategoryTest {
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
    public void testCreateCategory(){
        Category category = new Category() ;

        category.setName("Boissons");
        session.persist(category);

        Integer idCat = category.getIdCategory() ;
        
        assertTrue(idCat>0);
    }
    @Test 
    public void testReadCategory(){
        Category category = new Category() ;

        category.setName("Boissons");
        session.persist(category);

        Integer idCat = category.getIdCategory() ;
        Category category2 = session.getReference(Category.class, idCat);
        
        assertEquals(category2.getName() , "Boissons");
    }
    @Test
    public void testUpdateCategory(){
        Category category = new Category() ;

        category.setName("Boissons");
        session.persist(category);

        Integer idCat = category.getIdCategory() ;
        Category category2 = session.getReference(Category.class, idCat); 
        category2.setName("testUpdate");
        session.persist(category2);
        Category category3=session.find(Category.class, idCat);
        assertEquals(category3.getName(), "testUpdate");
    }
    @Test 
    public void testDeleteCategory(){
        Category category = new Category() ;

        category.setName("Boissons");
        session.persist(category);

        Integer idCat = category.getIdCategory() ;
        Category category2 = session.getReference(Category.class, idCat); 
        session.remove(category2);
        Category category3=session.find(Category.class, idCat);
        assertNull(category3);
    }
}
