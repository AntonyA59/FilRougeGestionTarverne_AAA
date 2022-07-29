package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
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

        assertFalse(table.tableOccupied()) ;
    }
    @Test
    public void TableIsReservedWithoutCustomer(){
        Place place = new Place() ;
        Table table = new Table() ;
    
        place.save() ;
        table.setNumberPlace(5);
        table.setPlace(place);
        table.save() ;

        assertFalse(table.tableIsReserved()) ;
    }
    @Test
    public void TableIsOccupiedWithOneCustomer(){
        Place place = new Place() ;
        Table table = new Table() ;
        Manager manager = new Manager() ;
        User user = new User() ;
        Customer customer = new Customer() ;
    
        user.save() ;
        manager.setUser(user);
        manager.save() ;
        place.setManager(manager);
        place.save() ;
        table.setNumberPlace(5);
        table.setPlace(place);
        table.save() ;
        customer.setTable(table);
        customer.save() ;

        assertTrue(table.tableOccupied()) ;
    }
    @Test
    public void TableIsReservedWithCustomer(){
        Place place = new Place() ;
        Table table = new Table() ;
        Manager manager = new Manager() ;
        User user = new User() ;
        Customer customer = new Customer() ;
        Reservation reservation = new Reservation() ;
        Date date = new Date(0) ;
    
        user.save() ;
        manager.setUser(user);
        manager.save() ;
        place.setManager(manager);
        place.save() ;
        table.setNumberPlace(5);
        table.setPlace(place);
        table.save() ;
        customer.save() ;
        reservation.setCustomer(customer);
        reservation.setManager(manager);
        reservation.setNb_customer(1);
        reservation.setDate(date);
        reservation.save() ;
        customer.setTable(table);
        //assertTrue(table.TableIsReserved()) ;
    }
    @Test
    public void TestNumberOfSeatsAvailable(){
        Place place = new Place() ;
        Table table = new Table() ;
        Manager manager = new Manager() ;
        User user = new User() ;
        Customer customer = new Customer() ;
    
        user.save() ;
        manager.setUser(user);
        manager.save() ;
        place.setManager(manager);
        place.save() ;
        table.setNumberPlace(5);
        table.setPlace(place);
        table.save() ;
        customer.setTable(table);
        customer.save() ;

        assertEquals(table.numberOfSeatsAvailable(),4) ;
    }
}
