package aaa.tavern.dto;

import java.util.List;

import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.RecipeCustomer;

public class RecipeCustomerInventoryIngredientDto {
    private Integer idRecipe;
    private Integer idCustomer;
    private List<InventoryIngredient> inventaire;

    protected RecipeCustomerInventoryIngredientDto() {

    }

    public RecipeCustomerInventoryIngredientDto(RecipeCustomer recipeCustomer,
            List<InventoryIngredient> inventoryIngredient) {
        this.idRecipe = recipeCustomer.getRecipe().getId();
        this.idCustomer = recipeCustomer.getCustomer().getIdCustomer();
        this.inventaire = inventoryIngredient;
    }
}
