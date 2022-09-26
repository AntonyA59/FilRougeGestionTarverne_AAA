package aaa.tavern.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.TableRest;

@SpringBootTest
public class CustomerManagementServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TableRestRepository tableRestRepository;

    @Test
    public void modifedTableRest(){
        TableRest tableRest= new TableRest();
        tableRest.setNumberPlace(5);

        tableRest.setNumberPlace(tableRest.getNumberPlace()-1);
        tableRestRepository.save(tableRest);

        Mockito.verify(tableRestRepository).save(ArgumentMatcher.argThat(tableRest->tableRest.getNumberPlace==4));
    }



}
