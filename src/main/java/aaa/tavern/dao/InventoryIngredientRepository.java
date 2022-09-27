package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.InventoryIngredientKey;


@Repository
public interface InventoryIngredientRepository extends CrudRepository<InventoryIngredient, InventoryIngredientKey> {
    
    
}
