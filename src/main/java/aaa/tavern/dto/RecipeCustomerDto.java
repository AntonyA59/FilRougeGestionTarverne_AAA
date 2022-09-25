package aaa.tavern.dto;

public class RecipeCustomerDto {

    private CustomerDto customerDto;
    
    private RecipeDto recipeDto;

    //#region Get
    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public RecipeDto getRecipeDto() {
        return recipeDto;
    }
    //#endregion
    
}
