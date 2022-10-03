package aaa.tavern.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;


public class CustomerDto {

    private Integer idCustomer;

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

    private Integer tableRest;

    private Timestamp consommationStart;

    private Set<Integer> commandList=new HashSet<Integer>();

    public CustomerDto(Customer customer) {
        this.idCustomer= customer.getIdCustomer();
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
        this.tableRest = customer.getTableRest().getIdTable();
        this.consommationStart=customer.getConsommationStart();
        for(RecipeCustomer recipeCustomer: customer.getCommandList()){
            Integer recipeId= recipeCustomer.getRecipe().getId();
            this.commandList.add(recipeId);
        }
        
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

    public Integer getTableRest() {
        return tableRest;
    }

    public Set<Integer> getCommandList() {
        return commandList;
    }

    public Timestamp getConsommationStart() {
        return consommationStart;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }



    
    //#endregion 
}
