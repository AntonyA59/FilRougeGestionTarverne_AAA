package aaa.tavern.dto;

import aaa.tavern.entity.RecipeCustomer;

public class RecipeCustomerDto {

    private int customerId;
    private int recipeId;
    private Long recipeStart;

    protected RecipeCustomerDto() {

    }

    public RecipeCustomerDto(int customerId, int recipeId, Long recipeStart) {
        this.customerId = customerId;
        this.recipeId = recipeId;
        if(recipeStart != null){
            this.recipeStart = recipeStart ;
        }else{
            this.recipeStart = null ;
        }
    }

    public RecipeCustomerDto(RecipeCustomer recipeCustomer) {
        this.customerId = recipeCustomer.getCustomer().getIdCustomer();
        this.recipeId = recipeCustomer.getRecipe().getId();
        if (recipeCustomer.getRecipeStart() != null) {
            this.recipeStart = recipeCustomer.getRecipeStart().getTime();
        } else {
            this.recipeStart = null;
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public Long getRecipeStart() {
        return recipeStart;
    }

}
