package aaa.tavern.dto;

public class RecipeIngredientDto {
    
    protected RecipeIngredientDto(){

    }

    public RecipeIngredientDto(RecipeDto recipeDto, IngredientDto ingredientDto , Integer quantity){
        this.recipeDto = recipeDto;
        this.ingredientDto = ingredientDto;
        this.quantity = quantity;
    }

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
