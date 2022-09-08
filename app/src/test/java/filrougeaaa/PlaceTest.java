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




public class PlaceTest {
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
        Place place = new Place() ;
        Manager manager = new Manager() ;
        User user = new User() ;

        user.save() ;
        manager.setUser(user);
        manager.save() ;

        place.setManager(manager);
        place.setName("Cuisine");
        place.save() ;
        assertEquals(place.getName() , "Cuisine");
    }

    @Test
    public void testSavePlace(){
        Place place = new Place();
        place.getManager().getUser().save();
        place.getManager().save();
        place.setLevel(2);
        place.setName("Grenier");
        place.setType(1);
        assertTrue(place.save());
    }

    @Test
    public void testUpdatePlace(){
        Place place = new Place();
        place.getManager().getUser().save();
        place.getManager().save();
        place.setLevel(2);
        place.setName("Grenier");
        place.setType(1);
        place.save();
        Place place2 = new Place(place.getId());
        place2.setName("Célier");
        place2.save();
        place.get();
        assertEquals(place.getName(), place2.getName());
    }
}
