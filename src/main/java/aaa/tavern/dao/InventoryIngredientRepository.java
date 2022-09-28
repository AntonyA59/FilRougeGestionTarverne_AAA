package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.InventoryIngredient;
import aaa.tavern.Entity.Manager;


@Repository
public interface InventoryIngredientRepository extends CrudRepository<InventoryIngredient, Integer> {
    List<InventoryIngredient> findByManager(Manager manager);
    
}
