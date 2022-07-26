package filrougeaaa;

import filrougeaaa.utils.Model;
import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Date;


public class Recipe extends Model{
    protected String name;
    protected int level ;
    protected int sellingPrice;
    protected Time consommationTime ;
    protected Timestamp preparationTime ;
    protected Date expiration;
    protected int expGiven;
    protected Ingredient[] ingredient ;
    protected SubCategory subCategory;

	@Override
	public boolean get(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
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

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public int getExpGiven() {
		return expGiven;
	}

	public void setExpGiven(int expGiven) {
		this.expGiven = expGiven;
	}

	public Ingredient[] getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient[] ingredient) {
		this.ingredient = ingredient;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
//#endregion
	
}