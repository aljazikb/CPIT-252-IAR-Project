/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package designpattern_cpit2522.Flyweight;

/**
 *
 * @author noufa
 */
public class SharedProductFlyweight {

    //the shared attributes that will be stored in the flyweight object
    private String category;
    private String specificType;
    private String pricingStrategy;

    
    public SharedProductFlyweight(String category,String specificType,String pricingStrategy) {

        this.category = category;
        this.specificType = specificType;
        this.pricingStrategy = pricingStrategy;
    }
    

    public String getCategory() {return category;}

    public String getSpecificType() {return specificType; }

    public String getPricingStrategy() { return pricingStrategy;}
    
    
    
}
