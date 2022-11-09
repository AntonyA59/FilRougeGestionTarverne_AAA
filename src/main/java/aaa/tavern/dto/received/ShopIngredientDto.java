package aaa.tavern.dto.received;

public class ShopIngredientDto {
    int idManager;
    ShopIngredientQuantity[] shopIngredientQuantity ;

    protected ShopIngredientDto(){

    }
    public ShopIngredientDto(int idManager, ShopIngredientQuantity[] shopIngredientQuantity) {
        this.idManager = idManager;
        this.shopIngredientQuantity = shopIngredientQuantity;
    }
    public int getIdManager() {
        return idManager;
    }
    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
    public ShopIngredientQuantity[] getShopIngredientQuantity() {
        return shopIngredientQuantity;
    }
    public void setShopIngredientQuantity(ShopIngredientQuantity[] shopIngredientQuantity) {
        this.shopIngredientQuantity = shopIngredientQuantity;
    }
}
