package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;



@DataJpaTest
public class ManagerCustomerRepositoryTest {
    @Autowired
    private ManagerCustomerRepository managerCustomerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    @Sql("givenManagerAndCustomer_findByManager_ThenReturnCustomer.sql")
    public void givenManagerAndCustomer_findByManager_ThenReturnCustomer() {
        Optional<Manager> managerOpt = managerRepository.findById(2);
        Manager manager = managerOpt.get();
        List<ManagerCustomer> managerCustomers = managerCustomerRepository.findByManager(manager);

        assertEquals(managerCustomers.size(), 5);
    }
}
