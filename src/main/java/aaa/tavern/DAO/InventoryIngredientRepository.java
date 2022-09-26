package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.InventoryIngredient;


@Repository
public interface InventoryIngredientRepository extends CrudRepository<InventoryIngredient, Integer> {
    
    
}
