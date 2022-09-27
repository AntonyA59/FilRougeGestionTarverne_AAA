package aaa.tavern.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;

public class NewRecipeDto {
    private Integer id ;
    private String name ;
	private Integer sellingPrice;
	private Integer level;
	private Time consommationTime;
	private Time preparationTime;
	private Date peremptionDate;
	private Integer expGiven;
	private Integer subCategory;
	private HashMap<IngredientDto,Integer> tabIngredientsForRecipre=new HashMap<IngredientDto,Integer>();

    
    public NewRecipeDto(Recipe recipe){
        this.id=recipe.getIdRecipe();
        this.name = recipe.getName() ;
		this.sellingPrice = recipe.getSellingPrice() ;
		this.level = recipe.getLevel() ;
		this.consommationTime = recipe.getConsommationTime() ;
		this.preparationTime = recipe.getPreparationTime() ;
		this.peremptionDate = recipe.getPeremptionDate() ;
		this.expGiven = recipe.getExpGiven() ;
		this.subCategory = recipe.getSubCategory().getIdSubCategory() ;
		this.tabIngredientsForRecipe= recipe ;
    }
    
    public Recipe getRecipe() {
        return recipe;
    }

}
