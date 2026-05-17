/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;

/**
 *
 * @author aljaz
 */
public class GradientPanel extends javax.swing.JPanel {
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        java.awt.GradientPaint gp = new java.awt.GradientPaint(
            0, 0, new Color(30, 58, 95),      // بداية
            0, height, new Color(41, 128, 185) // نهاية
        );

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}