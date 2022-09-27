package aaa.tavern.dto;

public class InventoryIngredientDto {
    
    protected InventoryIngredientDto(){

    }

    public InventoryIngredientDto(ManagerDto managerDto, IngredientDto ingredientDto, Integer quantity){
        this.managerDto = managerDto;
        this.ingredientDto = ingredientDto;
    }
    private ManagerDto managerDto;
    
    private IngredientDto ingredientDto;

    private Integer quantity ;

    //#region Get
    public ManagerDto getManagerDto() {
        return managerDto;
    }

    public IngredientDto getIngredientDto() {
        return ingredientDto;
    }

    public Integer getQuantity() {
        return quantity;
    }
    //#endregion
    
}
