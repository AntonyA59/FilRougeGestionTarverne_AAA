package filrougeaaa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="recipe")
public class Recipe{
	@Id
    @Column(name = "id_recipe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRecipe;

	@Column(name="name")
    private String name;

	@Column(name="selling_price")
    private Integer sellingPrice;

	@Column(name="level")
    private Integer level ;

	@Column(name="consommation_time")
	@Temporal(TemporalType.TIME)
    private Time consommationTime ;

	@Column(name="preparation_time")
	@Temporal(TemporalType.TIME)
    private Time preparationTime ;

	@Column(name="peremption_date")
	@Temporal(TemporalType.DATE)
    private Date peremptionDate;

	@Column(name="exp_given")
    private Integer expGiven;

	@ManyToOne
    @JoinColumn(name = "id_subcategory")
    private SubCategory subCategory;

	public Recipe() {
		this.name = "" ;
		this.level = 0 ;
		this.sellingPrice = 0 ;
		this.consommationTime = new Time(0) ;
		this.preparationTime = new Time(0) ;
		this.peremptionDate = new Date(0) ;
		this.expGiven = 0 ;
		subCategory = new SubCategory() ;
	}

//#region get/set

	public String getName() {
		return name;
	}

	public void setIdRecipe(Integer idRecipe) {
		this.idRecipe = idRecipe;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setExpGiven(Integer expGiven) {
		this.expGiven = expGiven;
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

	public Integer getIdRecipe() {
		return idRecipe;
	}

//#endregion

/*
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
	
	private void saveIngredientsQuantityBDD(){
		String sqlIngredientQuantity ;
		
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
	*/

}