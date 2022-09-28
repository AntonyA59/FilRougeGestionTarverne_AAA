package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Recipe;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    
}
