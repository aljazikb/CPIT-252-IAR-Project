/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import java.sql.*;

/**
 *
 * @author noufa
 */
public class DataBase {

    public static Connection con = null;

    public DataBase() {
        //public static void main (String []args){

        try {

            //connecting to mySQL
            String ConnectionURL = "jdbc:mysql://localhost:3307";
            con = DriverManager.getConnection(ConnectionURL, "root","Ash&Loki2022");
            Statement st = con.createStatement();

            //creating the database
            //st.executeUpdate("CREATE DATABASE CPIT252");    //*************comment once created***************//
            st.executeUpdate("USE CPIT252");
            System.out.println("DB CREATED");

            //creating the table
            String inventory = "CREATE TABLE IF NOT EXISTS Inventory (ManagerID INT,InventoryID INT PRIMARY KEY,Name VARCHAR(100), Category VARCHAR(100), SpecificType VARCHAR(100),UnitPrice DECIMAL(8,2), QuantityInStock INT,InventoryValue DECIMAL(10,2),PricingStrategy VARCHAR(100), FOREIGN KEY (ManagerID) REFERENCES Manager(ManagerID) ) ";
            String manager = "CREATE TABLE IF NOT EXISTS Manager (ManagerID INT PRIMARY KEY,ManagerName VARCHAR(100),Password VARCHAR(255));";

            st.executeUpdate(manager);
            st.executeUpdate(inventory);

            System.out.println("TABLES CREATED");

            //filling the tables with both manager and inventory values
            String insert1 = "INSERT IGNORE INTO Inventory VALUES (100,1,'Robot','Electronics','RobotToy',45.99,20,919.80,'No Discount'),(101,2,'Racing Car','Toys','CarToy',30.50,15,457.50,'No Discount'),(102,3,'Burger','Food','FastFood',18.00,30,540.00,'No Discount'),(100,4,'Laptop','Electronics','PortableDevice',2500.00,5,12500.00,'No Discount');";
            String insert2 = "INSERT IGNORE INTO manager VALUES (100,'Ali','p@ss1'),(101,'Arwa','p@ss2'),(102,'Sara','pass@3');";

            st.executeUpdate(insert2);
            st.executeUpdate(insert1);

            con.close();

        } catch (SQLException s) {
            //System.out.println("SQL statement is not executed!"); 
            s.printStackTrace();
            s.getErrorCode();
            s.getSQLState();
        }

    }

}