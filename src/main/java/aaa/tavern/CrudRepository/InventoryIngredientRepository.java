package aaa.tavern.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.InventoryIngredient;


@Repository
public interface InventoryIngredientRepository extends CrudRepository<InventoryIngredient, Integer> {
    
    
}
