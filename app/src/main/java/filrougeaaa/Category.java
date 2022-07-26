package filrougeaaa;
import java.sql.* ;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;
public class Category extends Model{
    protected String name;

	public Category() {
        this.name = "" ;
    }

    public Category(int id) {
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM category WHERE id_category = "+id) ;
            if(resultat.next()){
                this.name = resultat.getString("name") ;
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
            ResultSet resultat = DBManager.execute("SELECT * FROM category WHERE id_category = "+id);
            if(resultat.next()){
                this.name = resultat.getString("name");
                this.id = id;
                return true;
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
	}

	@Override
	public boolean save() {
		String sql ;
        if(this.id != 0){
            sql = "UPDATE category SET name=? WHERE id_category = ?" ;
        }else{
            sql = "INSERT INTO category (name) VALUES (?)" ;
        }
        try {
            PreparedStatement pstmt =  DBManager.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pstmt.setString(1, this.name);
            if(this.id != 0)
                pstmt.setInt(2, this.id);
            
            pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();
			if(this.id == 0 && keys.next()){
				this.id = keys.getInt(1);
				return true;
			}
			else if(this.id != 0)
				return true;
			else
				return false;
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError"+ ex.getErrorCode());
            return false ;
        }
	}
//#region Get/Set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId(){
		return this.id ;
	}

	public void setId(int id){
		this.id = id ;
	}
//#endregion
}