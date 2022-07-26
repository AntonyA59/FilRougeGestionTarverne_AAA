package filrougeaaa;
import filrougeaaa.utils.Model;
public class Category extends Model{
    protected String name;

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
//#endregion
	
}