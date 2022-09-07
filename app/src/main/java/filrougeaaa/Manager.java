package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Manager extends Model {
    protected String name;
    protected int reputation;
    protected int chest;
    protected int exp;
    protected int maxExp;
    protected int level;
    protected User user;
    protected Map<Integer, Integer> inventoryIngredient;
    /*
     * protected Ingredient[] inventory;
     * protected Place[] places;
     * protected Reservation[] reservation;
     */

    /**
     * Generate a manager from database
     * 
     * @param id Database manager id
     */
    public Manager() {
        this.name = "" ;
        this.reputation = 0 ;
        this.chest = 0 ;
        this.exp = 0 ;
        this.maxExp = 0 ;
        this.level = 0 ;
        this.user = new User() ;
        this.inventoryIngredient = new HashMap<Integer,Integer>() ;
    }

    public Manager(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM manager  WHERE id_manager =" + id);
            if (resultat.next()) {
                this.name = resultat.getString(2);
                this.reputation = resultat.getInt(3);
                this.chest = resultat.getInt(4);
                this.level = resultat.getInt(5);
                this.exp = resultat.getInt(6);
                this.user = new User(resultat.getInt(7));
                this.inventoryIngredient = new HashMap<Integer,Integer>() ;
                this.id = id;
            }
            this.listInventoryIngredient();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public boolean get() {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM manager WHERE id_manager = " + this.id);
            if (resultat.next()) {
                this.name = resultat.getString(2);
                this.reputation = resultat.getInt(3);
                this.chest = resultat.getInt(4);
                this.level = resultat.getInt(5);
                this.exp = resultat.getInt(6);
                this.user = new User(resultat.getInt(7));
                return true;
            }
            this.listInventoryIngredient();
            return true;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }
    @Override
    public boolean get(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM manager WHERE id_manager = " + id);
            if (resultat.next()) {
                this.name = resultat.getNString(2);
                this.reputation = resultat.getInt(3);
                this.chest = resultat.getInt(4);
                this.level = resultat.getInt(5);
                this.exp = resultat.getInt(6);
                this.user = new User(resultat.getInt(7));
                this.id = id;
                return true;
            }
            this.listInventoryIngredient();
            return true;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean save() {
        String sql = "";
        if (this.id != 0) {

            sql = "UPDATE manager " +
                    "SET name = ?, reputation = ?, chest = ?, level = ?, experience = ?, id_user = ? " +
                    "WHERE id_manager = ?";
        } else {
            sql = "INSERT INTO manager(name, reputation, chest, level, experience, id_user) VALUES(?, ?, ?, ?, ?, ?)";

        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.name);
            pstmt.setFloat(2, this.reputation);
            pstmt.setFloat(3, this.chest);
            pstmt.setInt(4, this.level);
            pstmt.setFloat(5, this.exp);
            pstmt.setInt(6, this.user.getId());

            if (this.id != 0)
                pstmt.setInt(7, this.id);

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (this.id == 0 && keys.next()) {
                this.id = keys.getInt(1);
                return true;
            } else if (this.id != 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }
    }

    // #region get/set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<Integer, Integer> getInventory() {
        return inventoryIngredient;
    }

    public void setInventory(Map<Integer, Integer> inventoryIngredient) {
        this.inventoryIngredient = inventoryIngredient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public int getId() {
        return this.id;
    }
    // #endregion

//// REGION INVENTORY INGREDIENT

    public boolean enoughMoneyToBuy(int id_ingredient,int quantity){
        // vérification que le manager possède suffisament d'argent
        int buyingPrice = 0 ;

        Ingredient thisIngredient = new Ingredient() ;
        thisIngredient.get(id_ingredient) ;
        buyingPrice = thisIngredient.getBuyingPrice()*quantity ;
        if(buyingPrice > this.chest){
            return false ;
        }else{
            return true ;
        }
    }
    private boolean haveQuantityIngredientInInventaire(Recipe recipe){
        //
        for (Integer id_ingredients : recipe.getTabIngredients().keySet()) {
            if(this.inventoryIngredient.get(id_ingredients) != null){
                if(recipe.getTabIngredients().get(id_ingredients) > this.inventoryIngredient.get(id_ingredients)){
                    return false;
                }else{
                    return true ;
                }
            }else{
                return false ;
            }
        }
        return true;
    }
    public boolean requestRecipe(Recipe recipe){
        if(haveQuantityIngredientInInventaire(recipe)){
            for (Integer id_ingredient : recipe.getTabIngredients().keySet()) {
                int quantityInit=this.inventoryIngredient.get(id_ingredient);
                int quantityConsom= recipe.getTabIngredients().get(id_ingredient);
                this.inventoryIngredient.put(id_ingredient,quantityInit-quantityConsom);
            }
            return true;
        }    
        return false;
    }

    public Map<Integer,Integer> listInventoryIngredient(){
        try{
            ResultSet resultat = DBManager.execute("SELECT id_ingredient,quantity FROM inventory_ingredient WHERE id_manager = "+this.id+" ;");
            
            while(resultat.next()){
                inventoryIngredient.put(resultat.getInt("id_ingredient"), resultat.getInt("quantity")) ;
            }
            return inventoryIngredient ;
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null ;
        }
    }

    /* fonction doublons avec la méthode "listInventoryIngredient()" + cette méthode est un get qui ne renvoie rien...
    private void getInventoryBDD(){
        try{
            ResultSet resultat2=DBManager.execute("SELECT id_ingredient , quantity FROM inventory_ingredient WHERE id_manager= "+this.id+" ;");
            this.inventoryIngredient=new HashMap<Integer,Integer>();
            while(resultat2.next()){
                this.inventoryIngredient.put(resultat2.getInt("id_ingredient"),resultat2.getInt("quantity"));
            }

        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    */
    public boolean buyIngredient(int idIngredient, int quantity){
        Map<Integer,Integer> listInventoryIngredient = new HashMap<Integer,Integer>() ;
        Ingredient ingredient = new Ingredient(idIngredient) ;
        listInventoryIngredient = listInventoryIngredient();
        int quantityBDD = 0 ;
        String sql = "";

        if(enoughMoneyToBuy(idIngredient,quantity)){     // si le Manager possède suffisement d'argents
            this.chest -= ingredient.getBuyingPrice() ;

            if(listInventoryIngredient.get(idIngredient) == null){
                listInventoryIngredient.put(idIngredient,1);
                
                sql = "INSERT INTO inventory_ingredient(quantity, id_manager, id_ingredient) VALUES(?, ?, ?)";
            }else{
                quantityBDD = listInventoryIngredient.get(idIngredient) ; // listInventoryIngredient => Map<Integer,Integer>>
                quantityBDD += quantity ;
                listInventoryIngredient.replace(idIngredient, quantityBDD) ;
                sql = "UPDATE inventory_ingredient " +
                    "SET quantity = ? " +
                    "WHERE id_manager = ? AND id_ingredient = ? ";
                try {
                    PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setInt(1, quantityBDD);
                    pstmt.setInt(2, this.id);
                    pstmt.setInt(3, idIngredient);
        
                    pstmt.executeUpdate();
                    ResultSet keys = pstmt.getGeneratedKeys();
                    if (this.id == 0 && keys.next()) {
                        this.id = keys.getInt(1);
                        return true;
                    } else if (this.id != 0)
                        return true;
                    else
                        return false;
        
                } catch (SQLException e) {
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("VendorError: " + e.getErrorCode());
                    return false;
                }
            }
            
        }else{
            return false ;
        }
        return false ;
    }

    public List<Integer> getListPlace(){
        List<Integer> listPlace = new ArrayList<Integer>() ;
        try{
            ResultSet resultat = DBManager.execute("SELECT id_place FROM place WHERE id_manager = "+this.id+" ;");
            
            while(resultat.next()){
                listPlace.add(resultat.getInt("id_place")) ;
            }
            return listPlace ;
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null ;
        }
    }
    /*
     * public Place[] getPlaces() {
     * return places;
     * }
     * 
     * public void setPlaces(Place[] places) {
     * this.places = places;
     * }
     * 
     * public Reservation[] getReservation() {
     * return reservation;
     * }
     * 
     * public void setReservation(Reservation[] reservation) {
     * this.reservation = reservation;
     * }
     */
}