package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class RecipeCustomer extends Model {
    Customer customer ;
    Recipe recipe ;
    int quantity ;
    
    public RecipeCustomer() {
        recipe = new Recipe() ;
        quantity = 0 ;
    }
    public RecipeCustomer(int id){
        try{
			ResultSet resultat = DBManager.execute("SELECT * FROM recipe_customer WHERE id_rc = "+id) ;
			if(resultat.next()){
                this.customer = new Customer(resultat.getInt("id_customer")) ;
				this.recipe = new Recipe(resultat.getInt("id_recipe")) ;
				this.quantity = resultat.getInt("quantity") ;
				this.id = id ;
			}
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
		}
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public boolean get(int id) {
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe_customer WHERE id_rc = "+id);
            if(resultat.next()){
                this.customer = new Customer(resultat.getInt("id_customer")) ;
                this.recipe = new Recipe(resultat.getInt("id_recipe")) ;
				this.quantity = resultat.getInt("quantity") ;
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
            sql = "UPDATE recipe_customer SET id_customer=?,id_recipe=?,quantity=? WHERE id_rc = ?" ;
        }else{
            sql = "INSERT INTO recipe (id_customer,id_recipe,quantity) VALUES (?,?,?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setInt(1, this.customer.getId());
			pstmt.setInt(2, this.recipe.getId());
			pstmt.setInt(3,this.quantity);
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
        return this.id;
    }
    
}
