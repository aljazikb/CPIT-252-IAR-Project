/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package designpattern_cpit2522;

/**
 *
 * @author noufa
 */
import designpattern_cpit2522.DB_Singleton.*;
import java.sql.*;

public class login {

    private static int loggedInManagerID = -1;

    public static boolean authenticate(int managerID, String password) {
        try {

            //singleton DB connection
            DBconnectionSingleton db = DBconnectionSingleton.getConnectionInstance();
            Connection con = db.getConnection();

            //checks for the ID and password in the DB entered from the manager via the GUI 
            String query = "SELECT ManagerID FROM Manager WHERE ManagerID = ? AND Password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, managerID);
            ps.setString(2, password.trim());

            ResultSet rs = ps.executeQuery();

            //stores the current manager ID
            if (rs.next()) {
                loggedInManagerID = managerID;
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int login() {
        return loggedInManagerID;
    }

    public static void logout() {
        loggedInManagerID = -1;
    }

    public static boolean isLoggedIn() {
        return loggedInManagerID != -1;
    }
}
