package aaa.tavern.dto;

public class RecipeIngredientDto {
    

    private RecipeDto recipeDto ;


    private IngredientDto ingredientDto ;

    
    private Integer quantity;

    //#region Get
    public RecipeDto getRecipeDto() {
        return recipeDto;
    }


    public IngredientDto getIngredientDto() {
        return ingredientDto;
    }


    public Integer getQuantity() {
        return quantity;
    }
    //#endregion
    

}
