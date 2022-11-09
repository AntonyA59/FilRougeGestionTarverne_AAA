package aaa.tavern.dto.received;

public class ShopIngredientDto {
    int idManager;
    int[] idIngredient;

    protected ShopIngredientDto(){

    }
    
    public ShopIngredientDto(int idManager, int[] idIngredient) {
        this.idManager = idManager;
        this.idIngredient = idIngredient;
    }

    public int getIdManager() {
        return idManager;
    }
    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
    public int[] getIdIngredient() {
        return idIngredient;
    }
    public void setIdIngredient(int[] idIngredient) {
        this.idIngredient = idIngredient;
    }   
}
