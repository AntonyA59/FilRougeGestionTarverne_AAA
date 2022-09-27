package aaa.tavern.Entity;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class RecipeIngredientKey implements Serializable {
    @Column(name = "recipe_id")
    private Integer idRecipe ;

    @Column(name = "ingredient_id")
    private Integer idIngredient ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            RecipeIngredientKey that = (RecipeIngredientKey) o;
        return idRecipe == that.idRecipe && idIngredient == that.idIngredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecipe, idIngredient);
    }
    //#region get/set
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
    //#endregion
}
