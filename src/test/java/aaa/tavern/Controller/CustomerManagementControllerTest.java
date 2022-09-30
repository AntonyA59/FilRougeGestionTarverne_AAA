package aaa.tavern.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeCustomer;
import aaa.tavern.Entity.RecipeIngredient;
import aaa.tavern.Entity.SubCategory;
import aaa.tavern.Entity.TableRest;
import aaa.tavern.Service.CustomerManagementService;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerManagementService customerManagementService;


    // Test NewRecipe
    @Test 
    public void givenCorrectParam_WhenPostNewRecipe_thenReturnRecipeDto() throws Exception{
        ObjectMapper objectMapper= new ObjectMapper();
        
        SubCategory subCategory= new SubCategory();
        subCategory.setIdSubCategory(1);
        Recipe recipe = new Recipe("test", Integer.valueOf(1), Integer.valueOf(1), Long.valueOf(1l), Long.valueOf(1l), new Date(1664488800l), Integer.valueOf(1), subCategory, new ArrayList<RecipeIngredient>());
        recipe.setIdRecipe(1);
        RecipeDto recipeDto = new RecipeDto(recipe);

        Mockito.when(customerManagementService.getNewRecipe(1)).thenReturn(recipeDto);
    
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                                                    .post("/api/customerManagement/newRecipe")
                                                    .param("managerId", "1");

        MvcResult result = mockMvc.perform(query).andReturn();
        String returnedJsonStr= result.getResponse().getContentAsString();
        
        JsonNode returnedJson = objectMapper.readTree(returnedJsonStr);

        String jsonStr="""
                {
                    "id": 1 ,
                    "name":"test",
                    "sellingPrice":1,
                    "level":1,
                    "consommationTime": 1,
                    "preparationTime": 1,
                    "peremptionDate": 1664488800,
                    "expGiven": 1,
                    "subCategory": 1,
                    "tabIngredientsForRecipe": {}
                }
                """;
        JsonNode expectedJson= objectMapper.readTree(jsonStr);
        
        assertEquals(expectedJson, returnedJson);
    }

    @Test
    public void givenCorrectParam_whenPostNewRecipe_thenReturn200() throws Exception{
		    
        SubCategory subCategory= new SubCategory();
        subCategory.setIdSubCategory(1);
        Recipe recipe = new Recipe("test", Integer.valueOf(1), Integer.valueOf(1), Long.valueOf(1l), Long.valueOf(1l), new Date(1664488800l), Integer.valueOf(1), subCategory, new ArrayList<RecipeIngredient>());
        recipe.setIdRecipe(1);
        RecipeDto recipeDto = new RecipeDto(recipe);

        Mockito.when(customerManagementService.getNewRecipe(1)).thenReturn(recipeDto);
    	
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("managerId", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(200, status);
    }

    @Test
    public void givenCorrectParamButNotPresentBDD_whenPostNewRecipe_thenReturn404() throws Exception{
		
        Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);
		
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("managerId", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(404, status);
    }

    @Test
    public void givenIncorrectParam_whenPostNewRecipe_thenReturn404() throws Exception{
		
       Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);
    	
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("manager", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(400, status);
    }

    // newCustomer
    
    @Test 
    public void givenCorrectParam_WhenPostNewCustomer_thenReturnCustomerDto() throws Exception{
        ObjectMapper objectMapper= new ObjectMapper();
        TableRest tableRest= new TableRest();
        tableRest.setTableId(1);
        Set<RecipeCustomer> tabCustomer= new HashSet<RecipeCustomer>();
        Customer customer = new Customer(Integer.valueOf(1), Float.valueOf(1f),
                                        Float.valueOf(1f) ,Float.valueOf(1f)  ,
                                        Float.valueOf(1f) ,Float.valueOf(1f) , 
                                        Float.valueOf(1f) , new Time(1l),
                                        Float.valueOf(1f) ,Float.valueOf(1f) , 
                                        true, Integer.valueOf(1),
                                        tableRest,tabCustomer);
        customer.setIdCustomer(1);
        CustomerDto customerDto = new CustomerDto(customer);

        Mockito.when(customerManagementService.getNewCustomer(1)).thenReturn(customerDto);
    
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                                                    .post("/api/customerManagement/newCustomer")
                                                    .param("managerId", "1");

        MvcResult result = mockMvc.perform(query).andReturn();
        String returnedJsonStr= result.getResponse().getContentAsString();
        
        JsonNode returnedJson = objectMapper.readTree(returnedJsonStr);

        String jsonStr="""
                {
                    "idCustomer": 1 ,
                    "purseOfGold":1,
                    "happiness": 1,
                    "hunger":1,
                    "thirst":1,
                    "nauseaLevel":1,
                    "alcoholLevel":1,
                    "toilet":1,
                    "timeInTavern": ,

                    private Float nauseaTolerance;

                    private Float alcoholTolerance;
                    
                    private Boolean gender;

                    private Integer expGiven;

                    private TableRest tableRest;

                    private Timestamp consommationStart;

                    private Set<Integer> commandList=new HashSet<Integer>();
                }
                """;
        JsonNode expectedJson= objectMapper.readTree(jsonStr);
        
        assertEquals(expectedJson, returnedJson);
    }
/*
    @Test
    public void givenCorrectParam_whenPostNewRecipe_thenReturn200() throws Exception{
		    
        SubCategory subCategory= new SubCategory();
        subCategory.setIdSubCategory(1);
        Recipe recipe = new Recipe("test", Integer.valueOf(1), Integer.valueOf(1), Long.valueOf(1l), Long.valueOf(1l), new Date(1664488800l), Integer.valueOf(1), subCategory, new ArrayList<RecipeIngredient>());
        recipe.setIdRecipe(1);
        RecipeDto recipeDto = new RecipeDto(recipe);

        Mockito.when(customerManagementService.getNewRecipe(1)).thenReturn(recipeDto);
    	
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("managerId", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(200, status);
    }

    @Test
    public void givenCorrectParamButNotPresentBDD_whenPostNewRecipe_thenReturn404() throws Exception{
		
        Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);
		
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("managerId", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(404, status);
    }

    @Test
    public void givenIncorrectParam_whenPostNewRecipe_thenReturn404() throws Exception{
		
       Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);
    	
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/customerManagement/newRecipe")
                .param("manager", "1");
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(400, status);
    }*/
}
