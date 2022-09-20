package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.RecipeIngredient;


@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
    
    
}
