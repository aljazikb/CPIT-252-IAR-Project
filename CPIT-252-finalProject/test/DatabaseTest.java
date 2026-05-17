

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noufa
 */
import Singleton.DataBase;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
public class DatabaseTest {
   
    // tests if the DB runs correctly --> no exceptions thrown
    @Test
    public void testIfConstructorRuns() {
        try {
            new DataBase();
        } catch (Exception e) {
            fail("threw an exception");
        }
    }

    // tests if the static DB connection is initialized and not null after creation
    @Test
    public void testStaticConnection() {
        new DataBase();
        assertNotNull(DataBase.con);
    }

    // tests if creating multiple DB objects does not throw exceptions
    @Test
    public void testMultipleCreations() {
        try {
            new DataBase();
            new DataBase();
        } catch (Exception e) {
            fail("multiple initializations threw an exception");
        }
    }
}
   



