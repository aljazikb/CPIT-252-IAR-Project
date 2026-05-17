package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.sql.*;
import designpattern_cpit2522.DB_Singleton.DBconnectionSingleton;
import designpattern_cpit2522.login;

public class viewInventory extends javax.swing.JFrame {

    /**
     * viewInventory ------------- Displays ALL products for the CURRENT
     * logged‑in manager. (Uses your blue theme + modern table + clean layout)
     */
    private JTable table;
    private DefaultTableModel tableModel;

    // Colors (your theme)
    private final Color ACCENT = new Color(59, 130, 246);
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color TEXT_MUTED = new Color(148, 163, 184);
    private final Color BG_DARK = new Color(15, 23, 42);
    private final Color CARD_BG = new Color(30, 41, 59);

    private static final String[] COLUMNS = {
        "ID", "Name", "Category", "Type",
        "Quantity", "Unit Price",
        "Pricing Strategy", "Inventory Value"
    };

    public viewInventory() {

        setTitle("Products");
        setSize(1400, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        buildUI();
        loadInventoryFromDB();   // <-- REAL DATA LOADED HERE
    }

    // -------------------------------------------------------------------
    // BUILD INTERFACE
    // -------------------------------------------------------------------
    private void buildUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BG_DARK);
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        // ================================================================
        // HEADER BAR
        // ================================================================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_DARK);

        JLabel title = new JLabel("Products");
        title.setForeground(TEXT_LIGHT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        // LEFT: BACK BUTTON
        JButton backBtn = accentButton("Back");
        backBtn.addActionListener(e -> {
            new Dashboard1().setVisible(true);
            dispose();
        });

        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtons.setBackground(BG_DARK);
        leftButtons.add(backBtn);

        // RIGHT: ADD PRODUCT BUTTON
        JButton addBtn = accentButton("+ Add Product");
        addBtn.addActionListener(e -> {
            new AddProduct().setVisible(true);
            dispose();
        });

        addBtn.addActionListener(e -> {
            new AddProduct().setVisible(true);
            dispose();
        });

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtons.setBackground(BG_DARK);
        rightButtons.add(addBtn);

        header.add(leftButtons, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        header.add(rightButtons, BorderLayout.EAST);

        main.add(header, BorderLayout.NORTH);

        // ================================================================
        // TABLE
        // ================================================================
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(tableModel);
        styleTable();

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(CARD_BG);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80)));

        main.add(scroll, BorderLayout.CENTER);

        add(main);
    }

    // -------------------------------------------------------------------
    // STYLE TABLE
    // -------------------------------------------------------------------
    private void styleTable() {

        table.setBackground(CARD_BG);
        table.setForeground(TEXT_LIGHT);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(30, 58, 100));
        table.setSelectionForeground(TEXT_LIGHT);

        JTableHeader h = table.getTableHeader();
        h.setBackground(new Color(15, 23, 42));
        h.setForeground(TEXT_MUTED);
        h.setFont(new Font("Segoe UI", Font.BOLD, 12));
        h.setPreferredSize(new Dimension(h.getWidth(), 35));
        h.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 62, 80)));

        // Zebra rows
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable tbl, Object val, boolean selected, boolean focus,
                    int row, int col) {

                super.getTableCellRendererComponent(tbl, val, selected, focus, row, col);

                if (selected) {
                    setBackground(new Color(30, 58, 100));
                } else {
                    setBackground(row % 2 == 0 ? CARD_BG : new Color(26, 39, 58));
                }

                setForeground(TEXT_LIGHT);
                setBorder(new EmptyBorder(0, 12, 0, 0));
                return this;
            }
        });
    }

    // -------------------------------------------------------------------
    // LOAD ACTUAL DB DATA BASED ON LOGGED-IN USER
    // -------------------------------------------------------------------
    private void loadInventoryFromDB() {

        try {
            DBconnectionSingleton db = DBconnectionSingleton.getConnectionInstance();
            Connection con = db.getConnection();

            String sql = "SELECT * FROM Inventory WHERE ManagerID = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, login.login());  // <-- CURRENT USER ID

            ResultSet rs = ps.executeQuery();
            tableModel.setRowCount(0);    // clear old rows

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("InventoryID"),
                    rs.getString("Name"),
                    rs.getString("Category"),
                    rs.getString("SpecificType"),
                    rs.getInt("QuantityInStock"),
                    rs.getDouble("UnitPrice"),
                    rs.getString("PricingStrategy"),
                    rs.getDouble("InventoryValue")
                };
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this, "Error loading inventory from database.");
        }
    }

    // -------------------------------------------------------------------
    // BUTTON STYLE
    // -------------------------------------------------------------------
    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(new Color(15, 23, 42));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(8, 18, 8, 18));
        return b;
    }

    // -------------------------------------------------------------------
    // MAIN (for isolated testing)
    // -------------------------------------------------------------------
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new viewInventory().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
