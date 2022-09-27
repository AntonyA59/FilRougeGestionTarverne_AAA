package aaa.tavern.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.DAO.CustomerRepository;
import aaa.tavern.DAO.TableRestRepository;
import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.TableRest;

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

        Mockito.verify(tableRestRepository).save(ArgumentMatchers.argThat(tableRest2->tableRest2.getNumberPlace()==4));
    }

    @Test
    public void modifedCustomerTableIdWithAssignNewTable(){
        Customer customer= new Customer();
        Optional<Customer> optCustomer= Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        TableRest tableRest= new TableRest();
        tableRest.setNumberPlace(5);
        tableRest.setTableId(1);
        Optional<TableRest> optTableRest= Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        customerManagementService.assignNewTable(1, 1);

        //à voir avec Loic
        Mockito.verify(customerRepository).save(ArgumentMatchers.argThat(customer2->customer2.getTableRest().getTableId()==1));
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
