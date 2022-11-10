package aaa.tavern.dto.received;

public class ShopIngredientQuantity {
    int idIngredient ;
    int quantity ;
    protected ShopIngredientQuantity(){
        
    }
    public ShopIngredientQuantity(int idIngredient, int quantity) {
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }
    public int getIdIngredient() {
        return idIngredient;
    }
    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
