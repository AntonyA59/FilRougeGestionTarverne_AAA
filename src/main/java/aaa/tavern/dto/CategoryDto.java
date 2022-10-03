package aaa.tavern.dto;

import aaa.tavern.entity.Category;

public class CategoryDto {
    
    private Integer id;
    private String name;

    
    protected CategoryDto(){

    }

    public CategoryDto(Category category){
        this.name = category.getName();
        this.id= category.getIdCategory();
    }

//#region
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
//#endregion
}
