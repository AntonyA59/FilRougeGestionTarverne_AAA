package aaa.tavern.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;

public class ManagerDto {

    protected ManagerDto(){

    }

    public ManagerDto(Manager manager){
        this.id=manager.getManagerId();
        this.name = manager.getName();
        this.reputation = manager.getReputation();
        this.chest = manager.getChest();
        this.level = manager.getLevel();
        this.experience=manager.getExperience();
        this.player=manager.getPlayer().getPlayerId();
        this.maxExp=manager.getMaxExp();
        for(InventoryIngredient inventoryIngredient: manager.getInventoryIngredient()){
            Integer ingredientId= inventoryIngredient.getIngredient().getIdIngredient();
            Integer quantity= inventoryIngredient.getQuantity();
            this.ingredientQuantity.put(ingredientId,quantity);
        }
    }

    private Integer id;

    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private Integer player;
    
    private Integer maxExp;
    
    private Map<Integer,Integer> ingredientQuantity = new HashMap<Integer, Integer>();

    
    //#region get/set
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlayer() {
        return player;
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

    public Map<Integer, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public Integer getMaxExp() {
        return maxExp;
    }


    //#endregion

    
}
