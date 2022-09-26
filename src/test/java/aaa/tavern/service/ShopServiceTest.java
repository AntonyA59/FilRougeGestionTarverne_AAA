package aaa.tavern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ShopServiceTest {
    
    @MockBean
	ManagerRepository managerRepository;

    @MockBean
    IngredientRepository ingredientRepository;
	
	@Autowired 
	ShopService shopService ;
    
}
