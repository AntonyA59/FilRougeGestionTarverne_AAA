package filrougeaaa;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class RecipeIngredientKey implements Serializable {
    @Column(name = "id_recipe")
    private Integer idRecipe ;

    @Column(name = "id_ingredient")
    private Integer idIngredient ;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Integer idIngredient) {
        this.idIngredient = idIngredient;
    }    
}
