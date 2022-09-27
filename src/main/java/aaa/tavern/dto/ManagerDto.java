package aaa.tavern.dto;


import java.util.HashMap;
import java.util.Map;

import aaa.tavern.Entity.Ingredient;


public class ManagerDto {

    protected ManagerDto(){

    }

    public ManagerDto(Integer id,String name, Integer reputation, Integer chest, Integer level, Map<Ingredient,Integer> ingredientQuantity ){
        this.id = id;
        this.name = name;
        this.reputation = reputation;
        this.chest = chest;
        this.level = level;
        this.ingredientQuantity= ingredientQuantity;
    }

    private Integer id;

    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private Map<Ingredient,Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();

    
    //#region get

    public Integer getId() {
        return id;
    }
    
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
