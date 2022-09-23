package aaa.tavern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import aaa.tavern.entity.Category;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.utils.HibernateUtil;

public class SubCategoryTest {
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
    public void testCreateSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;

        subCategory.setName("Bières");
        category.setName("Boissons");

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        assertTrue(subCategory.getIdSubCategory()>0);
    }
    @Test 
    public void testReadSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;

        subCategory.setName("Bières");
        category.setName("Boissons");

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        Integer idSubCat = subCategory.getIdSubCategory() ;
        SubCategory subCategory2 = session.getReference(SubCategory.class, idSubCat);

        assertEquals(subCategory2.getName(),subCategory.getName());
    }

    @Test
    public void testUpdateSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;

        subCategory.setName("Bières");
        category.setName("Boissons");

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        Integer idSubCat = subCategory.getIdSubCategory() ;
        SubCategory category2 = session.getReference(SubCategory.class, idSubCat);
        category2.setName("Vin");
        session.persist(category2);
        SubCategory category3=session.find(SubCategory.class,idSubCat);
        assertEquals(category3.getName(), category2.getName());
    }

    @Test 
    public void testDeleteSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;

        subCategory.setName("Bières");
        category.setName("Boissons");

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        Integer idSubCat = subCategory.getIdSubCategory() ;
        SubCategory subCategory2 = session.getReference(SubCategory.class, idSubCat);
        session.remove(subCategory2);
        SubCategory subCategory3=session.find(SubCategory.class,idSubCat);
        assertNull(subCategory3);
    }
}
