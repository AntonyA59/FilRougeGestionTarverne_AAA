package aaa.tavern.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Recipe;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
   List<Recipe> findByLevelLessThanEqual(Integer level); 
   //TODO A VOIR POUR UN FIND PAR ID ET LEVEL
   //Optional<Recipe> findByidAndByLevelLessThanEqual(Integer id ,Integer level); 
}
