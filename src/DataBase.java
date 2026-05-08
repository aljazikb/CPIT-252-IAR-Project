package designpattern_cpit2522;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;

/**
 *
 * @author noufa
 */


public class DataBase{
    
    public static Connection con = null;
    
    public DataBase(){
        
        try{
       
        //connecting to mySQL//
        String ConnectionURL = "jdbc:mysql://localhost:3306";
        con = DriverManager.getConnection(ConnectionURL,"root","root") ;
        Statement st = con.createStatement();
        
        //creating the database//
        st.executeUpdate("CREATE DATABASE CPIT252"); //*************comment once created***************//
        st.executeUpdate("USE CPIT252");
        System.out.println("DB CREATED");
        
        
        //creating the tables//
        String inventory = ("CREATE TABLE IF NOT EXISTS Inventory (InventoryID INT PRIMARY KEY,Name VARCHAR(100),Category VARCHAR(100),UnitPrice DECIMAL(8,2),QuantityInStock INT,InventoryValue DECIMAL(10,2));");
        String manager = ("CREATE TABLE IF NOT EXISTS Manager (ManagerID INT PRIMARY KEY,ManagerName VARCHAR(100),Password VARCHAR(255));");
        
        st.executeUpdate(inventory);
        st.executeUpdate(manager);
        
        System.out.println("TABLES CREATED");
        
        //adding login details for workers in worker analysis//
       
        String insert1 = "INSERT IGNORE INTO Inventory VALUES (1,'Robot','Cars',45.99,20,919.80),(2,'Racing Car','Cars',30.50,15,457.50),(3,'Barbie Doll','Toys',60.00,10,600.00),(4,'Teddy Bear','Toys',25.75,25,643.75);";
        String insert2 = "INSERT INTO manager VALUES (100,'Ali','p@ss1'),(101,'Arwa','p@ss2'),(102,'Sara','pass@3');";
        
        
        st.executeUpdate(insert1);
        st.executeUpdate(insert2);

        
        
        con.close();
        
         }
  
  
        catch (SQLException s){
        //System.out.println("SQL statement is not executed!"); 
        s.printStackTrace();
        s.getErrorCode();
        s.getSQLState();
        }
    
    }
    
    
    
    
    
    
}
