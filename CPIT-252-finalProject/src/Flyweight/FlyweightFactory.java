/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flyweight;

import java.util.*;

public class FlyweightFactory {

    //hash map to store and reuse shared flyweight objects
    private static HashMap<String, SharedProductFlyweight> sharedObjects = new HashMap<>();

    //checks if the object exists --> reuse || else --> create new flyweight and store it in the hash map
    public static Flyweight getSharedData(String category, String specificType, String pricingStrategy) {

        //create a unique key for each combination
        String key = category + "-" + specificType + "-" + pricingStrategy;

        //check if the flyweight object does not exists
        if (!sharedObjects.containsKey(key)) {

            //create new shared flyweight object
            SharedProductFlyweight data = new SharedProductFlyweight(category, specificType, pricingStrategy);

            //store the object inside the hash map
            sharedObjects.put(key, data);

            //print message --> new flyweight is created
            System.out.println("NEW Flyweight Created: " + key);

            //if the object deos exist in the hash map --> reuse
        } else {

            //print message
            System.out.println("Reusing Existing Flyweight: " + key);
        }

        //return the shared flyweight object
        return sharedObjects.get(key);
    }
}