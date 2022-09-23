package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Ingredient;


@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    
    
}
