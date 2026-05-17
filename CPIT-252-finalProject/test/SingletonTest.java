/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noufa
 */
import Singleton.DBconnectionSingleton;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class SingletonTest {
 
    @Test
    public void testSingletonInstance() {
        DBconnectionSingleton instance = DBconnectionSingleton.getConnectionInstance();
        assertNotNull(instance);
    }

    @Test
    public void testSameInstance() {
        DBconnectionSingleton instance1 = DBconnectionSingleton.getConnectionInstance();
        DBconnectionSingleton instance2 = DBconnectionSingleton.getConnectionInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testConnection() {
        DBconnectionSingleton instance = DBconnectionSingleton.getConnectionInstance();
        assertNotNull(instance.getConnection());
    }
}
   

