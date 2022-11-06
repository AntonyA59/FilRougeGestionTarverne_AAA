package aaa.tavern.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityNotFoundException;

import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ShopControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShopService shopService;

	//////// TEST BYING /////////
	@Test
	public void GivenIngredientAndManager_whenPostBuing_ThenPrepareIngredientAndShopServiceCalled() throws Exception {

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopBying")
				.param("idManager", "1")
				.param("idIngredient", "1");

		mockMvc.perform(query);

		Mockito.verify(shopService).prepareIngredientAndBuy(1, 1);
	}

	@Test
	public void GivenNotParam_whenPostBuing_ThenPrepareIngredientAndBuyServiceNotCalled() throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopBying");

		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 400);
	}

	@Test
	public void GivenIngredientAndManager_whenMockToReturnEntityNotFoundExceptionForBying_ThenReturnError406()
			throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopBying")
				.param("idManager", "1")
				.param("idIngredient", "1");

		Mockito.doThrow(EntityNotFoundException.class).when(shopService).prepareIngredientAndBuy(1, 1);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	@Test
	public void GivenIngredientAndManager_whenMockToReturnForbiddenException_ThenReturnError406() throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopBying")
				.param("idManager", "1")
				.param("idIngredient", "1");

		Mockito.doThrow(ForbiddenException.class).when(shopService).prepareIngredientAndBuy(1, 1);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	////////// SELLING ////////////

	@Test
	public void GivenIngredientAndManager_whenPostSelling_ThenPrepareIngredientAndShopServiceCalled() throws Exception {

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopSelling")
				.param("idManager", "1")
				.param("idIngredient", "1");

		mockMvc.perform(query);

		Mockito.verify(shopService).prepareIngredientAndSell(1, 1);
	}

	@Test
	public void GivenNotParamForSelling_whenPostSelling_ThenPrepareIngredientAndBuyServiceNotCalled() throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopSelling");

		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 400);
	}

	@Test
	public void GivenIngredientAndManager_whenMockToReturnEntityNotFoundExceptionForPostSelling_ThenReturnError406()
			throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopSelling")
				.param("idManager", "1")
				.param("idIngredient", "1");

		Mockito.doThrow(EntityNotFoundException.class).when(shopService).prepareIngredientAndSell(1, 1);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	@Test
	public void GivenIngredientAndManager_whenMockToReturnForbiddenExceptionForPostSelling_ThenReturnError406()
			throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/testShopSelling")
				.param("idManager", "1")
				.param("idIngredient", "1");

		Mockito.doThrow(ForbiddenException.class).when(shopService).prepareIngredientAndSell(1, 1);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}
}
