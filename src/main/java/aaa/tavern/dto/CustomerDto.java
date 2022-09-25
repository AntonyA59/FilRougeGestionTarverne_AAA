package aaa.tavern.dto;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import aaa.tavern.entity.RecipeCustomer;

public class CustomerDto {
    
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
