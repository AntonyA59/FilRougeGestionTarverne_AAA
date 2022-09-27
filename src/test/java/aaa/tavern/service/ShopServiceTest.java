package aaa.tavern.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ShopServiceTest {
    
    @MockBean
	ManagerRepository managerRepository;

    @MockBean
    IngredientRepository ingredientRepository;
	
	@Autowired 
	ShopService shopService ;
    
    ////// FONCTION D'ACHAT ////////

    @Test
    public void givenIngredientToBuy_WhenAllCondition_ThenSuccess() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();
        Ingredient ingredient4=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);
        ingredient4.setBuyingPrice(40);
        ingredient4.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);      
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(40);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient4);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        shopService.prepareIngredientAndBuy(1, 1);

        assertEquals(manager.getIngredientQuantity().get(ingredient4), 1);
    }

    // Ingredient de trop haut niveau
    @Test
    public void givenIngredientToBuy_WhenTooHightLevel_ThenForbiddenException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();
        Ingredient ingredient4=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);
        ingredient4.setBuyingPrice(40);
        ingredient4.setLevel(2);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);        
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient4);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(ForbiddenException.class, ()->shopService.prepareIngredientAndBuy(1, 1));
    }

    // Le Manager n'a pas assez d'argent
    @Test
    public void givenIngredientToBuy_WhenChestEmpty_ThenForbiddenException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();
        Ingredient ingredient4=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);
        ingredient4.setBuyingPrice(40);
        ingredient4.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(39);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient4);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(ForbiddenException.class, ()->shopService.prepareIngredientAndBuy(1, 1));
    }

    // Le Manager selectioné n'existe pas
    @Test
    public void givenIngredientToBuy_WhenManagerEmpty_ThenEntityNotFoundException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();
        Ingredient ingredient4=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);
        ingredient4.setBuyingPrice(40);
        ingredient4.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient4);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(EntityNotFoundException.class, ()->shopService.prepareIngredientAndBuy(2, 1));
    }

    // L'ingrédient selectioné n'existe pas
    @Test
    public void givenIngredientToBuy_WhenIngredientEmpty_ThenEntityNotFoundException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();
        Ingredient ingredient4=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);
        ingredient4.setBuyingPrice(40);
        ingredient4.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient4);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(EntityNotFoundException.class, ()->shopService.prepareIngredientAndBuy(1, 2));
    }

    /////// FONCTION DE VENTE ///////

    @Test
    public void givenIngredientToSell_WhenAllCondition_ThenSuccess() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        shopService.prepareIngredientAndSell(1, 1);

        assertEquals(manager.getIngredientQuantity().get(ingredient1), 2);
    }

    // Ingredient de trop haut niveau
    @Test
    public void givenIngredientToSell_WhenTooHightLevel_ThenForbiddenException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(2);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);        
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(ForbiddenException.class, ()->shopService.prepareIngredientAndSell(1, 1));
    }

    // L'ingrédient selectioné n'existe pas
    @Test
    public void givenIngredientToSell_WhenIngredientEmpty_ThenEntityNotFoundException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(EntityNotFoundException.class, ()->shopService.prepareIngredientAndSell(1, 2));
    }

    // Le Manager selectioné n'existe pas
    @Test
    public void givenIngredientToSell_WhenManagerEmpty_ThenEntityNotFoundException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(EntityNotFoundException.class, ()->shopService.prepareIngredientAndSell(2, 1));
    }

    //Le Manager ne possède pas l'Ingredient
    @Test
    public void givenIngredientToSell_WhenManagerNotGetIngredient_ThenForbiddenException() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        assertThrows(ForbiddenException.class, ()->shopService.prepareIngredientAndSell(1, 1));
    }

    //Le Manager retire le dernier type d'un ingrédient de son inventaire
    @Test
    public void givenIngredientToSell_WhenManagerGetOneIngredient_ThenInventoryEmpty() throws EntityNotFoundException, ForbiddenException{
        Ingredient ingredient1=new Ingredient();

        ingredient1.setBuyingPrice(10);
        ingredient1.setLevel(1);

        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 1);
        //init manager
        Manager manager =new Manager();
        manager.setChest(200);
        manager.setLevel(1);       
        manager.setIngredientQuantity(ingredientQuantity);

        //Mokito
        Optional<Manager> optManager= Optional.of(manager);
        Optional<Ingredient> optIngredient= Optional.of(ingredient1);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
        Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

        shopService.prepareIngredientAndSell(1, 1) ;

        assertEquals(ingredientQuantity.size(), 0);
    }
}
