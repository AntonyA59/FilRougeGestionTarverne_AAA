package filrougeaaa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Customer extends Model {
    protected int purseOfGold=0;
    protected float happiness=0;
    protected float hunger=0;
    protected float thirst=0;
    protected float nausea=0;
    protected float alcohol=0;
    protected float toilet=0;
    protected Time timeInTavern=null;
    protected float nauseaTolerance=0;
    protected float alcoholTolerance=0;
    protected int gender=0;
    protected int expGiven=0;
    protected Table table= null;
    protected ArrayList<Recipe> command;
    protected ArrayList<Reservation> reservation;
    Random rand= new Random();


    public Customer() {
        //min and max value to modify according to the game later
        this.purseOfGold = rand.nextInt(10,20);
        this.happiness = rand.nextFloat(0,1);
        this.hunger = rand.nextFloat(0,1);
        this.thirst = rand.nextFloat(0,1);
        this.nausea = rand.nextFloat(0,1);
        this.alcohol = rand.nextFloat(0,1);
        this.toilet = rand.nextFloat(0,1);
        this.timeInTavern = new Time(rand.nextLong(10,20));
        this.nauseaTolerance = rand.nextFloat(0,1);
        this.alcoholTolerance = rand.nextFloat(0,1);
        this.gender = rand.nextInt(0,1);
        this.expGiven = rand.nextInt(5,10);
    }
    
    /**
     * Generate a character from database
     * 
     * @param id Database character id
     */
    public Customer(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM customer  WHERE id_customer =" + id);
            if (resultat.next()) {
                this.purseOfGold = resultat.getInt(2);
                this.happiness = resultat.getFloat(3);
                this.hunger = resultat.getFloat(4);
                this.thirst = resultat.getFloat(5);
                this.nausea = resultat.getFloat(6);
                this.toilet = resultat.getFloat(7);
                this.timeInTavern = resultat.getTime(8);
                this.nauseaTolerance = resultat.getInt(9);
                this.alcoholTolerance = resultat.getInt(10);
                this.gender = resultat.getInt(11);
                this.expGiven = resultat.getInt(12);
                this.table = new Table(resultat.getInt(13));
                this.id = id;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }



    @Override
    public boolean get() {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM customer WHERE id_customer = " + this.id);
            if (resultat.next()) {
                this.purseOfGold = resultat.getInt(2);
                this.happiness = resultat.getFloat(3);
                this.hunger = resultat.getFloat(4);
                this.thirst = resultat.getFloat(5);
                this.nausea = resultat.getFloat(6);
                this.toilet = resultat.getFloat(7);
                this.timeInTavern = resultat.getTime(8);
                this.nauseaTolerance = resultat.getFloat(9);
                this.alcoholTolerance = resultat.getFloat(10);
                this.gender = resultat.getInt(11);
                this.expGiven = resultat.getInt(12);
                this.table = new Table(resultat.getInt(13));
                this.alcohol = resultat.getFloat(14);
                return true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean get(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM customer WHERE id_customer = " + id);
            if (resultat.next()) {
                this.purseOfGold = resultat.getInt(2);
                this.happiness = resultat.getFloat(3);
                this.hunger = resultat.getFloat(4);
                this.thirst = resultat.getFloat(5);
                this.nausea = resultat.getFloat(6);
                this.toilet = resultat.getFloat(7);
                this.timeInTavern = resultat.getTime(8);
                this.nauseaTolerance = resultat.getFloat(9);
                this.alcoholTolerance = resultat.getFloat(10);
                this.gender = resultat.getInt(11);
                this.expGiven = resultat.getInt(12);
                this.table = new Table(resultat.getInt(13));
                this.alcohol = resultat.getFloat(14);
                this.id = id;
                return true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean save() {
        String sql = "";
        String updateIdTable = "";
        String insertIdTable= "";
        String value = "";
        if(this.table != null){
            updateIdTable = ", id_table = ?";
            insertIdTable = ", id_table";
            value = ", ?";
        }
        if (this.id != 0) {
            sql = "UPDATE customer " +
                    "SET purse_of_gold = ?, happiness = ?, hunger = ?, thirst = ?, nausea = ?, alcohol = ?, toilet = ? , time_in_tavern = ?, nausea_tolerance = ?, alcohol_tolerance = ?, gender = ?, exp_given = ?"+ updateIdTable
                    +" WHERE id_customer = ? ";
        } else {
            sql = "INSERT INTO customer(purse_of_gold, happiness, hunger, thirst, nausea, alcohol, toilet, time_in_tavern, nausea_tolerance, alcohol_tolerance, gender, exp_given "+ insertIdTable +") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? "+ value +")";

        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, this.purseOfGold);
            pstmt.setFloat(2, this.happiness);
            pstmt.setFloat(3, this.hunger);
            pstmt.setFloat(4, this.thirst);
            pstmt.setFloat(5, this.nausea);
            pstmt.setFloat(6, this.alcohol);
            pstmt.setFloat(7, this.toilet);
            pstmt.setTime(8, this.timeInTavern);
            pstmt.setFloat(9, this.nauseaTolerance);
            pstmt.setFloat(10, this.alcoholTolerance);
            pstmt.setInt(11, this.gender);
            pstmt.setInt(12, this.expGiven);
            if(this.table != null){
                
                pstmt.setInt(13, this.table.getId());
            }
            if (id != 0){
                int param = 13;

                if(this.table != null){
                    param++;
                }

                pstmt.setInt(param, this.id);
            }

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (this.id == 0 && keys.next()) {
                this.id = keys.getInt(1);
                return true;
            } else if (this.id != 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }
    }

    public void assignTable(Table table){
        this.setTable(table);
        this.save();
    }
    
    // #region Get/Set
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

    public Time getTimeInTavern() {
        return timeInTavern;
    }

    public void setTimeInTavern(Time timeInTavern) {
        this.timeInTavern = timeInTavern;
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

    @Override
    public int getId() {
        return this.id;
    }

    // #endregion
}
