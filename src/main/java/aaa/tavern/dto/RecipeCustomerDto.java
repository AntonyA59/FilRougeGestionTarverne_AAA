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
        this.recipeStart = recipeStart;
    }

    public RecipeCustomerDto(RecipeCustomer recipeCustomer) {
        this.customerId = recipeCustomer.getCustomer().getIdCustomer();
        this.recipeId = recipeCustomer.getRecipe().getId();
        if (recipeCustomer.getRecipeStart() == null) {
            this.recipeStart = null;
        } else {
            this.recipeStart = recipeCustomer.getRecipeStart().getTime();
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
