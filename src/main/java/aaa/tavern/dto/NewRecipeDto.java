package aaa.tavern.dto;

import aaa.tavern.entity.Recipe;

public class NewRecipeDto {
    private Recipe recipe;

    
    public NewRecipeDto(Recipe recipe){
        this.recipe=recipe;
    }
    
    public Recipe getRecipe() {
        return recipe;
    }

}
