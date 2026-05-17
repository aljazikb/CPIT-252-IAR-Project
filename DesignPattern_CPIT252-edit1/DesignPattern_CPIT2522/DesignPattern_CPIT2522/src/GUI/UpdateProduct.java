/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import designpattern_cpit2522.login;
import designpattern_cpit2522.DB_Singleton.DBconnectionSingleton;
import designpattern_cpit2522.ProductService;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class UpdateProduct extends javax.swing.JFrame {

    private JComboBox<String> productBox;
    private JComboBox<String> updateFieldBox;
    private JTextField changeToField;

    private final Color ACCENT = new Color(59, 130, 246);
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color BG_DARK = new Color(15, 23, 42);
    private final Color CARD_BG = new Color(30, 41, 59);

    public UpdateProduct() {

        setTitle("Update Product");
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

        ShadowLabel title = new ShadowLabel("UPDATE PRODUCT");
        title.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 52));
        title.setBounds(300, 20, 500, 80);
        card.add(title);

        JButton backBtn = accentButton("Back");
        backBtn.setBounds(30, 30, 120, 40);
        backBtn.addActionListener(e -> {
            new Dashboard1().setVisible(true);
            dispose();
        });

        card.add(backBtn);

        Font labelFont = new Font("Tw Cen MT", Font.PLAIN, 28);

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

        JLabel updateLbl = new JLabel("Update");
        updateLbl.setFont(labelFont);
        updateLbl.setForeground(TEXT_LIGHT);
        updateLbl.setBounds(260, 230, 200, 40);
        card.add(updateLbl);

        updateFieldBox = new JComboBox<>(
                new String[]{"Category", "SpecificType", "QuantityInStock", "UnitPrice"}
        );
        updateFieldBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        updateFieldBox.setBounds(400, 230, 300, 40);
        card.add(updateFieldBox);

        JLabel changeLbl = new JLabel("Change To");
        changeLbl.setFont(labelFont);
        changeLbl.setForeground(TEXT_LIGHT);
        changeLbl.setBounds(240, 310, 250, 40);
        card.add(changeLbl);

        changeToField = new JTextField();
        changeToField.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        changeToField.setBounds(400, 310, 300, 40);
        card.add(changeToField);

        JButton applyBtn = accentButton("APPLY");
        applyBtn.setBounds(350, 430, 200, 55);

        applyBtn.addActionListener(e -> applyUpdate());
        card.add(applyBtn);
    }

    // -----------------------------------------------------------
    // LOAD PRODUCTS FOR DROPDOWN
    // -----------------------------------------------------------
    private void loadProducts() {
        try {
            DBconnectionSingleton db = DBconnectionSingleton.getConnectionInstance();
            Connection con = db.getConnection();

            String sql = "SELECT InventoryID, Name FROM Inventory WHERE ManagerID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, login.login());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("InventoryID");
                String name = rs.getString("Name");
                productBox.addItem(id + " - " + name);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load products.");
        }
    }

    // -----------------------------------------------------------
    // APPLY UPDATE LOGIC — TYPE SAFE
    // -----------------------------------------------------------
    private void applyUpdate() {

        if (productBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a product.");
            return;
        }

        String productText = (String) productBox.getSelectedItem();
        int inventoryID = Integer.parseInt(productText.split(" - ")[0]);

        String field = (String) updateFieldBox.getSelectedItem();
        String newValue = changeToField.getText().trim();

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a new value.");
            return;
        }

        Object castValue;

        switch (field) {

            case "QuantityInStock":

                try {
                    castValue = Integer.parseInt(newValue);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number.");
                    return;
                }

                break;

            case "UnitPrice":

                try {
                    castValue = Double.parseDouble(newValue);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Unit Price must be a number.");
                    return;
                }

                break;

            default:
                castValue = newValue;
        }

        try {
            //----------------------------------------------
            ProductService service = new ProductService();
            service.update(inventoryID, field, castValue);
            //----------------------------------------------
            
            JOptionPane.showMessageDialog(this, "Updated Successfully!");
            new Dashboard1().setVisible(true);
            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Update failed.");
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------
    // BUTTON STYLE (BLUE THEME)
    // -----------------------------------------------------------
    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(new Color(15, 23, 42));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(new EmptyBorder(8, 18, 8, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdateProduct.class.getName());

    /**
     * Creates new form UpdateProduct
     */
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
        java.awt.EventQueue.invokeLater(() -> new UpdateProduct().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
