package filrougeaaa;

import java.sql.*;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class SubCategory extends Model {
    protected String name;
    protected Category category;

    public SubCategory() {
        this.name = "";
        this.category = new Category();
    }

    public SubCategory(int id) {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM subcategory WHERE id_subcategory = "+id) ;
            if(resultat.next()){
                this.name = resultat.getString("name") ;
				this.category = new Category(resultat.getInt("id_category")) ;
                this.id = id ;
        }
        }catch(SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError"+ ex.getErrorCode());
        }
	}

	@Override
	public boolean get() {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM subcategory WHERE id_subcategory = "+this.id);
            if(resultat.next()){
                this.name = resultat.getString("name");
				this.category.get(resultat.getInt("id_category"));
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
	public boolean get(int id) {
		try{
            ResultSet resultat = DBManager.execute("SELECT * FROM subcategory WHERE id_subcategory = "+id);
            if(resultat.next()){
                this.name = resultat.getString("name");
				this.category.get(resultat.getInt("id_category"));
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
        String sql;
        if (this.id != 0) {
            sql = "UPDATE subcategory SET name=?,id_category=? WHERE id_subcategorie = ?";
        } else {
            sql = "INSERT INTO subcategory (name,id_category) VALUES (?,?)";
        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.name);
            pstmt.setInt(2, this.category.getId());
            if (this.id != 0)
                pstmt.setInt(3, this.id);

            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (this.id == 0 && keys.next()) {
                this.id = keys.getInt(1);
                return true;
            } else if (this.id != 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError" + ex.getErrorCode());
            return false;
        }
    }

    // #region get/set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getId() {
        return this.id;
    }
    // #endregion get/set
}