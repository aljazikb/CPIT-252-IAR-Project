package designpattern_cpit2522;



/**
 *
 * @author aljaz
 */
import java.sql.*;

public class InventoryDatabase {
    
    private static InventoryDatabase instance;
    private Connection con;
    
    private InventoryDatabase(){
        try {
            String ConnectionURL = "jdbc:mysql://localhost:3306/CPIT252";
            con = DriverManager.getConnection(ConnectionURL, "root", "root");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
    

    public static InventoryDatabase getConnectionInstance() {
        
        if (instance == null) {
            instance =new InventoryDatabase();
        }
        return instance;
    }
    

    public Connection getConnection() {
        return con; }
    
}