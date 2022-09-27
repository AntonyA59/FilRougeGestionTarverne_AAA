package aaa.tavern.dto;

public class SubCategoryDto {
    
    private String name;

    private CategoryDto categoryDto;

    //#region Get
    public String getName() {
        return name;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }
    //#endregion
    
}
