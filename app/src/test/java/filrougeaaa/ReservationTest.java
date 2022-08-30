package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Savepoint;

import org.junit.jupiter.api.*;

import filrougeaaa.utils.DBManager;

public class ReservationTest {

    //init and clean the connection BDD
    Savepoint save;
    @BeforeAll
    public static void setup(){
        DBManager.init();
        DBManager.setAutoCommit(false);
    }

    @BeforeEach
    public void init(){
        save=DBManager.setSavePoint();
    }

    @AfterEach
    public void done(){
        DBManager.rollback(save);
    }

    @AfterAll
    public static void tearDown(){
        DBManager.close();
    }
    @Test
    public void testSaveReservation() {
        Customer customer = new Customer();
        customer.save();
        Manager manager = new Manager();
        manager.getUser().save();
        manager.save();
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setDate(new Date(20));
        reservation.setManager(manager);
        assertTrue(reservation.save());
    }

    /* Test obsolète à cause de la suppréssion du nombre de client(nb_customer) dans une réservation
    @Test
    public void testUpdateReservation() {
        Customer customer = new Customer();
        customer.save();
        Manager manager = new Manager();
        manager.getUser().save();
        manager.save();
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setDate(new Date(20));
        reservation.setManager(manager);
        reservation.setNb_customer(3);
        reservation.save();
        Reservation reservation2 = new Reservation(reservation.getId());
        reservation2.setNb_customer(4);
        reservation2.save();
        reservation.get();
        assertEquals(reservation.getNb_customer(), reservation2.getNb_customer());
    }
    */


}
