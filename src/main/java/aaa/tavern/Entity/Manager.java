package aaa.tavern.entity;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "manager")
public class Manager {
    public Manager(){

    }
    public Manager(
    String name, 
    Integer reputation, 
    Integer chest, 
    Integer level, 
    Integer experience, 
    Player player)
    {
        this.name =name;
        this.reputation = reputation;
        this.chest = chest;
        this.level = level;
        this.experience = experience;
        this.player = player;
    }

    @Id
    @Column(name = "id_manager")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerID;
    
    @NotBlank
    @Column(name = "name")
    private String name;
    
    @Column(name = "reputation")
    private Integer reputation;

    @Column(name = "chest")
    private Integer chest;
    
    @Column(name = "level")
    private Integer level;
    
    @Column(name = "experience")
    private Integer experience;
    
    @ManyToOne()
    @JoinColumn(name = "id_player")
    private Player player;
    
    @Transient
    private Integer maxExp;

    @OneToMany(mappedBy = "manager")
    private List<InventoryIngredient> inventoryIngredient = new ArrayList<InventoryIngredient>();
    
    //#region get/set 
    public Integer getManagerID() {
        return managerID;
    }


    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getReputation() {
        return reputation;
    }


    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }


    public Integer getChest() {
        return chest;
    }


    public void setChest(Integer chest) {
        this.chest = chest;
    }


    public Integer getLevel() {
        return level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }


    public Integer getExperience() {
        return experience;
    }


    public void setExperience(Integer experience) {
        this.experience = experience;
    }


    public Player getPlayer() {
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    public Integer getMaxExp() {
        return maxExp;
    }


    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }

    public List<InventoryIngredient> getInventoryIngredient() {
        return inventoryIngredient;
    }
    public void setInventoryIngredient(List<InventoryIngredient> inventoryIngredient) {
        this.inventoryIngredient = inventoryIngredient;
    }

    // #endregion

}