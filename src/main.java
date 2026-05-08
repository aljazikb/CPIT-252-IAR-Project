package designpattern_cpit2522;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import designpattern_cpit2522.InventoryDatabase;
import java.sql.Connection;

/**
 *
 * @author aljaz
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
        //DataBase db = new DataBase();
        InventoryDatabase singleton = InventoryDatabase.getConnectionInstance();
        Connection con = singleton.getConnection();

    }

}
