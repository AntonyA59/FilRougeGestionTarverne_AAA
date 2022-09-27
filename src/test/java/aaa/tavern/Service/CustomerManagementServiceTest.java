package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.TableRest;
import aaa.tavern.service.CustomerManagementService;

@SpringBootTest
public class CustomerManagementServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TableRestRepository tableRestRepository;

    @Autowired
    private CustomerManagementService customerManagementService;

    @Test
    public void modifedTableRestWithAssignNewTable(){
        Customer customer= new Customer();
        Optional<Customer> optCustomer= Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        TableRest tableRest= new TableRest();
        tableRest.setNumberPlace(5);
        Optional<TableRest> optTableRest= Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        customerManagementService.assignNewTable(1, 1);

        //à voir avec Loic
        //Mockito.verify(tableRestRepository).save(ArgumentMatcher.argThat(tableRest->tableRest.getNumberPlace==4));
    }

    @Test
    public void modifedCustomerTableIdWithAssignNewTable(){
        Customer customer= new Customer();
        Optional<Customer> optCustomer= Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        TableRest tableRest= new TableRest();
        tableRest.setNumberPlace(5);
        Optional<TableRest> optTableRest= Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        customerManagementService.assignNewTable(1, 1);

        //à voir avec Loic
        //Mockito.verify(customerRepository).save(ArgumentMatcher.argThat(customer->customer.getTableRest==1));
    }

    @Test
    public void returnEntityNotFoundExceptionInCustomerWithAssignNewTable(){
        Optional<Customer> optCustomer= Optional.empty();
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        TableRest tableRest= new TableRest();
        tableRest.setNumberPlace(5);
        Optional<TableRest> optTableRest= Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        assertThrows(EntityNotFoundException.class, ()-> customerManagementService.assignNewTable(1, 1));
    }

    @Test
    public void returnEntityNotFoundExceptionInTableWithAssignNewTable(){
        Customer customer= new Customer();
        Optional<Customer> optCustomer= Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        Optional<TableRest> optTableRest= Optional.empty();
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        assertThrows(EntityNotFoundException.class, ()-> customerManagementService.assignNewTable(1, 1));
    }



}
