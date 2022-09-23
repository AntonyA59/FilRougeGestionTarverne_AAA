package aaa.tavern.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ShopServiceTest {
    
    @MockBean
	ManagerRepository managerRepository;

    @MockBean
    IngredientRepository ingredientRepository;
	
	@Autowired 
	ShopService shopService ;
    
    @Test
    public void givenIngredient_WhenToAddInInventoryWithoutGold_ThenReturnForbiddenException(){
        Optional<Manager> manager = Optional.empty();

        Mockito.when(managerRepository.findById(0)).thenReturn(manager) ;
        //InventoryIngredient InventoryIngredient = new InventoryIngredient() ;        

        Ingredient ingredient = Mockito.mock(Ingredient.class) ;

        Mockito.when(ingredientRepository.findById(0)).thenReturn(Optional.of(ingredient)) ;
        assertThrows(EntityNotFoundException.class, () -> shopService.Buy(0));
    }
}
