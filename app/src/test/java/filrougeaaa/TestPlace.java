package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import filrougeaaa.utils.DBManager;




public class TestPlace {
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
    void testGetPlace(){
        Place place = new Place(1) ;
        assertEquals(place.getName() , "Cuisine");
    }

    @Test
    public void testSavePlace(){
        Place place = new Place();
        place.setLevel(2);
        place.setName("Grenier");
        place.setType(1);
        place.setManager(new Manager(1));
        assertTrue(place.save());
    }

    @Test
    public void testUpdatePlace(){
        Place place = new Place();
        place.setLevel(2);
        place.setName("Grenier");
        place.setType(1);
        place.setManager(new Manager(1));
        place.save();
        Place place2 = new Place(place.getId());
        place2.setName("Célier");
        place2.save();
        place.get();
        assertEquals(place.getName(), place2.getName());
    }
    @Test
    public void TableIsOccupiedWithoutCustomer(){
        Place place = new Place() ;
        Table table = new Table() ;
    
        table.setNumberPlace(5);
        table.setPlace(place);

        assertEquals(table.TableOccupied(),false) ;
    }
    @Test
    public void TableIsReservedWithoutCustomer(){
        Place place = new Place() ;
        Table table = new Table() ;
    
        table.setNumberPlace(5);
        table.setPlace(place);

        assertEquals(table.TableIsReserved(),false) ;
    }
}
