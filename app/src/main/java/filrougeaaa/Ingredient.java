package filrougeaaa;

import filrougeaaa.utils.Model;

public class Ingredient extends Model{
    protected String name;
    protected int buyingPrice;
    protected int level;
    protected SubCategory subCategory;

    
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}
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