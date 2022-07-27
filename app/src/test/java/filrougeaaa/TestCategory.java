package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class TestCategory {
    Savepoint save = null ;

    @BeforeAll
    static void testInitDBManager(){
        DBManager.init();
        DBManager.setAutoCommit(false);
    }
    @AfterAll
    public static void tearDown(){
        DBManager.setAutoCommit(true);
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
    void testGetCategory(){
        Category category = new Category(1) ;
        assertEquals(category.getName() , "Boissons");
    }

}
