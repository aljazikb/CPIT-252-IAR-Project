/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern_cpit2522.Strategy;

/**
 *
 * @author aljaz
 * strategy executor
 */
public class Context {
    
    private DiscountStrategy strategy;

    public Context(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeDiscount(double price) {
        return strategy.applyDiscount(price);
    }

    public String getStrategyName() {
        return strategy.getStrategyName();
    }
    
    
    
}
