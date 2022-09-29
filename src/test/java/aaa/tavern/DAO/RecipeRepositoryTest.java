package aaa.tavern.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.Entity.Recipe;

@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Test
    @Sql("givenRecipeLevel2_WhenFindWithLevelRecipe_ThenReturn2Recipe.sql")
    public void givenRecipeLevel2_WhenFindWithLevelRecipe_ThenReturn2Recipe(){
        List<Recipe> listRecipe2= recipeRepository.findByLevelLessThanEqual(2);
        assertEquals(listRecipe2.size(), 3);
    
    }
    
    @Test
    @Sql("givenRecipeLevel2_WhenFindWithLevelRecipe_ThenNotReturnRecipe.sql")
    public void givenRecipeLevel2_WhenFindWithLevelRecipe_ThenNotReturnRecipe(){
        List<Recipe> listRecipe2= recipeRepository.findByLevelLessThanEqual(2);
        assertEquals(listRecipe2.size(), 0);
    
    }

}


