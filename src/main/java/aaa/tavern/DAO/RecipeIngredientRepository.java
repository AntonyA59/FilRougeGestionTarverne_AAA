package aaa.tavern.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeIngredient;


@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByRecipe(Recipe Recipe); 
    
}
