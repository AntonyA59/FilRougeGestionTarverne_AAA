package aaa.tavern.dto;

import java.util.ArrayList;
import java.util.List;


import aaa.tavern.entity.InventoryIngredient;

public class ManagerDto {

    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private List<InventoryIngredient> inventoryIngredient = new ArrayList<InventoryIngredient>();

    
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

    public List<InventoryIngredient> getInventoryIngredient() {
        return inventoryIngredient;
    }
    //#endregion

    
}
