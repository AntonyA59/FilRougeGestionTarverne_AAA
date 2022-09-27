package aaa.tavern.dto;

public class IngredientDto {
    protected IngredientDto(){

    }

    public IngredientDto(Integer id, String name, int level, int buyingPrice){
        this.id = id;
        this.name = name;
        this.level = level;
        this.buyingPrice = buyingPrice;
    }

    private Integer id; 

	private String name;

	
	private int level;

	
	private int buyingPrice;


    private SubCategoryDto subCategoryDto;


    //#region Get
    public Integer getId() {
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


    public SubCategoryDto getSubCategoryDto() {
        return subCategoryDto;
    }
    //#endregion


    
}
