package aaa.tavern.dto;

import java.util.List;

public class LoadManagerDto {

    private ManagerDto manager;

    private List<CategoryDto> categories;

    private List<CustomerDto> customers;

    private List<IngredientDto> ingredients;

    private List<InventoryManagerIngredientDto> inventoryManagerIngredient;

    private List<PlaceDto> places;

    private List<RecipeDto> recipes;

    private List<RecipeDto> recipeCustomers;

    private List<SubCategoryDto> SubCategories;

    private List<TableRestDto> TableRests;

    protected LoadManagerDto() {

    }

    public LoadManagerDto(
            ManagerDto manager,
            List<CategoryDto> categories,
            List<CustomerDto> customers,
            List<IngredientDto> ingredients,
            List<InventoryManagerIngredientDto> inventoryManagerIngredient,
            List<PlaceDto> places,
            List<RecipeDto> recipes,
            List<RecipeDto> recipeCustomers,
            List<SubCategoryDto> SubCategories,
            List<TableRestDto> TableRests) {
        this.manager = manager;
        this.categories = categories;
        this.customers = customers;
        this.ingredients = ingredients;
        this.inventoryManagerIngredient = inventoryManagerIngredient;
        this.places = places;
        this.recipes = recipes;
        this.recipeCustomers = recipeCustomers;
        this.SubCategories = SubCategories;
        this.TableRests = TableRests;
    }

    // #region get
    public ManagerDto getManager() {
        return manager;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public List<InventoryManagerIngredientDto> getInventoryManagerIngredient() {
        return inventoryManagerIngredient;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public List<RecipeDto> getRecipeCustomers() {
        return recipeCustomers;
    }

    public List<SubCategoryDto> getSubCategories() {
        return SubCategories;
    }

    public List<TableRestDto> getTableRests() {
        return TableRests;
    }

    // #endregion

}
