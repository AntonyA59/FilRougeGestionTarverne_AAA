package filrougeaaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Savepoint;
import java.sql.Time;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filrougeaaa.utils.DBManager;

public class CustomerTest {
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
    public void testSave(){
        save = DBManager.setSavePoint();
    }
    @AfterEach
    public void testRollback(){
        DBManager.rollback(save);
    }

    @Test
    public void testGetCustomer(){
        Customer customer = new Customer();
        customer.setAlcoholTolerance(30);
        customer.save();

        assertEquals(customer.getAlcoholTolerance(), 30);
    }

    @Test
    public void saveCustomerAtDb(){
        Customer customer = new Customer();
        customer.setPurseOfGold(100);
        customer.setHappiness(10);
        customer.setHunger(40);
        customer.setThirst(30);
        customer.setNausea(10);
        customer.setToilet(10);
        customer.setNauseaTolerance(40);
        customer.setAlcoholTolerance(50);
        customer.setTimeInTavern(new Time(20));
        customer.setGender(1);
        customer.setExpGiven(10);
        customer.setAlcohol(10);
        assertTrue(customer.save());
    }
    @Test
    public void updateCustomer(){
        TableRest table = new TableRest();
        table.place.manager.user.save();
        table.place.manager.save();
        table.place.save();
        table.save();
        Customer customer = new Customer();
        customer.setPurseOfGold(100);
        customer.setHappiness(10);
        customer.setHunger(40);
        customer.setThirst(30);
        customer.setNausea(10);
        customer.setToilet(10);
        customer.setNauseaTolerance(40);
        customer.setAlcoholTolerance(50);
        customer.setTimeInTavern(new Time(20));
        customer.setGender(1);
        customer.setExpGiven(10);
        customer.setAlcohol(10);
        customer.setTable(table);
        customer.save();
        Customer customer2 = new Customer(customer.getId());
        customer2.setAlcohol(20);
        customer2.save();
        customer.get();
        assertEquals(customer2.getAlcohol(), customer.getAlcohol());
    }
    

}
