package filrougeaaa;

import java.io.Serializable;

import jakarta.persistence.*;


@Embeddable
public class RecipeCustomerKey implements Serializable{
    @Column(name = "id_customer")
    private Integer customerId;

    @Column(name = "id_recipe")
    private Integer recipeId;

    //#region
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
    //#endregion

    
}
