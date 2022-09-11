package filrougeaaa;

import jakarta.persistence.*;

@Entity
@Table(name="inventory_ingredient")
public class InventoryIngredient {
    
    @EmbeddedId
    private InventoryIngredientKey id = new InventoryIngredientKey();
    
    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "id_manager")
    private Manager manager;
    
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;
    
    private Integer quantity ;
    
    
    public InventoryIngredient() {
        this.quantity = 0 ;
        this.manager = new Manager() ;
        this.ingredient = new Ingredient() ;
    }
    
    
    //#region get/set
    public InventoryIngredientKey getId() {
        return id;
    }


    public void setId(InventoryIngredientKey id) {
        this.id = id;
    }


    public Manager getManager() {
        return manager;
    }


    public void setManager(Manager manager) {
        this.manager = manager;
    }


    public Ingredient getIngredient() {
        return ingredient;
    }


    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion  

}
