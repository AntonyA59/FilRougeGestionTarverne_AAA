package aaa.tavern.dto;

public class InventoryIngredientDto {
    

    private ManagerDto manager;
    

    private IngredientDto ingredient;

    private Integer quantity ;

    //#region Get
    public ManagerDto getManager() {
        return manager;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }
    //#endregion
    
}
