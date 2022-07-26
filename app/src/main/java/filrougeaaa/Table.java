package filrougeaaa;

import filrougeaaa.utils.Model;

public class Table extends Model {
    protected int numberPlace;
    protected float hygiene;
    protected float posX;
    protected float posY;
    protected Place place;

    
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
    public int getNumberPlace() {
        return numberPlace;
    }
    public void setNumberPlace(int numberPlace) {
        this.numberPlace = numberPlace;
    }
    public float getHygiene() {
        return hygiene;
    }
    public void setHygiene(float hygiene) {
        this.hygiene = hygiene;
    }
    public float getPosX() {
        return posX;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }
    public float getPosY() {
        return posY;
    }
    public void setPosY(float posY) {
        this.posY = posY;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
//#endregion    
    
}
