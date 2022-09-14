package filrougeaaa;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class InventoryIngredientKey implements Serializable{
    @Column(name = "id_manager")
    private Integer managerId;

    @Column(name = "id_ingredient")
    private Integer ingredientId;

    //#region
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    //#endregion
}
