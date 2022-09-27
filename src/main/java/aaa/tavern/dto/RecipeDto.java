package aaa.tavern.dto;

import java.sql.Time;
import java.sql.Date;

public class RecipeDto {
    

    private String name;

    private Integer sellingPrice;

    private Integer level ;
    
    private Time consommationTime ;

    private Time preparationTime ;
    
    private Date peremptionDate;

    private Integer expGiven;

    private SubCategoryDto subCategoryDto;

    //#region Get
    public String getName() {
        return name;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public Integer getLevel() {
        return level;
    }

    public Time getConsommationTime() {
        return consommationTime;
    }

    public Time getPreparationTime() {
        return preparationTime;
    }

    public Date getPeremptionDate() {
        return peremptionDate;
    }

    public Integer getExpGiven() {
        return expGiven;
    }

    public SubCategoryDto getSubCategoryDto() {
        return subCategoryDto;
    }
    //#endregion


    
}
