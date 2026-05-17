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
public class NoDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double price) {
        return price;
    }

    @Override
    public String getName() {
        return "No Discount";
    }
}
