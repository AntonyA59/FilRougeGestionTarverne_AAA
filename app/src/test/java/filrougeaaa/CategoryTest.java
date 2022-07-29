package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class CategoryTest {
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
    void testConstucteurCategory(){
        Category category = new Category() ;
        category.setName("Boissons");
        category.save();
        assertEquals(category.getName() , "Boissons");
    }
    @Test
    void testGetCategory(){
        Category category = new Category() ;
        category.get(2) ;
        assertEquals(category.getName() , "Plats");
    }
}
