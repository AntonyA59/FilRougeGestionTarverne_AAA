package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class Place extends Model {
    protected String name;
    protected int type;
    protected int level;
    protected User user;

    public Place(int id) {
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM place WHERE id_place = "+id) ;
            if(resultat.next()){
				this.name=resultat.getString(2);
				this.type=resultat.getInt(3);
				this.level=resultat.getInt(4);
                this.user = new User(resultat.getInt(id));
                this.id = id ;
            }
        }catch(SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError"+ ex.getErrorCode());
        }
	}
    
    @Override
    public boolean get(int id) {
		try{
			ResultSet resultat= DBManager.execute("SELECT * FROM place where id_place="+id);
			if(resultat.next()){

				this.name=resultat.getString(2);
				this.type=resultat.getInt(3);
				this.level=resultat.getInt(4);
                this.user = new User(resultat.getInt(id));
				return true;
			}
		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
    }
    @Override
    public boolean save() {
		String sql;
        if(this.id != 0)
            sql = "UPDATE place "+
                    "SET name = ?,type = ?, level = ?, id_manager = ?"+
                    "WHERE id_place = ?";
        else
            sql = "INSERT INTO place (name, type, level, id_manager)"+
                        "VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement stmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.name);
            stmt.setInt(2, this.type);
            stmt.setInt(3, this.level);
            stmt.setInt(4, this.user.id);
            if(id != 0)
                stmt.setInt(5, this.id);

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if(this.id == 0 && keys.next()){
                this.id = keys.getInt(1);
                return true;
            }
            else if(this.id != 0)
                return true;
            else
                return false;

        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
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
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    //#endregion

    @Override
    public int getId() {
        return this.id;
    }
}
