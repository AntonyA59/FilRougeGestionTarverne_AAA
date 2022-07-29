package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Savepoint;
import java.sql.Time;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filrougeaaa.utils.DBManager;

public class TestReservation {
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
    public void testSaveReservation() {
        User user = new User();
        Customer customer = new Customer();
        customer.save();
        Manager manager = new Manager();
        manager.save();
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setDate(new Date(20));
        reservation.setManager(manager);
        reservation.setNb_customer(3);
        assertTrue(reservation.save());
    }

    @Test
    public void testUpdateReservation() {
        Customer customer = new Customer();
        customer.save();
        Manager manager = new Manager();
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
}
