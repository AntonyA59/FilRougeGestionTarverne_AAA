package aaa.tavern.dto;

import java.util.HashMap;
import java.util.Map;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;

public class ManagerDto {

    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private Map<Ingredient,Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();

    protected ManagerDto(){

    }

    public ManagerDto(Manager manager){
        this.name = manager.getName() ;
        this.reputation = manager.getReputation() ;
        this.chest = manager.getChest() ;
        this.level = manager.getLevel() ;
        this.experience = manager.getExperience() ;
        
    }

    public ManagerDto(String name, Integer reputation, Integer chest, Integer level, Map<Ingredient,Integer> ingredientQuantity ){
        this.name = name;
        this.reputation = reputation;
        this.chest = chest;
        this.level = level;
        this.ingredientQuantity= ingredientQuantity;
    }
    
    //#region get/set
    public String getName() {
        return name;
    }

    public Integer getReputation() {
        return reputation;
    }

    public Integer getChest() {
        return chest;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getExperience() {
        return experience;
    }

    public Map<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }


    //#endregion

    
}
