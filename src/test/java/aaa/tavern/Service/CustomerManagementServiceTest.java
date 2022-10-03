package aaa.tavern.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.DAO.CustomerRepository;
import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.DAO.RecipeRepository;
import aaa.tavern.DAO.TableRestRepository;
import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeIngredient;
import aaa.tavern.Entity.SubCategory;
import aaa.tavern.Entity.TableRest;
import aaa.tavern.dto.RecipeDto;

//whenassertThrow 
@SpringBootTest
public class CustomerManagementServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TableRestRepository tableRestRepository;

    @Autowired
    private CustomerManagementService customerManagementService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private Customer customerMock;
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
    
        Manager manager= new Manager();
        manager.setIdManager(1);
        manager.setLevel(1);
        Optional<Manager> optManager= Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        
        SubCategory subCategory= new SubCategory();
        subCategory.setIdSubCategory(1);

        List<Recipe> listTest = new ArrayList<Recipe>();
        List<RecipeDto> listTestDto = new ArrayList<RecipeDto>();
        ArrayList<RecipeIngredient> tabIngredientsForRecipe = new ArrayList<RecipeIngredient>();
       
        Recipe recipe1= new Recipe("recipe1", Integer.valueOf(1), Integer.valueOf(1), 1L, Long.valueOf(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe1.setId(1);
        RecipeDto recipeDto1= new RecipeDto(recipe1);
        listTest.add(recipe1);
        listTestDto.add(recipeDto1);
        
        Recipe recipe2= new Recipe("recipe2", Integer.valueOf(1), Integer.valueOf(1), 1L, Long.valueOf(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe2.setId(2);
        recipe2.setName("recipe2");
        RecipeDto recipeDto2= new RecipeDto(recipe2);
        listTest.add(recipe2);
        listTestDto.add(recipeDto2);
        
        Recipe recipe3= new Recipe("recipe3", Integer.valueOf(1), Integer.valueOf(1),1l, Long.valueOf(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe3.setId(3);
        recipe3.setName("recipe3");
        RecipeDto recipeDto3= new RecipeDto(recipe3);
        listTest.add(recipe3);
        listTestDto.add(recipeDto3);

        Mockito.when(recipeRepository.findByLevelLessThanEqual(1)).thenReturn(listTest);
        RecipeDto recipeDto=customerManagementService.getNewRecipe(1);

        assertTrue(listTestDto.contains(recipeDto));
    }

    
    @Test
    public void givenCustomerServed_WhenCustomerEat(){

        Optional<Customer> optCutomer= Optional.of(customerMock);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCutomer);
        customerManagementService.customerServed(1);
        
        Mockito.verify(customerMock).setConsommationStart(Mockito.any());
        
        Mockito.verify(customerRepository).save(customerMock);
    }
    

}
