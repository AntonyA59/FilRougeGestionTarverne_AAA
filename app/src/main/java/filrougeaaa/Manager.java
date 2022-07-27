package filrougeaaa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Manager extends Model{
    protected String name;
    protected int reputation;
    protected int chest;
    protected int exp;
    protected int maxExp;
    protected int level;
	protected User user;
    protected Ingredient[] inventory;
    protected Place[] places;
    protected Reservation[] reservation;



    /**
     * Generate a character from database
     * 
     * @param id Database character id
     */
    public Manager(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM manager  WHERE id_manager =" + id);
            if (resultat.next()) {
                this.name = resultat.getString(2);
                this.reputation = resultat.getInt(3);
                this.chest = resultat.getInt(4);
                this.level = resultat.getInt(5);
                //this.user = new User(resultat.getInt(6));
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
            ResultSet resultat = DBManager.execute("SELECT * FROM manager WHERE id_manager = " + this.id);
            if (resultat.next()) {
                this.name = resultat.getNString(2);
                this.reputation = resultat.getInt(3);
                this.chest = resultat.getInt(4);
                this.level = resultat.getInt(5);
                this.exp = resultat.getInt(6);
				// this.user = new User(resultat.getInt(7));
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

            sql = "UPDATE manager " +
                    "SET name = ?, reputation = ?, chest = ?, level = ?, experience = ?, id_user = ?" +
                    "WHERE id_manager = ? ";
        } else {
            sql = "INSERT INTO manager(name, reputation, chest, level, experience, id_user) VALUES(?, ?, ?, ?, ?, ?)";

        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.name);
            pstmt.setFloat(2, this.reputation);
            pstmt.setFloat(3, this.chest);
            pstmt.setFloat(4, this.level);
            pstmt.setFloat(5, this.exp);
            pstmt.setInt(6, this.user.id);

            if (id != 0)
                pstmt.setInt(7, this.id);

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

//#region get/set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Ingredient[] getInventory() {
		return inventory;
	}

	public void setInventory(Ingredient[] inventory) {
		this.inventory = inventory;
	}

	public Place[] getPlaces() {
		return places;
	}

	public void setPlaces(Place[] places) {
		this.places = places;
	}

	public Reservation[] getReservation() {
		return reservation;
	}

	public void setReservation(Reservation[] reservation) {
		this.reservation = reservation;
	}
//#endregion

    @Override
    public int getId() {
        return this.id;
    }
}