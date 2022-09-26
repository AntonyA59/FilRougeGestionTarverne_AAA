package aaa.tavern.service;

import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.exception.ForbiddenException;

@Service
public class ShopService {
    @Autowired
    ManagerRepository managerRepository ;
    
    @Autowired
    IngredientRepository ingredientRepository ;

    @Autowired
    Manager manager ;

    @Autowired
    Map<Integer,Ingredient> mapIngredients ;  

    public void Buy(int idIngredient) throws ForbiddenException{
        Ingredient ingredient = mapIngredients.get(idIngredient);

        if(ingredient == null)
            throw new EntityNotFoundException();

        if (manager.getChest() < ingredient.getBuyingPrice())
			throw new ForbiddenException();

        manager.setChest(manager.getChest() - ingredient.getBuyingPrice());
        
        Add(idIngredient) ;

        managerRepository.save(manager) ;
    }

    public void Sell(int idIngredient){
        Ingredient ingredient = mapIngredients.get(idIngredient);

        if(ingredient == null)
            throw new EntityNotFoundException();

        manager.setChest(manager.getChest() + ((int)Math.ceil(ingredient.getBuyingPrice()/2)));

        Remove(idIngredient);

        managerRepository.save(manager) ;
    }

    private void Add(int idIngredient){
        Ingredient ingredient = mapIngredients.get(idIngredient) ;

        if (ingredient != null){
            throw new EntityNotFoundException();   
        }else{
            manager.addIngredientQuantity(ingredient);
        }
    }

    private void Remove(int idIngredient){
        Ingredient ingredient = mapIngredients.get(idIngredient) ;

        if (ingredient != null){
            throw new EntityNotFoundException();   
        }else{
            //manager.removeIngredientQuantity(ingredient);
        }
    }

    public Map<Integer,Ingredient> getAllIngredients(){
        return mapIngredients ;
    }

    public Ingredient getIngredient(int idIngredient){
        if(mapIngredients.get(idIngredient) != null){
            return mapIngredients.get(idIngredient) ;
        }else{
            return null ;
        }
    }
}
