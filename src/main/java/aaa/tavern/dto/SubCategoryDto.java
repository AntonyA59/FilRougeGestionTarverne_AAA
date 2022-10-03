package aaa.tavern.dto;

import aaa.tavern.entity.SubCategory;

public class SubCategoryDto {
    
    private Integer id;

    private String name;

    private Integer categoryDto;

    public SubCategoryDto(SubCategory subCategory){
        this.id=subCategory.getIdSubCategory();
        this.name=subCategory.getName();
        this.categoryDto= subCategory.getCategory().getIdCategory();
    }

    //#region Get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCategoryDto() {
        return categoryDto;
    }
    //#endregion


    
}
