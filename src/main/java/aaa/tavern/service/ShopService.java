package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.IngredientDto;

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

    /**
     * 
     * @param idManager
     * @return List<IngredientDto>
     */
    //retourne la liste des ingredients du jeu en foncion du niveau du Manager
    public List<IngredientDto> getAllIngredients(int idManager){
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        List<Ingredient> listEntityIngredients = ingredientRepository.findByLevelLessThanEqual(manager.getLevel()) ;
        List<IngredientDto> listIngredientsDto = new ArrayList<IngredientDto>() ;

        for (Ingredient ingredientElement : listEntityIngredients) {
            listIngredientsDto.add(new IngredientDto(ingredientElement)) ;
        }

        return listIngredientsDto ;
    }

    /**
     * 
     * @param idManager
     * @param idIngredient
     * @return 
     * @throws EntityNotFoundException
     * @throws ForbiddenException
     */
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

    /**
     * 
     * @param idManager
     * @param idIngredient
     * @throws EntityNotFoundException
     * @throws ForbiddenException
     */
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

    /**
     * 
     * @param ingredient
     * @param manager
     */
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

    /**
     * 
     * @param ingredient
     * @param manager
     * @return boolean
     */
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

    /**
     * 
     * @param ingredient
     * @param manager
     * @return boolean
     */
    //Retire l'argent au Manager
    private boolean Buy(Ingredient ingredient,Manager manager){
        if (manager.getChest() >= ingredient.getBuyingPrice()){
            manager.setChest(manager.getChest() - ingredient.getBuyingPrice());
            return true ;
        }else{
            return false ;
        }
    }

    /**
     * 
     * @param ingredient
     * @param manager
     */
    //Ajoute l'argent au Manager arondi à l'entier supérieur
    private void Sell(Ingredient ingredient, Manager manager){
        manager.setChest(manager.getChest() + ((int)Math.ceil(ingredient.getBuyingPrice()/2)));
    }
}
