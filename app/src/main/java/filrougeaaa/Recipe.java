package filrougeaaa;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;
import java.sql.Timestamp;

import java.sql.Time;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recipe extends Model{
    protected String name;
    protected int level ;
    protected int sellingPrice;
    protected Time consommationTime ;
    protected Timestamp preparationTime ;
    protected Date peremptionDate;
    protected int expGiven;
    protected SubCategory subCategory;
	
	public Recipe() {
		this.name = "" ;
		this.level = 0 ;
		this.sellingPrice = 0 ;
		this.consommationTime = null ;
		this.preparationTime = null ;
		this.peremptionDate = null ;
		this.expGiven = 0 ;
		subCategory = new SubCategory() ;
	}
	public Recipe(int id) {
		try{
			ResultSet resultat = DBManager.execute("SELECT * FROM recipe WHERE id_recipe = "+id) ;
			if(resultat.next()){
				this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.sellingPrice = resultat.getInt("selling_price") ;
				this.consommationTime = resultat.getTime("consommation_time") ;
				this.preparationTime = resultat.getTimestamp("preparation_time") ;
				this.peremptionDate = resultat.getDate("peremption_date") ;
				this.expGiven = resultat.getInt("exp_given") ;
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
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean get(int id) {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe WHERE id_recipe = "+id);
            if(resultat.next()){
                this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.sellingPrice = resultat.getInt("selling_price") ;
				this.consommationTime = resultat.getTime("consommation_time") ;
				this.preparationTime = resultat.getTimestamp("preparation_time") ;
				this.peremptionDate = resultat.getDate("peremption_date") ;
				this.expGiven = resultat.getInt("exp_given") ;
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
            sql = "UPDATE recipe SET name=?,level=?,selling_price=?,consommation_time=?,preparation_time=?,expiration=?,exp_given=?,id_subcategory=? WHERE id_recipe = ?" ;
        }else{
            sql = "INSERT INTO recipe (name,level,selling_price,consommation_time,preparation_time,expiration,exp_given,id_subcategory) VALUES (?,?,?,?,?,?,?,?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setString(1, this.name);
			pstmt.setInt(2, this.level);
			pstmt.setInt(3, this.sellingPrice);
			pstmt.setTime(4, this.consommationTime);
			pstmt.setTimestamp(5, this.preparationTime);
			pstmt.setDate(6,this.peremptionDate);
			pstmt.setInt(7, this.expGiven);
			pstmt.setInt(8,this.subCategory.getId());
            if(this.id != 0)
                pstmt.setInt(9, this.id);
            
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
//#region get/set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Time getConsommationTime() {
		return consommationTime;
	}

	public void setConsommationTime(Time consommationTime) {
		this.consommationTime = consommationTime;
	}

	public Timestamp getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Timestamp preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Date getPeremptionDate() {
		return peremptionDate;
	}

	public void setPeremptionDate(Date peremptionDate) {
		this.peremptionDate = peremptionDate;
	}

	public int getExpGiven() {
		return expGiven;
	}

	public void setExpGiven(int expGiven) {
		this.expGiven = expGiven;
	}
	/*
	public Ingredient[] getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient[] ingredient) {
		this.ingredient = ingredient;
	}
	*/
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