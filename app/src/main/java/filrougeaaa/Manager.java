package filrougeaaa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {
    
    @Id
    @Column(name = "id_manager")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerID;
    
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
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    

    private Integer maxExp;

    
    
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Integer getMaxExp() {
        return maxExp;
    }


    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }


    public Map<Integer, Integer> getInventoryIngredient() {
        return inventoryIngredient;
    }


    public void setInventoryIngredient(Map<Integer, Integer> inventoryIngredient) {
        this.inventoryIngredient = inventoryIngredient;
    }
    // #endregion

    //#region INVENTORY INGREDIENT

    // public boolean enoughMoneyToBuy(int id_ingredient,int quantity){
    //     // vérification que le manager possède suffisament d'argent
    //     int buyingPrice = 0 ;

    //     Ingredient thisIngredient = new Ingredient() ;
    //     thisIngredient.get(id_ingredient) ;
    //     buyingPrice = thisIngredient.getBuyingPrice()*quantity ;
    //     if(buyingPrice > this.chest){
    //         return false ;
    //     }else{
    //         return true ;
    //     }
    // }
    // private boolean haveQuantityIngredientInInventaire(Recipe recipe){
    //     //
    //     for (Integer id_ingredients : recipe.getTabIngredients().keySet()) {
    //         if(this.inventoryIngredient.get(id_ingredients) != null){
    //             if(recipe.getTabIngredients().get(id_ingredients) > this.inventoryIngredient.get(id_ingredients)){
    //                 return false;
    //             }else{
    //                 return true ;
    //             }
    //         }else{
    //             return false ;
    //         }
    //     }
    //     return true;
    // }
    // public boolean requestRecipe(Recipe recipe){
    //     if(haveQuantityIngredientInInventaire(recipe)){
    //         for (Integer id_ingredient : recipe.getTabIngredients().keySet()) {
    //             int quantityInit=this.inventoryIngredient.get(id_ingredient);
    //             int quantityConsom= recipe.getTabIngredients().get(id_ingredient);
    //             this.inventoryIngredient.put(id_ingredient,quantityInit-quantityConsom);
    //         }
    //         return true;
    //     }    
    //     return false;
    // }

    // public Map<Integer,Integer> listInventoryIngredient(){
    //     try{
    //         ResultSet resultat = DBManager.execute("SELECT id_ingredient,quantity FROM inventory_ingredient WHERE id_manager = "+this.id+" ;");
            
    //         while(resultat.next()){
    //             inventoryIngredient.put(resultat.getInt("id_ingredient"), resultat.getInt("quantity")) ;
    //         }
    //         return inventoryIngredient ;
    //     }
    //     catch (SQLException ex) {
    //         // handle any errors
    //         System.out.println("SQLException: " + ex.getMessage());
    //         System.out.println("SQLState: " + ex.getSQLState());
    //         System.out.println("VendorError: " + ex.getErrorCode());
    //         return null ;
    //     }
    // }

    //  fonction doublons avec la méthode "listInventoryIngredient()" + cette méthode est un get qui ne renvoie rien...
    // private void getInventoryBDD(){
    //     try{
    //         ResultSet resultat2=DBManager.execute("SELECT id_ingredient , quantity FROM inventory_ingredient WHERE id_manager= "+this.id+" ;");
    //         this.inventoryIngredient=new HashMap<Integer,Integer>();
    //         while(resultat2.next()){
    //             this.inventoryIngredient.put(resultat2.getInt("id_ingredient"),resultat2.getInt("quantity"));
    //         }

    //     }catch(SQLException ex){
    //         System.out.println("SQLException: " + ex.getMessage());
    //         System.out.println("SQLState: " + ex.getSQLState());
    //         System.out.println("VendorError: " + ex.getErrorCode());
    //     }
    // }
    
    // public boolean buyIngredient(int idIngredient, int quantity){
    //     Map<Integer,Integer> listInventoryIngredient = new HashMap<Integer,Integer>() ;
    //     Ingredient ingredient = new Ingredient(idIngredient) ;
    //     listInventoryIngredient = listInventoryIngredient();
    //     int quantityBDD = 0 ;
    //     String sql = "";

    //     if(enoughMoneyToBuy(idIngredient,quantity)){     // si le Manager possède suffisement d'argents
    //         this.chest -= ingredient.getBuyingPrice() ;

    //         if(listInventoryIngredient.get(idIngredient) == null){
    //             listInventoryIngredient.put(idIngredient,1);
                
    //             sql = "INSERT INTO inventory_ingredient(quantity, id_manager, id_ingredient) VALUES(?, ?, ?)";
    //         }else{
    //             quantityBDD = listInventoryIngredient.get(idIngredient) ; // listInventoryIngredient => Map<Integer,Integer>>
    //             quantityBDD += quantity ;
    //             listInventoryIngredient.replace(idIngredient, quantityBDD) ;
    //             sql = "UPDATE inventory_ingredient " +
    //                 "SET quantity = ? " +
    //                 "WHERE id_manager = ? AND id_ingredient = ? ";
    //             try {
    //                 PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    //                 pstmt.setInt(1, quantityBDD);
    //                 pstmt.setInt(2, this.id);
    //                 pstmt.setInt(3, idIngredient);
        
    //                 pstmt.executeUpdate();
    //                 ResultSet keys = pstmt.getGeneratedKeys();
    //                 if (this.id == 0 && keys.next()) {
    //                     this.id = keys.getInt(1);
    //                     return true;
    //                 } else if (this.id != 0)
    //                     return true;
    //                 else
    //                     return false;
        
    //             } catch (SQLException e) {
    //                 System.out.println("SQLState: " + e.getSQLState());
    //                 System.out.println("SQLException: " + e.getMessage());
    //                 System.out.println("VendorError: " + e.getErrorCode());
    //                 return false;
    //             }
    //         }
            
    //     }else{
    //         return false ;
    //     }
    //     return false ;
    // }

    // public List<Integer> getListPlace(){
    //     List<Integer> listPlace = new ArrayList<Integer>() ;
    //     try{
    //         ResultSet resultat = DBManager.execute("SELECT id_place FROM place WHERE id_manager = "+this.id+" ;");
            
    //         while(resultat.next()){
    //             listPlace.add(resultat.getInt("id_place")) ;
    //         }
    //         return listPlace ;
    //     }
    //     catch (SQLException ex) {
    //         // handle any errors
    //         System.out.println("SQLException: " + ex.getMessage());
    //         System.out.println("SQLState: " + ex.getSQLState());
    //         System.out.println("VendorError: " + ex.getErrorCode());
    //         return null ;
    //     }
    // }
 //#endregion
}