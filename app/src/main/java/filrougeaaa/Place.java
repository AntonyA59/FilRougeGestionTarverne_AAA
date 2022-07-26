package filrougeaaa;

import filrougeaaa.Enumeration.EType;
import filrougeaaa.utils.Model;

public class Place extends Model {
    protected String name;
    protected EType type;
    protected int level;

    
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
    public EType getType() {
        return type;
    }
    public void setType(EType type) {
        this.type = type;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    //#endregion
}
