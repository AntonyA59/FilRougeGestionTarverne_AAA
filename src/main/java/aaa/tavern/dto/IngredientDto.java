package aaa.tavern.dto;

public class IngredientDto {
    
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
