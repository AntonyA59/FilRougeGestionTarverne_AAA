package aaa.tavern.entity;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_customer")
public class RecipeCustomer{

    @EmbeddedId
    private RecipeCustomerKey id=new RecipeCustomerKey();

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "id_customer")
    private Customer customer;
    
    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;
    
    private Integer quantity ;
    
    public RecipeCustomer() {
        customer = new Customer() ;
        recipe = new Recipe() ;
        quantity = 0 ;
    }

    //#region
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion

}
