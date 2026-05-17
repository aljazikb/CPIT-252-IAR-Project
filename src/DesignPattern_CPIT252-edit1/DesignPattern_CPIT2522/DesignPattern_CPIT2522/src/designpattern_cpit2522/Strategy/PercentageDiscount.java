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
public class PercentageDiscount implements DiscountStrategy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    //applies the user entered discount to the price
    public double applyDiscount(double price) {
        return price - (price * percent / 100);
    }

    @Override
    public String getStrategyName() {
        return percent + "% Discount";
    }

}
