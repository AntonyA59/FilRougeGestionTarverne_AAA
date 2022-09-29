package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
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

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.entity.TableRest;
import aaa.tavern.service.utils.ListRecipe;

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
        tableRest.setIdTable(1);
        Optional<TableRest> optTableRest= Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        customerManagementService.assignNewTable(1, 1);

        //à voir avec Loic
        Mockito.verify(customerRepository).save(ArgumentMatchers.argThat(customer2->customer2.getTableRest().getIdTable()==1));
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
        SubCategory subCategory= new SubCategory();
        subCategory.setIdSubCategory(1);
        ArrayList<RecipeIngredient> tabIngredientsForRecipe = new ArrayList<RecipeIngredient>();
        Recipe recipe1= new Recipe("recipe1", Integer.valueOf(1), Integer.valueOf(1), new Time(1l), new Time(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe1.setIdRecipe(1);
        listTest.put(recipe1.getIdRecipe(), recipe1);

        RecipeDto recipeDto1= new RecipeDto(recipe1);
        listTestDto.add(recipeDto1);
        
        Recipe recipe2= new Recipe("recipe2", Integer.valueOf(1), Integer.valueOf(1), new Time(1l), new Time(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe2.setIdRecipe(2);
        recipe2.setName("recipe2");
        listTest.put(recipe2.getIdRecipe(), recipe2);
        RecipeDto recipeDto2= new RecipeDto(recipe2);
        listTestDto.add(recipeDto2);
        
        Recipe recipe3= new Recipe("recipe3", Integer.valueOf(1), Integer.valueOf(1), new Time(1l), new Time(1l), new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
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
