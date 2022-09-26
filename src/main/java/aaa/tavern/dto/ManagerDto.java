package aaa.tavern.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;

public class ManagerDto {

    protected ManagerDto(){

    }

    public ManagerDto(String name, Integer reputation, Integer chest, Integer level, Map<Ingredient,Integer> ingredientQuantity ){
        this.name = name;
        this.reputation = reputation;
        this.chest = chest;
        this.level = level;
        this.ingredientQuantity= ingredientQuantity;
    }
    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private Map<Ingredient,Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();

    
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
