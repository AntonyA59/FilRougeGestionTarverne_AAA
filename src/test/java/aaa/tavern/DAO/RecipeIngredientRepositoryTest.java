package aaa.tavern.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeIngredient;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class RecipeIngredientRepositoryTest {
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;
    
    //test
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    @Sql("givenIngredientInRecipe_WhenFindWithIdRecipe_ThenNotReturnIngredient.sql")
    public void givenIngredientInRecipe_WhenFindWithIdRecipe_ThenNotReturnIngredient(){
        Optional<Recipe> optRecipe= recipeRepository.findById(3);
        Recipe recipe=optRecipe.get();
        List<RecipeIngredient> listRecipeIngredient= recipeIngredientRepository.findByRecipe(recipe);
        
        assertEquals(0, listRecipeIngredient.size());
    } 

    @Test
    @Sql("givenIngredientInRecipe_WhenFindWithIdRecipe_ThenReturnTwoIngredient.sql")
    public void givenIngredientInRecipe_WhenFindWithIdRecipe_ThenReturnTwoIngredient(){
        Optional<Recipe> optRecipe= recipeRepository.findById(1);
        Recipe recipe=optRecipe.get();
        List<RecipeIngredient> listRecipeIngredient= recipeIngredientRepository.findByRecipe(recipe);
        
        assertEquals(2, listRecipeIngredient.size());
    } 
}
