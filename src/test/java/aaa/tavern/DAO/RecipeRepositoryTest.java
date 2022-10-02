package aaa.tavern.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

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

    //@Test
    //@Sql("givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenNotReturnRecipe.sql")
    //public void givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenNotReturnRecipe(){
    //    Optional<Recipe> optRecipe2= recipeRepository.findByidAndByLevelLessThanEqual(2,1);
    //    assertFalse(optRecipe2.isPresent());
    //
    //}
}


