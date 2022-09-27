package aaa.tavern.dto;

import java.sql.Time;
import java.sql.Date;

public class RecipeDto {
    
    protected RecipeDto(){

    }

    public RecipeDto(Integer id,String name, Integer sellingPrice, Integer level, Time consommationTime, Time preparationTime, 
    Date peremptionDate, Integer ExpGiven, SubCategoryDto subCategoryDto ){
        this.id = id;
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.level = level;
        this.consommationTime = consommationTime;
        this.preparationTime = preparationTime;
        this.peremptionDate = peremptionDate;
        this.expGiven = expGiven;
        this.subCategoryDto = subCategoryDto;
    }
    private Integer id;

    private String name;

    private Integer sellingPrice;

    private Integer level ;
    
    private Time consommationTime ;

    private Time preparationTime ;
    
    private Date peremptionDate;

    private Integer expGiven;

    private SubCategoryDto subCategoryDto;

    //#region Get
    public Integer getId() {
        return id;
    }

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
