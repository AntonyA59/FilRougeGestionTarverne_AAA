package aaa.tavern.dto;

public class RecipeCustomerDto {

    private CustomerDto customerDto;
    
    private RecipeDto recipeDto;

    protected RecipeCustomerDto(){

    }

    public RecipeCustomerDto(CustomerDto customerDto , RecipeDto recipeDto){
        this.customerDto = customerDto;
        this.recipeDto = recipeDto;
    }
    //#region Get
    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public RecipeDto getRecipeDto() {
        return recipeDto;
    }
    //#endregion
    
}
