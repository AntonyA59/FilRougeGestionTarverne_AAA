package aaa.tavern.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.RecipeService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:test.properties")
public class RecipeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RecipeService recipeService;

  @Test
    public void givenCorrectParam_WhenPostRequestRecipe_thenReturn200() throws Exception{

        
        
        doNothing().when(recipeService).prepareRecipe(1,1,1);
        
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                                                    .post("/api/recipe/requestRecipe")
                                                    .param("managerId", "1")
                                                    .param("recipeId", "1")
                                                    .param("customerId","1" );

        int status = mockMvc
                        .perform(query)
                        .andReturn()
                        .getResponse()
                        .getStatus();
        

        assertEquals(200, status);
    }

  @Test
    public void givenCorrectParamButNotPresentBDD_whenPostRequestRecipe_thenReturn404() throws Exception{
		
        Mockito.doThrow(EntityNotFoundException.class).when(recipeService).prepareRecipe(1, 1, 1);
		
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/recipe/requestRecipe")
                .param("managerId", "1")
                .param("recipeId", "1")
                .param("customerId", "1");
                
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(404, status);
    }

  @Test
    public void givenCorrectParam_whenPostRequestRecipe_thenReturn400withForbidden() throws Exception{
		
        Mockito.doThrow(ForbiddenException.class).when(recipeService).prepareRecipe(1, 1, 1);
		
        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/recipe/requestRecipe")
                .param("managerId", "1")
                .param("recipeId", "1")
                .param("customerId", "1");
                
		
		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();
		
		assertEquals(400, status);
    }

  @Test
  public void givenIncorrectParam_whenPostRequestRecipe_thenReturn404() throws Exception {

    MockHttpServletRequestBuilder query = MockMvcRequestBuilders
        .post("/api/recipe/requestRecipe");

    int status = mockMvc
        .perform(query)
        .andReturn()
        .getResponse()
        .getStatus();

    assertEquals(400, status);
  }

}
