package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Recipe;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    
}
