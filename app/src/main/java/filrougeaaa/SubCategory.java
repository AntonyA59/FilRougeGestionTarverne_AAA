package filrougeaaa;

import filrougeaaa.utils.Model;

public class SubCategory extends Model{
    protected String name;
    protected Category category;


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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	//#endregion get/set
	
}