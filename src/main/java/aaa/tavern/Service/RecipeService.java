package aaa.tavern.Service;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.CustomerRepository;
import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.DAO.RecipeCustomerRepository;
import aaa.tavern.DAO.RecipeRepository;
import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.Ingredient;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeCustomer;
import aaa.tavern.Entity.RecipeIngredient;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private RecipeCustomerRepository recipeCustomerRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * High-level methods to create the recipe you want to launch
     * @param idManager id of the manager who wants to make the recipe
     * @param idRecipe id of the recipe we want to make
     * @param idCustomer id of the customer for whom we want to make the recipe
     * @throws EntityNotFoundException One of the entities was not found in the database
     * @throws ForbiddenException the manager does not have enough ingredients to make the recipe
     */
    @Transactional(rollbackOn = {EntityNotFoundException.class,ForbiddenException.class}) 
    public void prepareRecipe(int idManager, int idRecipe ,int idCustomer) throws EntityNotFoundException,ForbiddenException{

        Customer customer= ServiceUtil.getEntity(customerRepository, idCustomer);
        Manager manager= ServiceUtil.getEntity(managerRepository, idManager);
        Optional<Recipe> optRecipe = recipeRepository.findByIdAndLevelLessThanEqual(idRecipe, manager.getLevel());

        if (optRecipe.isEmpty())
            throw new ForbiddenException();
        
        Recipe recipe = optRecipe.get();

        requestRecipe(manager, recipe, customer);
    }

    /**
     * We check if the manager has the resources to make the recipe
     * @param manager manager who wants to make the recipe
     * @param recipe recipe we want to make
     * @return  returns (Y/N) if we have the necessary resources
     */
    private boolean haveQuantityIngredientInInventaire(Manager manager,Recipe recipe){
        for (RecipeIngredient recipeIngredient : recipe.getTabIngredientsForRecipe()) {
            Ingredient ingredient= recipeIngredient.getIngredient();
            Integer quantity= recipeIngredient.getQuantity();
            Map<Ingredient, Integer> inventaireManager= manager.getIngredientQuantity();
            if(!inventaireManager.containsKey(ingredient) || inventaireManager.get(ingredient)<quantity)
                return false;   
        }
        return true;
    }

    /**
     * remove the resources from the inventory of the manager then create a RecipeCustomer and finally save
     * @param manager manager who wants to make the recipe
     * @param recipe recipe we want to make
     * @param customer customer for whom we want to make the recipe
     * @throws ForbiddenException the manager does not have enough ingredients to make the recipe
     */
    private void requestRecipe(Manager manager, Recipe recipe,Customer customer) throws ForbiddenException{
        if(haveQuantityIngredientInInventaire(manager,recipe)){
            for (RecipeIngredient recipeIngredient : recipe.getTabIngredientsForRecipe()) {
                Ingredient ingredient= recipeIngredient.getIngredient();
                Integer quantity= recipeIngredient.getQuantity();
                manager.getIngredientQuantity().put(ingredient, manager.getIngredientQuantity().get(ingredient)-quantity);    
            }
            RecipeCustomer recipeCustomer= new RecipeCustomer(recipe,customer);
            recipeCustomerRepository.save(recipeCustomer);
            managerRepository.save(manager);
        } else{
            throw new ForbiddenException();
        }  
    }
}
