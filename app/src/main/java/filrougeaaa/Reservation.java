package filrougeaaa;

import java.sql.*;

import filrougeaaa.utils.*;

public class Reservation extends Model {
    protected Date date;
    protected Customer customer;
    protected Manager manager;

    public Reservation(){
        this.date=null;
        this.customer=null;
        this.manager=null;
    }
    public Reservation(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM reservation WHERE id_reservation=" + id);
            if (resultat.next()) {
                this.date = resultat.getDate("date");
                this.customer = new Customer(resultat.getInt("id_customer"));
                this.manager = new Manager(resultat.getInt("id_manager"));
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
            ResultSet resultat = DBManager.execute("SELECT * FROM reservation WHERE id_reservation=" + this.id);
            if (resultat.next()) {
                this.date = resultat.getDate("date");
                this.customer = new Customer(resultat.getInt("id_customer"));
                this.manager = new Manager(resultat.getInt("id_manager"));
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
            ResultSet resultat = DBManager.execute("SELECT * FROM reservation WHERE id_reservation=" + id);
            if (resultat.next()) {
                this.date = resultat.getDate("date");
                this.customer = new Customer(resultat.getInt("id_customer"));
                this.manager = new Manager(resultat.getInt("id_manager"));
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
            sql = "UPDATE reservation " +
                    "SET date=?, nb_customer= ?, id_customer=?, id_manager=? " +
                    "WHERE id_reservation= ?";
        } else {
            sql = "INSERT INTO reservation(date, nb_customer, id_customer, id_manager) " +
                    "VALUES(?,?,?,?)";
        }
        try {
            PreparedStatement stmc = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmc.setDate(1, this.date);
            stmc.setInt(3, this.customer.getId());
            stmc.setInt(4, this.manager.getId());
            if (this.id != 0)
                stmc.setInt(5, this.id);

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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    @Override
    public int getId() {
        return this.id;
    }
    // #endregion

}
