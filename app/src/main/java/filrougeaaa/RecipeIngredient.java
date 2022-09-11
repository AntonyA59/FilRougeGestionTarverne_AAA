package filrougeaaa;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="recipe_ingredient")
public class RecipeIngredient {
    @EmbeddedId
    private RecipeIngredientKey id ;

    @ManyToOne
    @MapsId("idRecipe")
    @JoinColumn(name = "id_recipe")
    private Recipe recipe ;

    @ManyToOne 
    @MapsId("idIngredient")
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient ;

    @Column(name = "quantity")
    Integer quantity;

    public RecipeIngredient() {
        this.id = new RecipeIngredientKey() ;
        this.quantity = 0;
        this.recipe = new Recipe();
        this.ingredient = new Ingredient();
    }

    //#region get/set
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public RecipeIngredientKey getId() {
        return id;
    }

    public void setId(RecipeIngredientKey id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion
}
