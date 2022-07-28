package filrougeaaa;

import java.sql.*;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Table extends Model {
    protected int numberPlace;
    protected float hygiene;
    protected float posX;
    protected float posY;
    protected Place place;

    public Table() {
        this.numberPlace = 0;
        this.hygiene = 0;
        this.posX = 0;
        this.posY = 0;
        this.place = new Place();
    }

    public Table(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM `table` WHERE id_table=" + id);
            if (resultat.next()) {
                this.numberPlace = resultat.getInt("number_place");
                this.hygiene = resultat.getInt("hygiene");
                this.posX = resultat.getInt("pos_x");
                this.posY = resultat.getInt("pos_y");
                this.place = new Place(resultat.getInt("id_place"));
                this.id = id;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    @Override
    public boolean get() {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM `table` WHERE id_table=" + this.id);
            if (resultat.next()) {
                this.numberPlace = resultat.getInt("number_place");
                this.hygiene = resultat.getInt("hygiene");
                this.posX = resultat.getInt("pos_x");
                this.posY = resultat.getInt("pos_y");
                this.place = new Place(resultat.getInt("id_place"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return false;
    }

    @Override
    public boolean get(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM `table` WHERE id_table=" + id);
            if (resultat.next()) {
                this.numberPlace = resultat.getInt("number_place");
                this.hygiene = resultat.getInt("hygiene");
                this.posX = resultat.getInt("pos_x");
                this.posY = resultat.getInt("pos_y");
                this.place = new Place(resultat.getInt("id_place"));
                this.id = id;
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return false;
    }

    @Override
    public boolean save() {
        String sql;
        if (this.id != 0) {
            sql = "UPDATE `table` " +
                    "SET number_place= ?, hygiene= ?, pos_x=?, pos_y=?, id_place = ? " +
                    "WHERE id_table= ?";
        } else {
            sql = "INSERT INTO `table`(number_place,hygiene,pos_x,pos_y,id_place) " +
                    "VALUES(?,?,?,?,?)";
        }
        try {
            PreparedStatement stmc = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmc.setInt(1, this.numberPlace);
            stmc.setFloat(2, this.hygiene);
            stmc.setFloat(3, this.posX);
            stmc.setFloat(4, this.posY);
            stmc.setInt(5, this.place.getId());
            if (this.id != 0)
                stmc.setInt(6, this.id);

            stmc.executeUpdate();
            ResultSet keys = stmc.getGeneratedKeys();
            if (this.id == 0 && keys.next()) {
                this.id = keys.getInt(1);
                return true;
            } else if (this.id != 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    // #region get/set
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

    @Override
    public int getId() {
        return this.id;
    }
    // #endregion

    public boolean TableOccupied(){
        int nbCustomer = 0 ;
        try{
			ResultSet resultat = DBManager.execute("SELECT COUNT(*) FROM customer as c"
            +" INNER JOIN `table` as t"
            +" ON t.id_table = c.id_table"
            +" INNER JOIN place as p"
            +" ON t.id_place = p.id_place"
            +" WHERE c.id_table = "+this.id+" ;") ;
			if(resultat.next()){
                nbCustomer = resultat.getInt("0") ;
                if(nbCustomer > 0){
                    return true ;
                }else{
                    return false ;
                }
			}else{
                return false ;
            }
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
            return false ;
		}
    }
    public boolean TableIsReserved(){
        int nbReservation = 0 ;
        try{
			ResultSet resultat = DBManager.execute("SELECT COUNT(*) FROM reservation as r"
            +" INNER JOIN customer as c"
            +" ON c.id_customer = r.id_customer"
            +" WHERE c.id_table = "+this.id) ;
            if(resultat.next()){
                nbReservation = resultat.getInt("0") ;
                if(nbReservation > 0){
                    return true ;
                }else{
                    return false ;
                }
			}else{
                return false ;
            }
		}catch(SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError"+ ex.getErrorCode());
            return false ;
		}
    }
}
