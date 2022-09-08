package filrougeaaa;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;


import jakarta.persistence.*;
import jakarta.persistence.Table;
@Entity
@Table(name = "customer")
public class Customer{
    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer customerId;

    @Column(name = "purse_of_gold")
    private Integer purseOfGold;

    private Float happiness;

    private Float hunger;

    private Float thirst;

    @Column(name="nausea_level")
    private Float nauseaLevel;

    @Column(name = "alcohol_level")
    private Float alcoholLevel;
    
    private Float toilet;

    @Column(name = "time_in_tavern",nullable = true)
    @Temporal(TemporalType.TIME)
    private Time timeInTavern;

    @Column(name = "nausea_tolerance")
    private Float nauseaTolerance;

    @Column(name = "alcohol_tolerance")
    private Float alcoholTolerance;
    
    private Integer gender;

    @Column(name = "exp_given")
    private Integer expGiven;

    @ManyToOne
    @JoinColumn(name = "id_table",nullable = true)
    private filrougeaaa.Table table;

    @OneToMany(mappedBy = "customer")
    private ArrayList<RecipeCustomer> commandList=new ArrayList<RecipeCustomer>();
    
    @OneToMany(mappedBy = "reservationId")
    protected ArrayList<Reservation> reservation=new ArrayList<Reservation>();

    @Transient
    Random rand= new Random();


    public Customer() {
        //min and max value to modify according to the game later
        this.purseOfGold = rand.nextInt(10,20);
        this.happiness = rand.nextFloat(0,1);
        this.hunger = rand.nextFloat(0,1);
        this.thirst = rand.nextFloat(0,1);
        this.nauseaLevel = rand.nextFloat(0,1);
        this.alcoholLevel = rand.nextFloat(0,1);
        this.toilet = rand.nextFloat(0,1);
        this.timeInTavern = new Time(rand.nextLong(10,20));
        this.nauseaTolerance = rand.nextFloat(0,1);
        this.alcoholTolerance = rand.nextFloat(0,1);
        this.gender = rand.nextInt(0,1);
        this.expGiven = rand.nextInt(5,10);
    }
    
    
    // #region Get/Set
    public Integer getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public Integer getPurseOfGold() {
        return purseOfGold;
    }


    public void setPurseOfGold(Integer purseOfGold) {
        this.purseOfGold = purseOfGold;
    }


    public Float getHappiness() {
        return happiness;
    }


    public void setHappiness(Float happiness) {
        this.happiness = happiness;
    }


    public Float getHunger() {
        return hunger;
    }


    public void setHunger(Float hunger) {
        this.hunger = hunger;
    }


    public Float getThirst() {
        return thirst;
    }


    public void setThirst(Float thirst) {
        this.thirst = thirst;
    }


    public Float getNauseaLevel() {
        return nauseaLevel;
    }


    public void setNauseaLevel(Float nauseaLevel) {
        this.nauseaLevel = nauseaLevel;
    }


    public Float getAlcoholLevel() {
        return alcoholLevel;
    }


    public void setAlcoholLevel(Float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }


    public Float getToilet() {
        return toilet;
    }


    public void setToilet(Float toilet) {
        this.toilet = toilet;
    }


    public Time getTimeInTavern() {
        return timeInTavern;
    }


    public void setTimeInTavern(Time timeInTavern) {
        this.timeInTavern = timeInTavern;
    }


    public Float getNauseaTolerance() {
        return nauseaTolerance;
    }


    public void setNauseaTolerance(Float nauseaTolerance) {
        this.nauseaTolerance = nauseaTolerance;
    }


    public Float getAlcoholTolerance() {
        return alcoholTolerance;
    }


    public void setAlcoholTolerance(Float alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
    }


    public Integer getGender() {
        return gender;
    }


    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public Integer getExpGiven() {
        return expGiven;
    }


    public void setExpGiven(Integer expGiven) {
        this.expGiven = expGiven;
    }


    public filrougeaaa.Table getTable() {
        return table;
    }


    public void setTable(filrougeaaa.Table table) {
        this.table = table;
    }


    public ArrayList<RecipeCustomer> getCommandList() {
        return commandList;
    }


    public void setCommandList(ArrayList<RecipeCustomer> commandList) {
        this.commandList = commandList;
    }


    public ArrayList<Reservation> getReservation() {
        return reservation;
    }


    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservation = reservation;
    }


    public Random getRand() {
        return rand;
    }


    public void setRand(Random rand) {
        this.rand = rand;
    }
    
    // #endregion
}
