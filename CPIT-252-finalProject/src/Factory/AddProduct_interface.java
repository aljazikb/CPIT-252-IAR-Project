/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;


import Strategy.*;

/**
 *
 * @author noufa
 */
public interface AddProduct_interface {

    void addProduct(String name, String specificType, double unitPrice, int quantityInStock);

}
