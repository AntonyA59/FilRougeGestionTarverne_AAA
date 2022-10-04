package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.exception.ForbiddenException;





@SpringBootTest
public class RecipeServiceTest {
    
    @Autowired
    private RecipeService recipeService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private CustomerRepository customerRepository;


    @Test
    public void requestRecipeSuccessful() throws EntityNotFoundException,ForbiddenException{
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findByIdAndLevelLessThanEqual(1,1)).thenReturn(optRecipe);
        
        //init manager
        Manager manager2 =new Manager();
        manager2.setLevel(1);
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 5);
        manager2.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager2);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);

        recipeService.prepareRecipe(1,1,1);
        
        assertEquals(manager2.getIngredientQuantity().get(ingredient3), 1);
    }

    @Test
    public void requestRecipeRecipeNotFound(){
        //init recipe
        
        Optional<Recipe> optRecipe= Optional.empty();
        Mockito.when(recipeRepository.findByIdAndLevelLessThanEqual(1,1)).thenReturn(optRecipe);
        
        //init manager
        Manager manager2 =new Manager();
        manager2.setLevel(1);
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager2.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager2);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(ForbiddenException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void requestRecipeCustomerNotFound(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Manager manager2 =new Manager();
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager2.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager2);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Optional<Customer> optCustomer= Optional.empty();        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(EntityNotFoundException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void requestRecipeManagerNotFound(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Optional<Manager> optManager= Optional.empty();
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

         //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(EntityNotFoundException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }


    @Test
    public void requestRecipeFailure(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Manager manager2 =new Manager();
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager2.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager2);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(ForbiddenException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }
}
