package aaa.tavern.dto;

import aaa.tavern.entity.RecipeCustomer;

public class RecipeCustomerDto {

    private int customerId;
    private int recipeId;
    private long recipeStart;

    protected RecipeCustomerDto() {

    }

    public RecipeCustomerDto(RecipeCustomer recipeCustomer) {
        this.customerId = recipeCustomer.getCustomer().getIdCustomer();
        this.recipeId = recipeCustomer.getRecipe().getId();
        this.recipeStart = recipeCustomer.getRecipeStart().getTime();
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public long getRecipeStart() {
        return recipeStart;
    }

}
