package aaa.tavern.service;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.InventoryIngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.InventoryIngredientKey;
import aaa.tavern.entity.Manager;
import aaa.tavern.exception.ForbiddenException;

@Service
public class ShopService {
    @Autowired
    ManagerRepository managerRepository ;
    
    @Autowired
    IngredientRepository ingredientRepository ;

    @Autowired
    InventoryIngredientRepository inventoryIngredientRepository ;

    @Autowired
    Manager manager ;

    @Autowired
    Map<Integer,Ingredient> mapIngredients ;

    public void Buy(int idIngredient) throws ForbiddenException{
        Optional<Ingredient> optIngredient = ingredientRepository.findById(idIngredient) ;

        if (optIngredient.isEmpty())
			throw new EntityNotFoundException();

        Ingredient ingredient = optIngredient.get();

        if (manager.getChest() < ingredient.getBuyingPrice())
			throw new ForbiddenException();

        manager.setChest(manager.getChest() - ingredient.getBuyingPrice());
        
        managerRepository.save(manager) ;
    }

    public void Sell(int idIngredient){
        Optional<Ingredient> optIngredient = ingredientRepository.findById(idIngredient) ;

        if (optIngredient.isEmpty())
			throw new EntityNotFoundException();
        
        Ingredient ingredient = optIngredient.get();
        manager.setChest(manager.getChest() + ((int)Math.ceil(ingredient.getBuyingPrice()/2)));

        managerRepository.save(manager) ;
    }

    public void Add(int idIngredient){
        //InventoryIngredientKey id = new InventoryIngredientKey(idManager, idIngredient);
        //Optional<InventoryIngredient> optInventoryIngredient = inventoryIngredientRepository.findById(id) ;

        //InventoryIngredient inventoryIngredient ;

        Ingredient ingredient = mapIngredients.get(idIngredient) ;

        if (ingredient != null)
            throw new EntityNotFoundException();   
        /*       
            //Ingredient ingredient = ingredientRepository.findById(idIngredient).get() ;
        }else{
            //inventoryIngredient = optInventoryIngredient.get() ;
            //inventoryIngredient.setQuantity(inventoryIngredient.getQuantity()+1);
        }
        */
        //inventoryIngredientRepository.save(inventoryIngredient) ;
    }

    public void Remove(int idManager, int idIngredient){
        InventoryIngredientKey id = new InventoryIngredientKey(idManager, idIngredient);
        Optional<InventoryIngredient> optInventoryIngredient = inventoryIngredientRepository.findById(id) ;

        InventoryIngredient inventoryIngredient ;

        if (optInventoryIngredient.isEmpty()){
            throw new EntityNotFoundException();
        }else{
            inventoryIngredient = optInventoryIngredient.get() ;
            if(inventoryIngredient.getQuantity() == 1){
                inventoryIngredientRepository.deleteById(id);
            }else{
                inventoryIngredient.setQuantity(inventoryIngredient.getQuantity()-1);
            }
            inventoryIngredientRepository.save(inventoryIngredient) ;
        }
    }
}
