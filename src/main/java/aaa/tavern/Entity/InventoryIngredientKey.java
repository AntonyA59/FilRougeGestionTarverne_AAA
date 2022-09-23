package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class InventoryIngredientKey implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(name = "id_manager")
    private Integer idManager;

    @Column(name = "id_ingredient")
    private Integer idIngredient;

    public InventoryIngredientKey(Integer idManager, Integer idIngredient) {
        this.idManager = idManager;
        this.idIngredient = idIngredient;
    }

    //#region
    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public Integer getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Integer idIngredient) {
        this.idIngredient = idIngredient;
    }

    //#endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        InventoryIngredientKey that = (InventoryIngredientKey) o;
        return idManager == that.idManager && idIngredient == that.idIngredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idManager, idIngredient);
    }
}
