package aaa.tavern.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Objects;

import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;

public class RecipeDto {
    private Integer id ;
    private String name ;
	private Integer sellingPrice;
	private Integer level;
	private Time consommationTime;
	private Time preparationTime;
	private Date peremptionDate;
	private Integer expGiven;
	private Integer subCategory;
	private HashMap<Integer,Integer> tabIngredientsForRecipe=new HashMap<Integer,Integer>();

    
    public RecipeDto(Recipe recipe){
        this.id=recipe.getIdRecipe();
        this.name = recipe.getName() ;
		this.sellingPrice = recipe.getSellingPrice() ;
		this.level = recipe.getLevel() ;
		this.consommationTime = recipe.getConsommationTime() ;
		this.preparationTime = recipe.getPreparationTime() ;
		this.peremptionDate = recipe.getPeremptionDate() ;
		this.expGiven = recipe.getExpGiven() ;
		this.subCategory = recipe.getSubCategory().getIdSubCategory() ;
		for(RecipeIngredient recipeIngredient: recipe.getTabIngredientsForRecipe()){
            Integer ingredientId= recipeIngredient.getIngredient().getIdIngredient();
            Integer quantity= recipeIngredient.getQuantity();
            this.tabIngredientsForRecipe.put(ingredientId,quantity);
        }
    }

    /**
	 * Deux RecipeDto sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RecipeDto that = (RecipeDto) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

//#region
    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Integer getSellingPrice() {
        return sellingPrice;
    }


    public Integer getLevel() {
        return level;
    }


    public Time getConsommationTime() {
        return consommationTime;
    }


    public Time getPreparationTime() {
        return preparationTime;
    }


    public Date getPeremptionDate() {
        return peremptionDate;
    }


    public Integer getExpGiven() {
        return expGiven;
    }


    public Integer getSubCategory() {
        return subCategory;
    }

    public HashMap<Integer, Integer> getTabIngredientsForRecipe() {
        return tabIngredientsForRecipe;
    }
//#endregion 

}
