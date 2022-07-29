package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class SubCategoryTest {
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
    void testConstucteurSubCategory(){
        SubCategory subCategory = new SubCategory(1) ;
        assertEquals(subCategory.getName() , "Bières");
    }
    @Test
    void testCategoryForSubCategory(){
        SubCategory subCategory = new SubCategory(1) ;
        assertEquals(subCategory.getCategory().getName(), "Boissons");
    }
    @Test
    void testSubCategory(){
        SubCategory subCategory = new SubCategory() ;
        subCategory.get(2) ;
        assertEquals(subCategory.getName(),"Vins");
    }
}
