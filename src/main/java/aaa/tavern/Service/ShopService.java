package aaa.tavern.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ShopService {
    @Autowired
    ManagerRepository managerRepository ;

    @Autowired
    IngredientRepository ingredientRepository ;

    //retourne la liste des ingredients du jeu en foncion du niveau du Manager
    public List<Ingredient> getAllIngredients(int idManager){
        Manager manager= ServiceUtil.getEntity(managerRepository, idManager);
        return ingredientRepository.findByLevelLessThanEqual(manager.getLevel()) ;
    }

    //Charge l'ingredient et le manager pour ensuite acheter
    public void prepareIngredientAndBuy(int idManager, int idIngredient) throws EntityNotFoundException,ForbiddenException{
        Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository, idIngredient);
        Manager manager= ServiceUtil.getEntity(managerRepository, idManager);

        if(ingredient == null || manager == null)
            throw new EntityNotFoundException();  

        if(ingredient.getLevel() > manager.getLevel())
            throw new ForbiddenException();  
        
        if(!Buy(ingredient,manager))
            throw new ForbiddenException(); 

        Add(ingredient,manager) ;
        
        managerRepository.save(manager) ;
    }    

    //Charge l'ingredient et le manager pour ensuite vendre
    public void prepareIngredientAndSell(int idManager, int idIngredient) throws EntityNotFoundException,ForbiddenException{
        Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository, idIngredient);
        Manager manager= ServiceUtil.getEntity(managerRepository, idManager);

        if(ingredient == null || manager == null)
            throw new EntityNotFoundException();  

        if(ingredient.getLevel() > manager.getLevel())
            throw new ForbiddenException(); 

        Sell(ingredient,manager);

        if(!Remove(ingredient,manager))
            throw new ForbiddenException();
        
        managerRepository.save(manager) ;
    }  

    //Ajoute l'ingredient dans l'inventaire
    private void Add(Ingredient ingredient, Manager manager){
        Map<Ingredient,Integer> inventory = manager.getIngredientQuantity() ;
        Integer quantity = inventory.get(ingredient) ;

        if(quantity == null){
            inventory.put(ingredient, 1) ;
        }else{
            quantity++ ;
            inventory.put(ingredient, quantity) ;
        }

        manager.setIngredientQuantity(inventory);
    }

    //Supprimer l'ingredient dans l'inventaire
    private boolean Remove(Ingredient ingredient, Manager manager){
        Map<Ingredient,Integer> inventory = manager.getIngredientQuantity() ;
        Integer quantity = inventory.get(ingredient) ;

        if(quantity != null){
            if(quantity <= 1){
                inventory.remove(ingredient) ;
            }else{
                quantity-- ;
                inventory.put(ingredient, quantity) ;
            }
            manager.setIngredientQuantity(inventory);
            return true ;
        }else{
            return false ;
        }
    }

    //Retire l'argent au Manager
    private boolean Buy(Ingredient ingredient,Manager manager){
        if (manager.getChest() >= ingredient.getBuyingPrice()){
            manager.setChest(manager.getChest() - ingredient.getBuyingPrice());
            return true ;
        }else{
            return false ;
        }
    }

    //Ajoute l'argent au Manager arondi à l'entier supérieur
    private void Sell(Ingredient ingredient, Manager manager){
        manager.setChest(manager.getChest() + ((int)Math.ceil(ingredient.getBuyingPrice()/2)));
    }
}
