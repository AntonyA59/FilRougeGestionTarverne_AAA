package aaa.tavern.dto;

import aaa.tavern.entity.Ingredient;

public class IngredientDto {
    protected IngredientDto(){

    }

    public IngredientDto(Ingredient ingredient){
        this.name = ingredient.getName();
        this.level = ingredient.getLevel();
        this.buyingPrice = ingredient.getBuyingPrice();
        this.id=ingredient.getIdIngredient();
        this.subCategory= ingredient.getSubCategory().getIdSubCategory();
    }


    private Integer id;

	private String name;

	
	private int level;

	
	private int buyingPrice;


    private Integer subCategory;


    //#region Get
    
    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
    }


    public int getBuyingPrice() {
        return buyingPrice;
    }


    public Integer getSubCategory() {
        return subCategory;
    }
    
    public Integer getId() {
        return id;
    }
    //#endregion
    
}
