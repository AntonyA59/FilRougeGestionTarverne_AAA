package filrougeaaa;

import java.sql.Timestamp;
import java.util.ArrayList;

import filrougeaaa.utils.Model;

public class Customer extends Model {
    protected int purseOfGold;
    protected float happiness;
    protected float hunger;
    protected float thirst;
    protected float nausea;
    protected float alcohol;
    protected float toilet;
    protected Timestamp TimeInTavern ;
    protected float nauseaTolerance;
    protected float alcoholTolerance;
    protected int gender;
    protected int expGiven;
    protected Table table;
    protected ArrayList<Recipe> command;
    protected ArrayList<Reservation> reservation;


    
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
    public int getPurseOfGold() {
        return purseOfGold;
    }
    public void setPurseOfGold(int purseOfGold) {
        this.purseOfGold = purseOfGold;
    }
    public float getHappiness() {
        return happiness;
    }
    public void setHappiness(float happiness) {
        this.happiness = happiness;
    }
    public float getHunger() {
        return hunger;
    }
    public void setHunger(float hunger) {
        this.hunger = hunger;
    }
    public float getThirst() {
        return thirst;
    }
    public void setThirst(float thirst) {
        this.thirst = thirst;
    }
    public float getNausea() {
        return nausea;
    }
    public void setNausea(float nausea) {
        this.nausea = nausea;
    }
    public float getAlcohol() {
        return alcohol;
    }
    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }
    public float getToilet() {
        return toilet;
    }
    public void setToilet(float toilet) {
        this.toilet = toilet;
    }
    public Timestamp getTimeInTavern() {
        return TimeInTavern;
    }
    public void setTimeInTavern(Timestamp timeInTavern) {
        TimeInTavern = timeInTavern;
    }
    public float getNauseaTolerance() {
        return nauseaTolerance;
    }
    public void setNauseaTolerance(float nauseaTolerance) {
        this.nauseaTolerance = nauseaTolerance;
    }
    public float getAlcoholTolerance() {
        return alcoholTolerance;
    }
    public void setAlcoholTolerance(float alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getExpGiven() {
        return expGiven;
    }
    public void setExpGiven(int expGiven) {
        this.expGiven = expGiven;
    }
    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public ArrayList<Recipe> getCommand() {
        return command;
    }
    public void setCommand(ArrayList<Recipe> command) {
        this.command = command;
    }
    public ArrayList<Reservation> getReservation() {
        return reservation;
    }
    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservation = reservation;
    }
//#endregion
    
}
