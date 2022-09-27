package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_customer")
public class RecipeCustomer implements Serializable{

    @EmbeddedId
    private RecipeCustomerKey id=new RecipeCustomerKey();

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    

    
    public RecipeCustomer() {}

    public RecipeCustomer(Recipe recipe , Customer customer){
        this.recipe= recipe;
        this.customer= customer;
    }

    /**
	 * Deux RecipeCustomer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            RecipeCustomer that = (RecipeCustomer) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    //#region get/set
    public RecipeCustomerKey getId() {
        return id;
    }

    public void setId(RecipeCustomerKey id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    //#endregion

}
