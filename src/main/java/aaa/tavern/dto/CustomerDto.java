package aaa.tavern.dto;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import aaa.tavern.entity.RecipeCustomer;

public class CustomerDto {
    
    protected CustomerDto(){

    }

    public CustomerDto(
    Integer purseOfGold, 
    Float happiness, 
    Float hunger,
    Float nauseaLevel,
    Float alcoholLevel,
    Float toilet,
    Time timeInTavern,
    Float nauseaTolerance,
    Float alcoholTolerance,
    Boolean gender,
    Integer expGiven,
    TableRestDto tableRestDto,
    Set<RecipeCustomer> commandList
    )
    {
        this.purseOfGold = purseOfGold;
        this.happiness = happiness;
        this.hunger = hunger;
        this.nauseaLevel = nauseaLevel;
        this.alcoholLevel = alcoholLevel;
        this.toilet = toilet;
        this.timeInTavern = timeInTavern;
        this.nauseaTolerance = nauseaTolerance;
        this.alcoholTolerance = alcoholTolerance;
        this.gender = gender;
        this.expGiven = expGiven;
        this.tableRestDto = tableRestDto;
        this.commandList = commandList;
    }
    
    private Integer purseOfGold;

    private Float happiness;

    private Float hunger;

    private Float thirst;

    private Float nauseaLevel;

    private Float alcoholLevel;
    
    private Float toilet;

    private Time timeInTavern;

    private Float nauseaTolerance;

    private Float alcoholTolerance;
    
    private Boolean gender;

    private Integer expGiven;

    private TableRestDto tableRestDto;

    private Set<RecipeCustomer> commandList = new HashSet<RecipeCustomer>();



    //#region Get
    public Integer getPurseOfGold() {
        return purseOfGold;
    }

    public Float getHappiness() {
        return happiness;
    }

    public Float getHunger() {
        return hunger;
    }

    public Float getThirst() {
        return thirst;
    }

    public Float getNauseaLevel() {
        return nauseaLevel;
    }

    public Float getAlcoholLevel() {
        return alcoholLevel;
    }

    public Float getToilet() {
        return toilet;
    }

    public Time getTimeInTavern() {
        return timeInTavern;
    }

    public Float getNauseaTolerance() {
        return nauseaTolerance;
    }

    public Float getAlcoholTolerance() {
        return alcoholTolerance;
    }

    public Boolean getGender() {
        return gender;
    }

    public Integer getExpGiven() {
        return expGiven;
    }

    public TableRestDto getTableRestDto() {
        return tableRestDto;
    }

    public Set<RecipeCustomer> getCommandList() {
        return commandList;
    }
    //#endregion

    
}
