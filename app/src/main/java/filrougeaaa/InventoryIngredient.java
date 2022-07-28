package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class InventoryIngredient extends Model {
    int quantity ;
    Manager manager ;
    Ingredient ingredient ;

    public InventoryIngredient() {
        this.quantity = 0 ;
        this.manager = new Manager() ;
        this.ingredient = new Ingredient() ;
    }

    public int getQuantity() {
        return quantity;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Manager getManager() {
        return manager;
    }



    public void setManager(Manager manager) {
        this.manager = manager;
    }



    public Ingredient getIngredient() {
        return ingredient;
    }



    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public InventoryIngredient(int id) {
        try{
			ResultSet resultat = DBManager.execute("SELECT * FROM inventory_ingredient WHERE id_ii = "+id) ;
			if(resultat.next()){
                this.quantity = resultat.getInt("quantity") ;
				this.manager = new Manager(resultat.getInt("id_manager")) ;
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient")) ;
				this.id = id ; 
			}
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
		}
    }

    @Override
    public boolean get() {
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM inventory_ingredient WHERE id_ii = "+ this.id);
            if(resultat.next()){
                this.quantity = resultat.getInt("quantity") ;
                this.manager = new Manager(resultat.getInt("id_manager")) ;
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient")) ;
				return true;
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return false;
    }

    @Override
    public boolean get(int id) {
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM inventory_ingredient WHERE id_ii = "+id);
            if(resultat.next()){
                this.quantity = resultat.getInt("quantity") ;
                this.manager = new Manager(resultat.getInt("id_manager")) ;
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient")) ;
                this.id = id;
				return true;
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return false;
    }

    @Override
    public boolean save() {
        String sql ;
        if(this.id != 0){
            sql = "UPDATE inventory_ingredient SET quantity=?,id_manager=?,id_ingredient=? WHERE id_ii = ?" ;
        }else{
            sql = "INSERT INTO inventory_ingredient (quantity,id_manager,id_ingredient) VALUES (?,?,?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setInt(1,this.quantity);
			pstmt.setInt(2, this.manager.getId());
            pstmt.setInt(3, this.ingredient.getId());
            if(this.id != 0)
                pstmt.setInt(4, this.id);
            
            pstmt.executeUpdate();
                ResultSet keys = pstmt.getGeneratedKeys();
                if(this.id == 0 && keys.next()){
                    this.id = keys.getInt(1);
                    return true;
                }
                else if(this.id != 0)
                    return true;
                else
                    return false;
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError"+ ex.getErrorCode());
            return false ;
        }
    }

    @Override
    public int getId() {
        return this.id ;
    }

    public Map<Integer,Integer> listIngredientByManager(int idManager){
        try{
            Map<Integer,Integer> listIngredient = new HashMap<Integer,Integer>() ;
            ResultSet resultat = DBManager.execute("SELECT id_ii,quantity FROM inventory_ingredient WHERE id_manager = "+idManager+";");
            
            while(resultat.next()){
                listIngredient.put(resultat.getInt("id_ii"), resultat.getInt("quantity")) ;
            }
            return listIngredient ;
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null ;
        }
    }
}
