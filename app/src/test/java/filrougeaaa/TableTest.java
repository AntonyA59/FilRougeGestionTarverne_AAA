package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Savepoint;

import org.junit.jupiter.api.*;


public class TableTest {


/*     @Test
    public void TableIsOccupiedWithoutCustomer(){
        Place place = new Place() ;
        TableRest table = new TableRest() ;
    
        table.setNumberPlace(5);
        table.setPlace(place);

        assertFalse(table.tableOccupied()) ;
    }
    @Test
    public void TableIsReservedWithoutCustomer(){
        Place place = new Place() ;
        TableRest table = new TableRest() ;
        User user = new User() ;
        Manager manager = new Manager() ;
    
        user.save() ;
        manager.setUser(user);
        manager.save();

        place.setManager(manager);

        place.save() ;
        table.setNumberPlace(5);
        table.setPlace(place);
        table.save() ;

        assertFalse(table.tableIsReserved()) ;
    }
    @Test
    public void TableIsOccupiedWithOneCustomer(){
        Place place = new Place() ;
        TableRest table = new TableRest() ;
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
    public void TestNumberOfSeatsAvailable(){
        Place place = new Place() ;
        TableRest table = new TableRest() ;
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
    } */
}
