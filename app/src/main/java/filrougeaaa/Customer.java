package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Customer extends Model {
    protected int purseOfGold;
    protected float happiness;
    protected float hunger;
    protected float thirst;
    protected float nausea;
    protected float alcohol;
    protected float toilet;
    protected Timestamp timeInTavern ;
    protected float nauseaTolerance;
    protected float alcoholTolerance;
    protected int gender;
    protected int expGiven;
    protected Table table;
    protected ArrayList<Recipe> command;
    protected ArrayList<Reservation> reservation;

    /**
     * Generate a character from database
     * 
     * @param id Database character id
     */
    
    public Customer() {
        this.purseOfGold = 0 ;
        this.happiness = 0 ;
        this.hunger = 0 ;
        this.thirst = 0 ;
        this.nausea = 0 ;
        this.alcohol = 0 ;
        this.toilet = 0 ;
        this.timeInTavern = null ;
        this.nauseaTolerance = 0;
        this.alcoholTolerance = 0 ;
        this.gender = 0;
        this.expGiven = 0 ;
        this.table = new Table() ;
    }

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
                this.timeInTavern = resultat.getTimestamp(8);
                this.nauseaTolerance = resultat.getInt(9);
                this.alcoholTolerance = resultat.getInt(10);
                this.gender = resultat.getInt(11);
                this.expGiven = resultat.getInt(12);
                // this.table = new Table(resultat.getInt(13));
                this.id = id;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public boolean get(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM customer WHERE id_customer = " + this.id);
            if (resultat.next()) {
                this.purseOfGold = resultat.getInt(2);
                this.happiness = resultat.getFloat(3);
                this.hunger = resultat.getFloat(4);
                this.thirst = resultat.getFloat(5);
                this.nausea = resultat.getFloat(6);
                this.toilet = resultat.getFloat(7);
                this.timeInTavern = resultat.getTimestamp(8);
                this.nauseaTolerance = resultat.getFloat(9);
                this.alcoholTolerance = resultat.getFloat(10);
                this.gender = resultat.getInt(11);
                this.expGiven = resultat.getInt(12);
                // this.table = new Table(resultat.getInt(13));
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
        if (this.id != 0) {

            sql = "UPDATE customer " +
                    "SET purse_of_gold = ?, happiness = ?, hunger = ?, thirst = ?, nausea = ?, toilet = ? , time_in_tavern = ?, nausea_tolerance = ?, alcohol_tolerance = ?, gender = ?, exp_given = ? , id_table = ?   " +
                    "WHERE id_customer = ? ";
        } else {
            sql = "INSERT INTO customer(purse_of_gold, happiness, hunger, thirst, nausea, toilet, time_in_tavern, nausea_tolerance, alcohol_tolerance, gender, exp_given, id_table) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, this.purseOfGold);
            pstmt.setFloat(2, this.happiness);
            pstmt.setFloat(3, this.hunger);
            pstmt.setFloat(4, this.thirst);
            pstmt.setFloat(5, this.nausea);
            pstmt.setFloat(6, this.toilet);
            pstmt.setTimestamp(7, this.timeInTavern);
            pstmt.setFloat(8, this.nauseaTolerance);
            pstmt.setFloat(9, this.alcoholTolerance);
            pstmt.setInt(10, this.gender);
            pstmt.setInt(11, this.expGiven);
            pstmt.setInt(12, this.table.id);
            if (id != 0)
                pstmt.setInt(13, this.id);

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
        return timeInTavern;
    }
    public void setTimeInTavern(Timestamp timeInTavern) {
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

//#endregion

    @Override
    public int getId() {
        return this.id;
    }
}
