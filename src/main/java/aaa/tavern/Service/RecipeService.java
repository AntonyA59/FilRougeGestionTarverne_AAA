package aaa.tavern.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeCustomerRepository;
import aaa.tavern.dao.RecipeIngredientRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeCustomerRepository recipeCustomerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void prepareRecipe(int idManager, Integer idRecipe ,Integer idCustomer) throws EntityNotFoundException,ForbiddenException{
        Recipe recipe=ServiceUtil.getEntity(recipeRepository, idRecipe);
        Customer customer= ServiceUtil.getEntity(customerRepository, idCustomer);
        Manager manager= ServiceUtil.getEntity(managerRepository, idManager);

        requestRecipe(manager, recipe, customer);
    }

    //a voir si besoin 
    public List<RecipeIngredient> ListIngredientForRecipe(Integer idRecipe) throws EntityNotFoundException{        
        Recipe recipe=ServiceUtil.getEntity(recipeRepository, idRecipe);
        List<RecipeIngredient> listGet= recipeIngredientRepository.findByRecipe(recipe);

        return listGet; 
    }

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
