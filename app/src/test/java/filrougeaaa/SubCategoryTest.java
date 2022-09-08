package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import filrougeaaa.utils.HibernateUtil;

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

    @Test
    void testInsertSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        Category category = new Category() ;

        subCategory.setName("Bières");
        category.setName("Boissons");

        session.persist(category);
        subCategory.setCategory(category);
        session.persist(subCategory);

        Integer idSubCat = subCategory.getIdSubCategory() ;
        SubCategory category2 = session.getReference(SubCategory.class, idSubCat);

        assertEquals(category2.getName(), "Bières");
    }
}
