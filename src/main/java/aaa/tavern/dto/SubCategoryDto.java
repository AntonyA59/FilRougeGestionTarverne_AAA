package aaa.tavern.dto;

public class SubCategoryDto {
    

    protected SubCategoryDto(){

    }

    public SubCategoryDto(Integer id,String name, CategoryDto categoryDto){
        this.id = id;
        this.name = name;
        this.categoryDto = categoryDto;
    }

    private Integer id;
    private String name;

    private CategoryDto categoryDto;

    //#region Get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }
    //#endregion


    
}
