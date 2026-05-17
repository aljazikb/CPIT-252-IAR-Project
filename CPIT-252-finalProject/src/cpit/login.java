package cpit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Singleton.*;
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
