package designpattern_cpit2522.DB_Singleton;

/**
 *
 * @author aljaz
 */
import java.sql.*;

public class DBconnectionSingleton {

    private static DBconnectionSingleton instance;
    private Connection con;

    private DBconnectionSingleton() {

        try {
            //getting a connection to the DB
            String ConnectionURL = "jdbc:mysql://localhost:3307/CPIT252";
            con = DriverManager.getConnection(ConnectionURL, "root", "Ash&Loki2022");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Singleton Pattern Application 
    public static DBconnectionSingleton getConnectionInstance() {

        if (instance == null) {
            instance = new DBconnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

}
