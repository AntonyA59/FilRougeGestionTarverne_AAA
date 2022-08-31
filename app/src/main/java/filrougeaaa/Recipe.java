package filrougeaaa;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;
import java.util.ArrayList;
import java.util.HashMap;
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
    protected Time preparationTime ;
    protected Date peremptionDate;
    protected int expGiven;
    protected SubCategory subCategory;
	protected HashMap<Integer,Integer> tabIngredients;

	public Recipe() {
		this.name = "" ;
		this.level = 0 ;
		this.sellingPrice = 0 ;
		this.consommationTime = new Time(0) ;
		this.preparationTime = new Time(0) ;
		this.peremptionDate = new Date(0) ;
		this.expGiven = 0 ;
		subCategory = new SubCategory() ;
		tabIngredients = new HashMap<Integer,Integer>() ;
	}

	public Recipe(int id) {
		try{
			ResultSet resultat = DBManager.execute("SELECT * FROM recipe WHERE id_recipe = "+id+" ;") ;
			if(resultat.next()){
				this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.sellingPrice = resultat.getInt("selling_price") ;
				this.consommationTime = resultat.getTime("consommation_time") ;
				this.preparationTime = resultat.getTime("preparation_time") ;
				this.peremptionDate = resultat.getDate("peremption_date") ;
				this.expGiven = resultat.getInt("exp_given") ;
				this.subCategory = new SubCategory(resultat.getInt("id_subcategory")) ;
				this.id = id ;
			}
			this.getIngredientsQuantityBDD(id);
			
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
		}
	}

	private void getIngredientsQuantityBDD(int id_recipe){
		try{
			ResultSet resultat2=DBManager.execute("SELECT id_ingredient , quantity FROM recipe_ingredient WHERE id_recipe= "+id_recipe+" ;");
			this.tabIngredients=new HashMap<Integer,Integer>();
			while(resultat2.next()){
				this.tabIngredients.put(resultat2.getInt("id_ingredient"),resultat2.getInt("quantity"));
			}

		}catch(SQLException ex){
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
		}
	}
	@Override
	public boolean get() {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe WHERE id_recipe = "+ this.id);
            if(resultat.next()){
                this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.sellingPrice = resultat.getInt("selling_price") ;
				this.consommationTime = resultat.getTime("consommation_time") ;
				this.preparationTime = resultat.getTime("preparation_time") ;
				this.peremptionDate = resultat.getDate("peremption_date") ;
				this.expGiven = resultat.getInt("exp_given") ;
				this.subCategory = new SubCategory(resultat.getInt("id_subcategory"));
				this.getIngredientsQuantityBDD(this.id);
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
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe WHERE id_recipe = "+id+" ;");
            if(resultat.next()){
                this.name = resultat.getString("name") ;
				this.level = resultat.getInt("level") ;
				this.sellingPrice = resultat.getInt("selling_price") ;
				this.consommationTime = resultat.getTime("consommation_time") ;
				this.preparationTime = resultat.getTime("preparation_time") ;
				this.peremptionDate = resultat.getDate("peremption_date") ;
				this.expGiven = resultat.getInt("exp_given") ;
				this.subCategory = new SubCategory(resultat.getInt("id_subcategory")) ;
                this.id = id;
				this.getIngredientsQuantityBDD(id);
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
            sql = "UPDATE recipe SET name=? ,level=? ,selling_price=? ,consommation_time=? ,preparation_time=? ,peremption_date=? ,exp_given=? ,id_subcategory=? WHERE id_recipe = ? " ;
        }else{
            sql = "INSERT INTO recipe (name,level,selling_price,consommation_time,preparation_time,peremption_date,exp_given,id_subcategory) VALUES (?,?,?,?,?,?,?,?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setString(1, this.name);
			pstmt.setInt(2, this.level);
			pstmt.setInt(3, this.sellingPrice);
			pstmt.setTime(4, this.consommationTime);
			pstmt.setTime(5, this.preparationTime);
			pstmt.setDate(6,this.peremptionDate);
			pstmt.setInt(7, this.expGiven);
			pstmt.setInt(8,this.subCategory.getId());
			
            if(this.id != 0)
                pstmt.setInt(9, this.id);
            
            pstmt.executeUpdate();
                ResultSet keys = pstmt.getGeneratedKeys();
                if(this.id == 0 && keys.next()){
                    this.id = keys.getInt(1);
					this.saveIngredientsQuantityBDD();
                    return true;
                }
                else if(this.id != 0){
					this.saveIngredientsQuantityBDD();
                    return true;
				}else{
                    return false;
				}
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError"+ ex.getErrorCode());
            return false ;
        }
	}
	private void saveIngredientsQuantityBDD(){
		String sqlIngredientQuantity ;
		/*
		if(this.id!=0){
			sqlIngredientQuantity = "UPDATE recipe_ingredient SET quantity=? WHERE id_recipe = ? AND id_ingredient = ?" ;
		}else{
			sqlIngredientQuantity = "INSERT INTO recipe_ingredient (quantity,id_ingredient,id_recipe) VALUES (?,?,?)" ;
		}
		*/
		for (int id_ingredient : this.tabIngredients.keySet()){
			try{
				sqlIngredientQuantity = "SELECT COUNT(*) FROM recipe_ingredient WHERE id_recipe = "+this.id+" AND id_ingredient = "+id_ingredient ;
				ResultSet resultat = DBManager.execute(sqlIngredientQuantity) ;
            	if(resultat.next()){
					if(resultat.getInt("COUNT(*)") > 0){
						sqlIngredientQuantity = "UPDATE recipe_ingredient SET quantity=? WHERE id_recipe = ? AND id_ingredient = ?" ;
					}else{
						sqlIngredientQuantity = "INSERT INTO recipe_ingredient (quantity,id_ingredient,id_recipe) VALUES (?,?,?)" ;
					}
					PreparedStatement pstmtIngQuan =  DBManager.conn.prepareStatement(sqlIngredientQuantity,Statement.RETURN_GENERATED_KEYS) ;
					int quantity=this.tabIngredients.get(id_ingredient);
					if(this.id!=0){
						pstmtIngQuan.setInt(1,quantity);
						pstmtIngQuan.setInt(2,id_ingredient);
						pstmtIngQuan.setInt(3,this.id);
					}else{
						pstmtIngQuan.setInt(1,quantity);
						pstmtIngQuan.setInt(2,id_ingredient);
						pstmtIngQuan.setInt(3,this.id);
					}
					pstmtIngQuan.executeUpdate();
				}
			}catch(SQLException ex){
				System.out.println("SQLException" + ex.getMessage());
				System.out.println("SQLState" + ex.getSQLState());
				System.out.println("VendorError"+ ex.getErrorCode());
			}
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

	public Time getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Time preparationTime) {
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

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	public HashMap<Integer,Integer> getTabIngredients() {
		return tabIngredients;
	}

	public void setTabIngredients(HashMap<Integer, Integer> tabIngredients) {
		this.tabIngredients = tabIngredients;
	}
//#endregion

	public ArrayList<Recipe> listRecipeByLevel(int levelMax){
		try{
            ArrayList<Recipe> listRecipe = new ArrayList<Recipe>() ;
            ResultSet resultat = DBManager.execute("SELECT id_recipe FROM recipe WHERE level BETWEEN 1 AND "+levelMax+" ;");
			Recipe recipe ;
            
            while(resultat.next()){
				recipe = new Recipe(resultat.getInt("id_recipe")) ;
				listRecipe.add(recipe) ;
            }
            return listRecipe ;
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