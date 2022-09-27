package aaa.tavern.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.TableRest;
import aaa.tavern.Service.utils.ListRecipe;
import aaa.tavern.dto.RecipeDto;

@SpringBootTest
public class CustomerManagementServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TableRestRepository tableRestRepository;

    @Autowired
    private CustomerManagementService customerManagementService;

    @MockBean
    private ListRecipe listRecipe;

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

    @Test
    public void verifyReturnNewRecipeRandom(){
        Map<Integer,Recipe> listTest = new HashMap<Integer,Recipe>();
        List<RecipeDto> listTestDto = new ArrayList<RecipeDto>();
        
        Recipe recipe1= new Recipe();
        recipe1.setIdRecipe(1);
        recipe1.setName("recipe1");
        listTest.put(recipe1.getIdRecipe(), recipe1);
        //set tout les infos de la recipe //////////////////////////////////////////////////////////////
        RecipeDto recipeDto1= new RecipeDto(recipe1);
        listTestDto.add(recipeDto1);
        
        Recipe recipe2= new Recipe();
        recipe2.setIdRecipe(2);
        recipe2.setName("recipe2");
        listTest.put(recipe2.getIdRecipe(), recipe2);
        RecipeDto recipeDto2= new RecipeDto(recipe2);
        listTestDto.add(recipeDto2);
        
        Recipe recipe3= new Recipe();
        recipe3.setIdRecipe(3);
        recipe3.setName("recipe3");
        listTest.put(recipe3.getIdRecipe(), recipe3);
        RecipeDto recipeDto3= new RecipeDto(recipe3);
        listTestDto.add(recipeDto3);

        Mockito.when(listRecipe.getListRecipe()).thenReturn(listTest);
        RecipeDto recipeDto=customerManagementService.getNewRecipe();

        assertTrue(listTestDto.contains(recipeDto));
    }

}
