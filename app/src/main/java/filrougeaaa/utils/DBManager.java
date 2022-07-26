package filrougeaaa.utils;
import java.sql.*;

public class DBManager {
    public static Connection conn = null;
    private static String user = "aaa";
    private static String password = "jP59%4rSAs";
    private static String server = "51.68.227.19";
    private static String database = "aaa_tavern_manager";

    public static void init(){
        try {
            DBManager.conn =
            DriverManager.getConnection("jdbc:mysql://"+DBManager.server+"/"
                                            +DBManager.database, user, password);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static void close(){
        try{
            DBManager.conn.close();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static ResultSet execute(String sql){
        ResultSet test = null;
        try {
            Statement stmt = DBManager.conn.createStatement();
            test = stmt.executeQuery(sql);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return test;
    }
    public static int executeUpdate(String sql){
        int test = -1;
        try {
            Statement stmt = DBManager.conn.createStatement();
            test = stmt.executeUpdate(sql);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return test;
    }

    public static void setAutoCommit(boolean autocommit){
        try{
            conn.setAutoCommit(autocommit);
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static Savepoint setSavePoint(){
        try{
            return conn.setSavepoint();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public static void rollback(Savepoint save){
        try{
            conn.rollback(save);
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}