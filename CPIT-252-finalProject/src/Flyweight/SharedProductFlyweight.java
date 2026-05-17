/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flyweight;

/**
 *
 * @author noufa
 */public class SharedProductFlyweight implements Flyweight{

    //the shared attributes that will be stored in the flyweight object
    private String category;
    private String specificType;
    private String pricingStrategy;

    
    public SharedProductFlyweight(String category,String specificType,String pricingStrategy) {

        this.category = category;
        this.specificType = specificType;
        this.pricingStrategy = pricingStrategy;
    }
    

    @Override
    public String getCategory() {return category;}

    @Override
    public String getSpecificType() {return specificType; }
    
    @Override
    public String getPricingStrategy() { return pricingStrategy;}
    
    
    
}
