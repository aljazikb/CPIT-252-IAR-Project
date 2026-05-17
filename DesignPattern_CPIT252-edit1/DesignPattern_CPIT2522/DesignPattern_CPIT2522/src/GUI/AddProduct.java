/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

/**
 *
 * @author noufa
 */

import designpattern_cpit2522.Factory.ProductFactoryCreator;
import designpattern_cpit2522.Strategy.*;
import designpattern_cpit2522.login;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AddProduct extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddProduct.class.getName());

    private JTextField nameField, typeField, quantityField, priceField;
    private JComboBox<String> categoryBox;

    private final Color ACCENT = new Color(59, 130, 246);
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color BG_DARK = new Color(15, 23, 42);
    private final Color CARD_BG = new Color(30, 41, 59);
    private final Color FIELD_BG = new Color(26, 39, 58);

    public AddProduct() {

        setTitle("Add Product");
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new GradientPanel());
        setLayout(null);

        buildUI();
    }

    private void buildUI() {

        JPanel card = new JPanel(null);
        card.setBackground(CARD_BG);
        card.setBounds(200, 60, 900, 620);
        card.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2));
        add(card);

        ShadowLabel title = new ShadowLabel("ADD PRODUCT");
        title.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 52));
        title.setBounds(330, 20, 500, 80);
        card.add(title);

        JButton backBtn = accentButton("Back");
        backBtn.setBounds(30, 30, 120, 40);
        backBtn.addActionListener(e -> {
            new Dashboard1().setVisible(true);
            dispose();
        });
        card.add(backBtn);

        Font labelFont = new Font("Tw Cen MT", Font.PLAIN, 28);

        // CATEGORY
        JLabel catLbl = new JLabel("Category:");
        catLbl.setFont(labelFont);
        catLbl.setForeground(TEXT_LIGHT);
        catLbl.setBounds(220, 150, 200, 40);
        card.add(catLbl);

        categoryBox = styledCombo(new String[]{"Toy", "Food", "Electronic"});
        categoryBox.setBounds(380, 150, 350, 40);
        card.add(categoryBox);

        // NAME
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setFont(labelFont);
        nameLbl.setForeground(TEXT_LIGHT);
        nameLbl.setBounds(220, 220, 200, 40);
        card.add(nameLbl);

        nameField = styledField();
        nameField.setBounds(380, 220, 350, 40);
        card.add(nameField);

        // SPECIFIC TYPE
        JLabel typeLbl = new JLabel("Type:");
        typeLbl.setFont(labelFont);
        typeLbl.setForeground(TEXT_LIGHT);
        typeLbl.setBounds(220, 290, 200, 40);
        card.add(typeLbl);

        typeField = styledField();
        typeField.setBounds(380, 290, 350, 40);
        card.add(typeField);

        // QUANTITY
        JLabel qtyLbl = new JLabel("Quantity:");
        qtyLbl.setFont(labelFont);
        qtyLbl.setForeground(TEXT_LIGHT);
        qtyLbl.setBounds(220, 360, 200, 40);
        card.add(qtyLbl);

        quantityField = styledField();
        quantityField.setBounds(380, 360, 350, 40);
        card.add(quantityField);

        // PRICE
        JLabel priceLbl = new JLabel("Unit Price:");
        priceLbl.setFont(labelFont);
        priceLbl.setForeground(TEXT_LIGHT);
        priceLbl.setBounds(220, 430, 200, 40);
        card.add(priceLbl);

        priceField = styledField();
        priceField.setBounds(380, 430, 350, 40);
        card.add(priceField);

        // ADD BUTTON
        JButton addBtn = accentButton("ADD");
        addBtn.setBounds(350, 520, 200, 60);
        addBtn.addActionListener(e -> addProduct());
        card.add(addBtn);
    }

    private JTextField styledField() {
        JTextField f = new JTextField();
        //f.setBackground(FIELD_BG);
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        f.setBorder(BorderFactory.createLineBorder(new Color(55, 65, 81), 2));
        return f;
    }

    private JComboBox<String> styledCombo(String[] items) {
        JComboBox<String> box = new JComboBox<>(items);
        box.setBackground(new Color(180, 180, 180));
        box.setForeground(Color.WHITE);
        box.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        box.setBorder(BorderFactory.createLineBorder(new Color(55, 65, 81), 2));
        return box;
    }

    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(new Color(15, 23, 42));
        b.setFont(new Font("Segoe UI", Font.BOLD, 18));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(new EmptyBorder(10, 20, 10, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void addProduct() {

        String category = (String) categoryBox.getSelectedItem();
        String name = nameField.getText().trim();
        String type = typeField.getText().trim();
        String qtyStr = quantityField.getText().trim();
        String priceStr = priceField.getText().trim();

        //checks if there is a user logged in first
        if (!login.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        //check if any of the necessary fields are emtpy
        if (name.isEmpty() || type.isEmpty() || qtyStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields.");
            return;
        }

        int qty;
        double price;

        try {
            qty = Integer.parseInt(qtyStr);
            price = Double.parseDouble(priceStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be numbers.");
            return;
        }
        //-----------Logic--------
        ProductFactoryCreator factoryCreator = new ProductFactoryCreator();
        
        // Only this moves to the AddFactory method
        factoryCreator.getProduct(category, name, type, price, qty);

        JOptionPane.showMessageDialog(this, "Product Added Successfully!");
        new Dashboard1().setVisible(true);
        dispose();
        
        /*
        //select factroy
        AddFactory factory;

        switch (category) {
            case "Toy":
                factory = new ToyFactory();
                break;
            case "Food":
                factory = new FoodFactory();
                break;
            default:
                factory = new ElectronicFactory();
        }

        //ليش دا
        //no discount --> default
        factory.addProduct(name, type, price, qty);
         */
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AddProduct().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
