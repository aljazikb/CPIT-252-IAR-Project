/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class ProductPanel extends javax.swing.JFrame {

    private final Runnable onChanged;

    private final Color ACCENT, TEXT_LIGHT, TEXT_MUTED, BG_DARK, CARD_BG;

    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField;

    private static final String[] COLUMNS = {"ID", "Name", "Category", "Type","Quantity", "Unit Price","Pricing Strategy", "Inventory Value"};

    public ProductPanel() {

        ACCENT = new Color(59,130,246);
        TEXT_LIGHT = Color.WHITE;
        TEXT_MUTED = new Color(148,163,184);
        BG_DARK = new Color(15,23,42);
        CARD_BG = new Color(30,41,59);

        onChanged = () -> {};

        setTitle("Products");

        setSize(1400, 850);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(BG_DARK);

        setLayout(new BorderLayout(0, 0));

        buildUI();
    }

    private void buildUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BG_DARK);

        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header
        JPanel header = new JPanel(new BorderLayout(12, 0));

        header.setBackground(BG_DARK);

        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel title = new JLabel("Products");

        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        title.setForeground(TEXT_LIGHT);

        searchField = styledField("Search by name or category...");

        searchField.setPreferredSize(new Dimension(240, 34));

        JButton addBtn = accentButton("+ Add Product");

        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        rightBar.setBackground(BG_DARK);

        rightBar.add(searchField);

        rightBar.add(addBtn);

        header.add(title, BorderLayout.WEST);

        header.add(rightBar, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(COLUMNS, 0) {

            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(tableModel);

        styleTable();

        tableModel.addRow(new Object[]{
            "P001",
            "Wireless Keyboard",
            "Electronics",
            "Standard",
            "45",
            "120",
            "Regular",
            "120"
        });

        tableModel.addRow(new Object[]{
            "P002",
            "Energy Drink",
            "Beverages",
            "Perishable",
            "8",
            "75",
            "Seasonal",
            "60"
        });

        JScrollPane scroll = new JScrollPane(table);

        scroll.setBackground(BG_DARK);

        scroll.getViewport().setBackground(CARD_BG);

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(44, 62, 80)));

        mainPanel.add(scroll, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel actions = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 0));

        actions.setBackground(BG_DARK);

        actions.setBorder(new EmptyBorder(12, 0, 0, 0));

        JButton editBtn = ghostButton("Edit");

        JButton deleteBtn = ghostButton("Delete");

        JButton refreshBtn = ghostButton("Refresh");

        actions.add(editBtn);

        actions.add(deleteBtn);

        actions.add(refreshBtn);

        mainPanel.add(actions, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void styleTable() {

        table.setBackground(CARD_BG);

        table.setForeground(TEXT_LIGHT);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.setRowHeight(38);

        table.setShowGrid(false);

        table.setIntercellSpacing(new Dimension(0, 0));

        table.setSelectionBackground(new Color(30, 58, 100));

        table.setSelectionForeground(TEXT_LIGHT);

        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();

        header.setBackground(new Color(15, 23, 42));

        header.setForeground(TEXT_MUTED);

        header.setFont(new Font("Segoe UI", Font.BOLD, 11));

        header.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0,
                        new Color(44, 62, 80)));

        header.setReorderingAllowed(false);

        int[] widths = {
            90, 160, 110, 100,
            80, 100, 160, 100
        };

        for (int i = 0; i < widths.length; i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(widths[i]);
        }

        table.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable t,
                    Object v,
                    boolean sel,
                    boolean foc,
                    int row,
                    int col) {

                super.getTableCellRendererComponent(
                        t, v, sel, foc, row, col);

                setBackground(
                        sel
                                ? new Color(30, 58, 100)
                                : (row % 2 == 0
                                ? CARD_BG
                                : new Color(26, 39, 58)));

                setForeground(TEXT_LIGHT);

                setFont(new Font("Segoe UI", Font.PLAIN, 13));

                setBorder(new EmptyBorder(0, 12, 0, 0));

                return this;
            }
        });
    }

    private JTextField styledField(String placeholder) {

        JTextField f = new JTextField(placeholder);

        f.setBackground(new Color(30, 41, 59));

        f.setForeground(TEXT_MUTED);

        f.setCaretColor(TEXT_LIGHT);

        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        f.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(55, 65, 81)),
                        new EmptyBorder(5, 10, 5, 10)));

        return f;
    }

    private JButton accentButton(String text) {

        JButton b = new JButton(text);

        b.setBackground(ACCENT);

        b.setForeground(new Color(15, 23, 42));

        b.setFont(new Font("Segoe UI", Font.BOLD, 13));

        b.setBorderPainted(false);

        b.setFocusPainted(false);

        b.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        b.setBorder(new EmptyBorder(8, 18, 8, 18));

        return b;
    }

    private JButton ghostButton(String text) {

        JButton b = new JButton(text);

        b.setBackground(new Color(30, 41, 59));

        b.setForeground(TEXT_LIGHT);

        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        b.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(55, 65, 81)),
                        new EmptyBorder(6, 14, 6, 14)));

        b.setFocusPainted(false);

        b.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        return b;
    }

    private void showMsg(String msg) {

        JOptionPane.showMessageDialog(
                this,
                msg,
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new ProductPanel().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
