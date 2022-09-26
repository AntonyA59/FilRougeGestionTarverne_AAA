package aaa.tavern.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "manager")
public class Manager {
<<<<<<< HEAD
=======
    public Manager(){}

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

>>>>>>> origin/Spring-test-Alex6dev
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManager;
    
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
    @JoinColumn(name = "player_id")
    private Player player;
    
    @Transient
    private Integer maxExp;

    //inventaire BDD
    @OneToMany(mappedBy = "manager")
    private List<InventoryIngredient> inventoryIngredient = new ArrayList<InventoryIngredient>();

    //inventaire jeu
    @Transient
    private Map<Ingredient,Integer> ingredientQuantity;

    public Manager(){
  
    }
    
<<<<<<< HEAD
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

=======
    //inventaire jeu
    @Transient
    private Map<Ingredient,Integer> ingredientQuantity;

    @PostLoad
    private void transformForIngredientQuantity(){
        Map<Ingredient,Integer> tab= new HashMap<Ingredient,Integer>();
        for(InventoryIngredient inventoryIngredient:this.inventoryIngredient){
            Ingredient ingredient= inventoryIngredient.getIngredient();
            Integer quantity= inventoryIngredient.getQuantity();
            tab.put(ingredient, quantity);
        }
        this.ingredientQuantity=tab;
    }
    
    @PrePersist
    @PreUpdate
    private void transformForIngredientIngredient(){
        this.inventoryIngredient.clear();
        for(Ingredient ingredient: this.ingredientQuantity.keySet()){
            Integer quantity= this.ingredientQuantity.get(ingredient);
            InventoryIngredient inventoryIngredient= new InventoryIngredient(this,ingredient,quantity);
            this.inventoryIngredient.add(inventoryIngredient);
        }
    }
    
>>>>>>> origin/Spring-test-Alex6dev
    //#region get/set 
    public Integer getIdManager() {
        return idManager;
    }


    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
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

    public void addIngredientQuantity(Ingredient ingredient){
        Integer quantity = ingredientQuantity.get(ingredient) ;
        if(quantity != null){
            quantity++ ;
            ingredientQuantity.replace(ingredient, quantity) ;
        }else{
            ingredientQuantity.put(ingredient, 1) ;
        }
    }

<<<<<<< HEAD
    public void removeIngredientQuantity(Ingredient ingredient){
        Integer quantity = ingredientQuantity.get(ingredient) ;
        if(quantity != null){
            quantity-- ;
            if(quantity <= 0){
                ingredientQuantity.remove(ingredient) ;
            }else{
                ingredientQuantity.replace(ingredient, quantity) ;
            }
        }
    }
    
=======
    public Map<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Map<Ingredient, Integer> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

>>>>>>> origin/Spring-test-Alex6dev
    // #endregion

}