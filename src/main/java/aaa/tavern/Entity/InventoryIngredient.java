package aaa.tavern.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="inventory_ingredient")
public class InventoryIngredient {
    
    @EmbeddedId
    private InventoryIngredientKey id ;

    /*
    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "id_manager")
    private Manager manager;
    
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;
    */
    
    private Integer quantity ;
    
    public InventoryIngredient() {
        this.quantity = 0 ;
        //this.manager = new Manager() ;
        //this.ingredient = new Ingredient() ;
    }
    
    public InventoryIngredient(Manager manager, Ingredient ingredient, int quantity) {
        id = new InventoryIngredientKey(manager.getIdManager(),ingredient.getIdIngredient());
        this.quantity = quantity;
    }
    
    //#region get/set
    public InventoryIngredientKey getId() {
        return id;
    }


    public void setId(InventoryIngredientKey id) {
        this.id = id;
    }
/* 
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
*/

    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion  

    /**
	 * Deux PlayerConsumable sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        InventoryIngredient that = (InventoryIngredient) o;
        return Objects.equals(id, that.id);
    }
 
	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
