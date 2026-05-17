
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author noufa
 */
package GUI;


import Strategy.*;
import cpit.ProductService;
import static cpit.login.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CreateDiscount extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CreateDiscount.class.getName());

    //for the drop down menu options
    private JComboBox<String> productBox;
    private JComboBox<String> discountBox;

    private JTextField percentField;

    private final Color ACCENT = new Color(59, 130, 246);
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color BG_DARK = new Color(15, 23, 42);
    private final Color CARD_BG = new Color(30, 41, 59);

    public CreateDiscount() {

        setTitle("Add Discount");
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

        ShadowLabel title = new ShadowLabel("ADD DISCOUNT");
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

        // PRODUCT DROPDOWN
        JLabel nameLbl = new JLabel("Product Name");
        nameLbl.setFont(labelFont);
        nameLbl.setForeground(TEXT_LIGHT);

        nameLbl.setBounds(200, 150, 250, 40);
        card.add(nameLbl);

        productBox = new JComboBox<>();
        productBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        productBox.setBounds(400, 150, 300, 40);
        card.add(productBox);

        loadProducts();

        // DISCOUNT TYPE DROPDOWN
        JLabel discLbl = new JLabel("Discount");
        discLbl.setFont(labelFont);
        discLbl.setForeground(TEXT_LIGHT);
        discLbl.setBounds(260, 230, 250, 40);
        card.add(discLbl);

        discountBox = new JComboBox<>(new String[]{"Seasonal", "Percentage"});
        discountBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        discountBox.setBounds(400, 230, 300, 40);
        card.add(discountBox);

        // PERCENT FIELD (hidden by default)
        percentField = new JTextField();
        percentField.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        percentField.setBounds(400, 300, 300, 40);
        percentField.setVisible(false);
        card.add(percentField);

        JLabel percentLbl = new JLabel("Percentage (%)");
        percentLbl.setFont(labelFont);
        percentLbl.setForeground(TEXT_LIGHT);

        percentLbl.setBounds(150, 300, 250, 40);
        percentLbl.setVisible(false);
        card.add(percentLbl);

        discountBox.addActionListener(e -> {
            boolean isPercent = discountBox.getSelectedItem().equals("Percentage");
            percentLbl.setVisible(isPercent);
            percentField.setVisible(isPercent);
        });

        // APPLY BUTTON
        JButton applyBtn = accentButton("ADD");
        applyBtn.setBounds(350, 430, 200, 55);

        applyBtn.addActionListener(e -> applyDiscount());
        card.add(applyBtn);
    }

    private void applyDiscount() {

        if (productBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a product.");
            return;
        }

        String productText = (String) productBox.getSelectedItem();
        int inventoryID = Integer.parseInt(productText.split(" - ")[0]);
        String discountType = (String) discountBox.getSelectedItem();
        String percentText = percentField.getText();

        // Validate percentage if not seasonal
        if (!discountType.equals("Seasonal")) {
            try {
                Double.parseDouble(percentText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Enter a valid percentage.");
                return;
            }
        }

        try {
            DiscountService service = new DiscountService();
            DiscountStrategy strategy = service.buildStrategy(discountType, percentText);
            service.applyDiscount(inventoryID, strategy);
            
            JOptionPane.showMessageDialog(this, "Discount Added Successfully");
            new Dashboard1().setVisible(true);
            dispose();
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.");
        }
    }

    
    //loads the products to the object productBox to store the products for the dropdown menu
    private void loadProducts() {

        try {
            ProductService service = new ProductService();
            int managerId = login(); // current user
            ArrayList<String> products = service.getProductsByManager(managerId);
            for (String p : products) {
                productBox.addItem(p);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products.");
        }
    }

    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(new Color(15, 23, 42));
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(new EmptyBorder(10, 20, 10, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
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
        java.awt.EventQueue.invokeLater(() -> new CreateDiscount().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
