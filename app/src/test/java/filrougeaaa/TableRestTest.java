package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Savepoint;

import org.hibernate.*;
import org.junit.jupiter.api.*;

import filrougeaaa.utils.HibernateUtil;


public class TableRestTest {
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
    public void testCreateTable(){
        User user = new User("test@test.com", "test", "test");
        session.persist(user);
        Manager manager = new Manager("Test", 10, 10, 1, 20, user);
        session.persist(manager);
        Place place=new Place();
        place.setManager(manager);
        place.setLevel(2);
        place.setName("test");
        place.setType(1);
        session.persist(place);
        TableRest tableRest=new TableRest();
        tableRest.setHygiene(2f);
        tableRest.setNumberPlace(2);
        tableRest.setPlace(place);
        tableRest.setPosX(2f);
        tableRest.setPosY(5f);
        session.persist(tableRest);
        Integer tableId=tableRest.getTableId();
        assertTrue(tableId>0);
    }

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
