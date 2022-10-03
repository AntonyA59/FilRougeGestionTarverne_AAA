package aaa.tavern.dto;

import aaa.tavern.Entity.Ingredient;

public class IngredientDto {

    private int id ;

	private String name;
	
	private int level;
	
	private int buyingPrice;

    private int idSubCategory;

    protected IngredientDto(){

    }



    public IngredientDto(Ingredient ingredient){
        this.id = ingredient.getId() ;
        this.name = ingredient.getName();
        this.level = ingredient.getLevel();
        this.buyingPrice = ingredient.getBuyingPrice();
        this.idSubCategory = ingredient.getSubCategory().getIdSubCategory() ;
    }

    //#region Get
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdSubCategory(int idSubCategory) {
        this.idSubCategory = idSubCategory;
    }


    //#endregion


    
}
