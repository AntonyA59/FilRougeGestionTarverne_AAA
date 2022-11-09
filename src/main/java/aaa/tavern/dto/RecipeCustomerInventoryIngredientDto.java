package aaa.tavern.dto;

import java.util.ArrayList;
import java.util.List;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.RecipeCustomer;

public class RecipeCustomerInventoryIngredientDto {
    private RecipeDto recipe;
    private CustomerDto customer;
    private List<InventoryManagerIngredientDto> inventaire=new ArrayList<InventoryManagerIngredientDto>();

    protected RecipeCustomerInventoryIngredientDto() {

    }

    public RecipeCustomerInventoryIngredientDto(RecipeCustomer recipeCustomer,
            List<InventoryIngredient> listInventoryIngredient) {
        this.recipe = new RecipeDto(recipeCustomer.getRecipe());
        this.customer = new CustomerDto(recipeCustomer.getCustomer());
        for (InventoryIngredient inventoryIngredient : listInventoryIngredient) {
            Ingredient ingredient = inventoryIngredient.getIngredient();
            Integer quantity = inventoryIngredient.getQuantity();
            this.inventaire.add(new InventoryManagerIngredientDto(ingredient,quantity));
        }
    }

    public RecipeDto getRecipe() {
        return recipe;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public List<InventoryManagerIngredientDto> getInventaire() {
        return inventaire;
    }

    
}
