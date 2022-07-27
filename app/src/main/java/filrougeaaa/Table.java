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

    public Table(int id){
        try{
            ResultSet resultat= DBManager.execute("SELECT * FROM `table` WHERE id_table="+id);
            if(resultat.next()){
                this.numberPlace=resultat.getInt("number_place");
                this.hygiene=resultat.getInt("hygiene");
                this.posX=resultat.getInt("pos_x");
                this.posY=resultat.getInt("pos_y");
                //this.place=new Place(resultat.getInt("id_place"));
            }
        }catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    @Override
    public boolean get(int id) {
        try{
            ResultSet resultat= DBManager.execute("SELECT * FROM `table` WHERE id_table="+id);
            if(resultat.next()){
                this.numberPlace=resultat.getInt("number_place");
                this.hygiene=resultat.getInt("hygiene");
                this.posX=resultat.getInt("pos_x");
                this.posY=resultat.getInt("pos_y");
                //this.place=new Place(resultat.getInt("id_place"));
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
            sql="UPDATE `table`"+
            "SET number_place= ?, hygiene= ?, pos_x=?, pos_y=?"+
            "WHERE id_table= ?";
        }
        else{
            sql="INSERT INTO `table`(number_place,hygiene,pos_x,pos_y)"+
            "VALUES(?,?,?,?)";
        }
        try{
            PreparedStatement stmc=DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmc.setInt(1, this.numberPlace);
            stmc.setFloat(2, this.posX);
            stmc.setFloat(3, this.posY);
            stmc.setInt(4,this.place.id);
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }

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
    @Override
    public int getId() {
        return this.id;
    }
    
}
