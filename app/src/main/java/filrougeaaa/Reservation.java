package filrougeaaa;

import java.sql.*;

import filrougeaaa.utils.*;

public class Reservation extends Model{
    protected Date date;
    protected int nb_customer;
    public Reservation(int id){
        try{
            ResultSet resultat= DBManager.execute("SELECT * FROM reservation WHERE id_reservation="+id);
            if(resultat.next()){
                this.date=resultat.getDate("date");
                this.nb_customer=resultat.getInt("nb_customer");
            }
        }catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }


    @Override
    public boolean get() {
        try{
            ResultSet resultat= DBManager.execute("SELECT * FROM reservation WHERE id_reservation="+id);
            if(resultat.next()){
                this.date=resultat.getDate("date");
                this.nb_customer=resultat.getInt("nb_customer");
                return true;
            }
        }catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return false;
    }
    
    @Override
    public boolean get(int id) {
        try{
            ResultSet resultat= DBManager.execute("SELECT * FROM reservation WHERE id_reservation="+id);
            if(resultat.next()){
                this.date=resultat.getDate("date");
                this.nb_customer=resultat.getInt("nb_customer");
                return true;
            }
        }catch(SQLException ex){
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
        if(this.id!=0){
            sql="UPDATE reservation"+
            "SET date=?, nb_customer= ? "+
            "WHERE id_reservation= ?";
        }
        else{
            sql="INSERT INTO reservation(date,nb_reservation)"+
            "VALUES(?,?)";
        }
        try{
            PreparedStatement stmc=DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmc.setDate(2, this.date);
            stmc.setInt(1, this.nb_customer);
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }

        return false;
    }

    //#region get/set
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getNb_customer() {
        return nb_customer;
    }
    public void setNb_customer(int nb_customer) {
        this.nb_customer = nb_customer;
    }
   //#endregion
    
}
