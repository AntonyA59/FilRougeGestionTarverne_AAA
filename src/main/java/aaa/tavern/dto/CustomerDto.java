package aaa.tavern.dto;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.TableRest;


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

    private TableRest tableRest;

    private Set<RecipeCustomer> commandList=new HashSet<RecipeCustomer>();

    public CustomerDto(Customer customer) {

        this.purseOfGold = customer.getPurseOfGold();
        this.happiness = customer.getHappiness();
        this.hunger = customer.getHunger();
        this.thirst = customer.getThirst();
        this.nauseaLevel = customer.getNauseaLevel();
        this.alcoholLevel = customer.getAlcoholLevel();
        this.toilet = customer.getToilet();
        this.timeInTavern= customer.getTimeInTavern();
        this.nauseaTolerance = customer.getNauseaTolerance();
        this.alcoholTolerance = customer.getAlcoholTolerance();
        this.gender = customer.getGender();
        this.expGiven = 0;
        this.tableRest = customer.getTableRest();
        
    }

    //#region
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

    public TableRest getTableRest() {
        return tableRest;
    }

    public Set<RecipeCustomer> getCommandList() {
        return commandList;
    }
    //#endregion 
}
