/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern_cpit2522.Strategy;

/**
 *
 * @author aljaz
 */
public class SeasonalDiscount implements DiscountStrategy {

    @Override
    //applies a predefined set discount value 
    public double applyDiscount(double price) {
        return price * 0.8;
    }

    @Override
    public String getStrategyName(){
        return "Seasonal Discount";
    }
}
