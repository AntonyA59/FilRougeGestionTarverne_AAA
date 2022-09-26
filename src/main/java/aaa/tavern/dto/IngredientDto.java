package aaa.tavern.dto;

public class IngredientDto {
    protected IngredientDto(){

    }

    public IngredientDto(String name, int level, int buyingPrice){
        this.name = name;
        this.level = level;
        this.buyingPrice = buyingPrice;
    }

	private String name;

	
	private int level;

	
	private int buyingPrice;


    private SubCategoryDto subCategoryDto;


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


    public SubCategoryDto getSubCategoryDto() {
        return subCategoryDto;
    }
    //#endregion
    
}
