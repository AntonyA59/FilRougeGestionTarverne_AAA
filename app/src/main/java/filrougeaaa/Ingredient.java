package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Ingredient extends Model{
    protected String name;
	protected int level;
    protected int buyingPrice;
    protected SubCategory subCategory;
	
	public Ingredient() {
		this.name = "" ;
		this.level = 0 ;
		this.buyingPrice = 0 ;
		this.subCategory = new SubCategory() ;
	}
	public Ingredient(int id) {
		try{
			ResultSet resultat = DBManager.execute("SELECT * FROM ingredient WHERE id_ingredient = "+id) ;
			if(resultat.next()){
				this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.buyingPrice = resultat.getInt("buying_price") ;
				this.subCategory = new SubCategory(resultat.getInt("id_subcategory")) ;
				this.id = id ;
			}
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
		}
	}
	@Override
	public boolean get(int id) {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM ingredient WHERE id_ingredient = "+id);
            if(resultat.next()){
                this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.buyingPrice = resultat.getInt("buying_price") ;
				this.subCategory = new SubCategory(resultat.getInt("id_subcategory")) ;
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
            sql = "UPDATE ingredient SET name=?,level=?,buying_price=?,id_subcategory=? WHERE id_ingredient = ?" ;
        }else{
            sql = "INSERT INTO ingredient (name,level,buying_price,id_subcategory) VALUES (?,?,?,?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setString(1, this.name);
			pstmt.setInt(2, this.level);
			pstmt.setInt(3, this.buyingPrice);
			pstmt.setInt(4,this.subCategory.getId(id));
            if(this.id != 0)
                pstmt.setInt(5, this.id);
            
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

//#region Get/Set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
//#endregion
	@Override
	public int getId() {
		return this.id;
	}
	
} 