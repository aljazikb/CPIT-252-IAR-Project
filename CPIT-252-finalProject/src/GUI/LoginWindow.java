/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;


import cpit.login;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginWindow extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginWindow.class.getName());


    private JTextField idField;
    private JPasswordField passwordField;

    // Blue theme colors matching the application design
    private final Color ACCENT = new Color(59,130,246);
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color CARD_BG = new Color(30,41,59);
    private final Color FIELD_BG = new Color(26,39,58);

    public LoginWindow() {
        setTitle("Login");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new GradientPanel());
        setLayout(null);

        buildUI();
    }

  
    private void buildUI() {

        // Main card panel for the login form
        JPanel card = new JPanel(null);
        card.setBackground(CARD_BG);
        card.setBounds(150, 80, 600, 440);
        card.setBorder(BorderFactory.createLineBorder(new Color(44,62,80), 2));
        add(card);

        // Title using custom ShadowLabel component
        ShadowLabel title = new ShadowLabel("LOGIN");
        title.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48));
        title.setBounds(260, 30, 400, 80);
        card.add(title);

        // Back button to return to Dashboard
        JButton backBtn = accentButton("Back");
        backBtn.setBounds(20, 20, 120, 40);
        
        backBtn.addActionListener(e -> {
            new Dashboard1().setVisible(true);
            dispose();
        });
        card.add(backBtn);

        Font labelFont = new Font("Tw Cen MT", Font.PLAIN, 28);

        // Manager ID input section
        JLabel idLbl = new JLabel("Manager ID:");
        idLbl.setForeground(TEXT_LIGHT);
        idLbl.setFont(labelFont);
        idLbl.setBounds(130, 150, 200, 40);
        card.add(idLbl);

        idField = styledField();
        idField.setBounds(300, 150, 200, 40);
        card.add(idField);

        // Password input section
        JLabel passLbl = new JLabel("Password:");
        passLbl.setForeground(TEXT_LIGHT);
        passLbl.setFont(labelFont);
        passLbl.setBounds(150, 230, 200, 40);
        card.add(passLbl);

        passwordField = new JPasswordField();
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(55,65,81), 2));
        passwordField.setBounds(300, 230, 200, 40);
        card.add(passwordField);

        // Login button
        JButton loginBtn = accentButton("LOGIN");
        loginBtn.setBounds(230, 330, 140, 55);
        loginBtn.addActionListener(e -> attemptLogin());
        card.add(loginBtn);
    }

    /**
     * Handles the login authentication process
     * Validates input fields and authenticates against the database
     * Shows appropriate error messages for various failure cases
     * Returns to Dashboard on successful login
     */
    private void attemptLogin() {

        String idText = idField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        // Validation: Check for empty fields
        if (idText.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Manager ID and Password cannot be empty.");
            return;
        }

        // Validation: Ensure Manager ID is numeric
        int managerID;
        try {
            managerID = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Manager ID must be a number.");
            return;
        }

        // Authenticate user using the login class
        boolean success = login.authenticate(managerID, pass);

        if (success) {
            // Success: Show confirmation and return to Dashboard
            JOptionPane.showMessageDialog(this, "Login Successful!");
            
            // Return to Dashboard - the Dashboard will automatically 
            // detect the login state and update its button text
            new Dashboard1().setVisible(true);
            dispose();
        } else {
            // Failed: Show error message
            JOptionPane.showMessageDialog(this, 
                "No user found or incorrect credentials.");
        }
    }

    /**
     * Creates a styled text field matching the application theme
     * @return JTextField with blue theme styling
     */
    private JTextField styledField() {
        JTextField f = new JTextField();
        f.setBackground(Color.WHITE);
        f.setForeground(Color.BLACK);
        f.setCaretColor(Color.BLACK);
        f.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        f.setBorder(BorderFactory.createLineBorder(new Color(55,65,81), 2));
        return f;
    }

    /**
     * Creates a styled button with blue accent color and hover effects
     * @param text Button text
     * @return JButton with application styling
     */
    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(new Color(15,23,42));
        b.setFont(new Font("Segoe UI", Font.BOLD, 18));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(new EmptyBorder(10,20,10,20));
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
        java.awt.EventQueue.invokeLater(() -> new LoginWindow().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
