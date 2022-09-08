package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class TableTest {
    //init and clean the connection BDD
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
    //test database
    public void testGetTable(){
        Table table= new Table();
        table.setNumberPlace(2);
        assertEquals(table.getNumberPlace(), 2);
    }

    @Test
    public void testSaveTable() {
        Table table = new Table();
        table.getPlace().getManager().getUser().save();
        table.getPlace().getManager().save();
        table.getPlace().save();
        table.setHygiene(30);
        table.setNumberPlace(3);
        table.setPosX(15);
        table.setPosY(30);
        assertTrue(table.save());
    }

    @Test
    public void testUpdateTable() {
        Table table = new Table();
        table.getPlace().getManager().getUser().save();
        table.getPlace().getManager().save();
        table.getPlace().save();
        table.setHygiene(30);
        table.setNumberPlace(3);
        table.setPlace(new Place(1));
        table.setPosX(15);
        table.setPosY(30);
        table.save();
        Table table2 = new Table(table.getId());
        table2.setHygiene(20);
        table2.save();
        table.get();
        assertEquals(table.getHygiene(), table2.getHygiene());
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
        reservation.setDate(date);
        reservation.save() ;
        customer.setTable(table);
        customer.save() ;
        table.tableIsReserved() ;// pour le debug
        assertTrue(table.tableIsReserved()) ;
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
