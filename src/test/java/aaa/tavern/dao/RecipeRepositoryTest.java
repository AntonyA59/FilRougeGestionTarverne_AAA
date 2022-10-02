package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Recipe;

@DataJpaTest
public class RecipeRepositoryTest {
	
	@Autowired
	private ManagerRepository managerRepository;
	
 	@Autowired
	private RecipeRepository recipeRepository;
	
	@Test
	@Sql("givenRecipes_FindByLessAndEqualLevelManager_thenReturnRecipe.sql")
	public void givenRecipes_FindByLessAndEqualLevelManager_thenReturnRecipe() {
		// Manager de niveau 5
		Manager manager = managerRepository.findById(1).get();
	
		List<Recipe> listRecipe = recipeRepository.findByLevelLessThanEqual(manager.getLevel());
		
		assertEquals(listRecipe.size(), 5);
	}
	
}
