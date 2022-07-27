package filrougeaaa;
import java.sql.*;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;
public class User extends Model{
    private String email;
    private String password;
    protected String nickName;
    protected Manager[] partie; 

	public User(){
		email = "" ;
		password = "" ;
		nickName = "" ;
	}
	public User(String email, String password, String nickName){
		this.email=email;
		this.password=password;
		this.nickName=nickName;
	}
	// avoir si utile
	public User(int id){
		try{
			ResultSet resultat= DBManager.execute("SELECT * FROM user where id_user="+id);
			if(resultat.next()){

				this.email=resultat.getString("email");
				this.password=resultat.getString("password");
				this.nickName=resultat.getString("nickName");
				this.id=id;
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
			ResultSet resultat= DBManager.execute("SELECT * FROM user where id_user="+id);
			if(resultat.next()){

				this.email=resultat.getString("email");
				this.password=resultat.getString("password");
				this.nickName=resultat.getString("nickName");
				this.id=id;
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
	public boolean get(int id) {

		try{
			ResultSet resultat= DBManager.execute("SELECT * FROM user where id_user="+id);
			if(resultat.next()){

				this.email=resultat.getString("email");
				this.password=resultat.getString("password");
				this.nickName=resultat.getString("nickName");
				this.id=id;
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
            sql = "UPDATE user "+
                    "SET email = ?,password = ?, nickName = ?"+
                    "WHERE id_user = ?";
        else
            sql = "INSERT INTO user (email, password, nickName)"+
                        "VALUES(?, ?, ?)";
        try{
            PreparedStatement stmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.email);
            stmt.setString(2, this.password);
            stmt.setString(3, this.nickName);
            if(id != 0)
                stmt.setInt(4, this.id);

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
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Manager[] getPartie() {
		return partie;
	}
	
	public void setPartie(Manager[] partie) {
		this.partie = partie;
	}
	
//#endregion
	@Override
	public int getId() {
		return this.id;
	}
	
} 