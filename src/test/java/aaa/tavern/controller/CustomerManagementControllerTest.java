package aaa.tavern.controller;

import java.util.ArrayList;
import java.util.HashSet;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.entity.TableRest;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.dto.received.AssignNewTableForCustomerDto;
import aaa.tavern.dto.received.CustomerFinishDto;
import aaa.tavern.dto.received.CustomerServedDto;
import aaa.tavern.dto.received.ManagerIdDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.CustomerManagementService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerManagementService customerManagementService;

    // Test NewRecipe
    @Test
    public void givenCorrectBody_WhenPostNewRecipe_thenReturnRecipeDto() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        SubCategory subCategory = new SubCategory();
        subCategory.setIdSubCategory(1);
        Recipe recipe = new Recipe("test", Integer.valueOf(1), Integer.valueOf(1), Long.valueOf(1l), Long.valueOf(1l),
                new Date(1664488800l), Integer.valueOf(1), subCategory, new ArrayList<RecipeIngredient>());
        recipe.setId(1);
        
        RecipeDto recipeDto = new RecipeDto(recipe);

        Mockito.when(customerManagementService.getNewRecipe(1)).thenReturn(recipeDto);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newRecipe")
                .contentType("application/json")
                .content(body);

        MvcResult result = mockMvc.perform(query).andReturn();
        String returnedJsonStr = result.getResponse().getContentAsString();

        JsonNode returnedJson = objectMapper.readTree(returnedJsonStr);

        String jsonStr = """
                {
                    "id": 1 ,
                    "name":"test",
                    "sellingPrice":1,
                    "level":1,
                    "consommationTime": 1,
                    "preparationTime": 1,
                    "peremptionDate": 1664488800,
                    "expGiven": 1,
                    "idSubCategory": 1,
                    "tabIngredientsForRecipe":{}
                }
                """;
        JsonNode expectedJson = objectMapper.readTree(jsonStr);

        assertEquals(expectedJson, returnedJson);
    }

    @Test
    public void givenCorrectBody_whenPostNewRecipe_thenReturn200() throws Exception {

        SubCategory subCategory = new SubCategory();
        subCategory.setIdSubCategory(1);
        Recipe recipe = new Recipe("test", Integer.valueOf(1), Integer.valueOf(1), Long.valueOf(1l), Long.valueOf(1l),
                new Date(1664488800l), Integer.valueOf(1), subCategory, new ArrayList<RecipeIngredient>());
        recipe.setId(1);
        RecipeDto recipeDto = new RecipeDto(recipe);

        Mockito.when(customerManagementService.getNewRecipe(1)).thenReturn(recipeDto);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        ObjectMapper objectMapper = new ObjectMapper();

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newRecipe")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void givenCorrectBodyButNotPresentBDD_whenPostNewRecipe_thenReturn404() throws Exception {

        Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        ObjectMapper objectMapper = new ObjectMapper();

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newRecipe")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(404, status);
    }

    @Test
    public void givenIncorrectBody_whenPostNewRecipe_thenReturn400() throws Exception {

        Mockito.when(customerManagementService.getNewRecipe(1)).thenThrow(EntityNotFoundException.class);

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newRecipe");

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }

    // newCustomer

    @Test
    public void givenCorrectBody_WhenPostNewCustomer_thenReturnCustomerDto() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TableRest tableRest = new TableRest();
        tableRest.setIdTable(1);
        HashSet<RecipeCustomer> tabCustomer = new HashSet<RecipeCustomer>();
        Customer customer = new Customer(Integer.valueOf(1), Float.valueOf(1f),
                Float.valueOf(1f), Float.valueOf(1f),
                Float.valueOf(1f), Float.valueOf(1f),
                Float.valueOf(1f), new Time(1l),
                Float.valueOf(1f), Float.valueOf(1f),
                true, Integer.valueOf(1),
                tableRest, tabCustomer, null);
        customer.setIdCustomer(1);
        CustomerDto customerDto = new CustomerDto(customer);

        Mockito.when(customerManagementService.getNewCustomer(1)).thenReturn(customerDto);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newCustomer")
                .contentType("application/json")
                .content(body);

        MvcResult result = mockMvc.perform(query).andReturn();
        String returnedJsonStr = result.getResponse().getContentAsString();

        JsonNode returnedJson = objectMapper.readTree(returnedJsonStr);

        String jsonStr = """
                {
                    "id": 1 ,
                    "purseOfGold":1,
                    "happiness": 1.0,
                    "hunger":1.0,
                    "thirst":1.0,
                    "nauseaLevel":1.0,
                    "alcoholLevel":1.0,
                    "toilet":1.0,
                    "timeInTavern": "01:00:00",
                    "nauseaTolerance": 1.0,
                    "alcoholTolerance": 1.0,
                    "gender": true,
                    "expGiven": 0,
                    "idTableRest": 1,
                    "consommationStart": null ,
                    "commandList": []
                    }
                    """;
        JsonNode expectedJson = objectMapper.readTree(jsonStr);

        assertEquals(expectedJson, returnedJson);
    }

    @Test
    public void givenCorrectBody_whenPostNewCustomer_thenReturn200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        TableRest tableRest = new TableRest();
        tableRest.setIdTable(1);
        HashSet<RecipeCustomer> tabCustomer = new HashSet<RecipeCustomer>();
        Customer customer = new Customer(Integer.valueOf(1), Float.valueOf(1f),
                Float.valueOf(1f), Float.valueOf(1f),
                Float.valueOf(1f), Float.valueOf(1f),
                Float.valueOf(1f), new Time(1l),
                Float.valueOf(1f), Float.valueOf(1f),
                true, Integer.valueOf(1),
                tableRest, tabCustomer, null);
        customer.setIdCustomer(1);
        CustomerDto customerDto = new CustomerDto(customer);

        Mockito.when(customerManagementService.getNewCustomer(1)).thenReturn(customerDto);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newCustomer")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void givenCorrectBodyButNotPresentBDD_whenPostNewCustomer_thenReturn404() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(customerManagementService.getNewCustomer(1)).thenThrow(EntityNotFoundException.class);

        ManagerIdDto jsonDto = new ManagerIdDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newCustomer")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(404, status);
    }

    @Test
    public void givenIncorrectBody_whenPostNewCustomer_thenReturn404() throws Exception {

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/newCustomer");

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }

    // Test controller customerAssignTable
    @Test
    public void givenCorrectBody_WhenPostCustomerAssignTable_thenReturn200() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        AssignNewTableForCustomerDto jsonDto = new AssignNewTableForCustomerDto(1, 1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerAssignTable")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void givenCorrectBodyButNotPresentBDD_whenPostCustomerAssignTable_thenReturn404() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AssignNewTableForCustomerDto jsonDto = new AssignNewTableForCustomerDto(1, 10);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        Mockito.doThrow(EntityNotFoundException.class).when(customerManagementService).assignNewTable(1, 10);

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerAssignTable")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(404, status);
    }

    @Test
    public void givenIncorrectBody_whenPostCustomerAssignTable_thenReturn400() throws Exception {

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerAssignTable");

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }

    // Test customerServed
    @Test
    public void givenCorrectBody_WhenPostCustomerServed_thenReturn200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerServedDto jsonDto = new CustomerServedDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerServed")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void givenCorrectBodyButNotPresentBDD_whenPostCustomerServed_thenReturn404() throws Exception {

        Mockito.doThrow(EntityNotFoundException.class).when(customerManagementService).customerServed(1);

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerServedDto jsonDto = new CustomerServedDto(1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerServed")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(404, status);
    }

    @Test
    public void givenIncorrectBody_whenPostCustomerServed_thenReturn404() throws Exception {

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerServed");

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }

    // test customerFinish

    @Test
    public void givenCorrectBody_WhenPostCustomerFinish_thenReturn200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerFinishDto jsonDto = new CustomerFinishDto(1, 1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerFinish")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void givenCorrectBodyButNotPresentBDD_whenPostCustomerFinish_thenReturn404() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerFinishDto jsonDto = new CustomerFinishDto(1, 1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        Mockito.doThrow(EntityNotFoundException.class).when(customerManagementService).customerFinishRecipe(1, 1);

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerFinish")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(404, status);
    }

    @Test
    public void givenCorrectParam_whenPostCustomerFinish_thenReturn400withForbidden() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerFinishDto jsonDto = new CustomerFinishDto(1, 1);

        String body = objectMapper
                .valueToTree(jsonDto)
                .toPrettyString();

        Mockito.doThrow(ForbiddenException.class).when(customerManagementService).customerFinishRecipe(1, 1);

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerFinish")
                .contentType("application/json")
                .content(body);

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }

    @Test
    public void givenIncorrectParam_whenPostCustomerFinish_thenReturn404() throws Exception {

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/api/game/customerManagement/customerFinish");

        int status = mockMvc
                .perform(query)
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(400, status);
    }
}
