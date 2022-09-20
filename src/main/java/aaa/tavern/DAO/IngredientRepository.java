package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.Ingredient;


@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    
    
}
