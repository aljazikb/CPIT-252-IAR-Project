/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

public class ShadowLabel extends JLabel {
    private Color shadowColor = new Color(0, 0, 0, 120); // ظل أسود نصف شفاف
    private int shadowOffsetX = 3;
    private int shadowOffsetY = 3;

    public ShadowLabel(String text) {
        super(text);
        setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 56));
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    @Override
    public  void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

     
        g2.setColor(shadowColor);
        g2.setFont(getFont());
        g2.drawString(getText(), shadowOffsetX, getHeight()/2 + shadowOffsetY);

       
        g2.setColor(getForeground());
        g2.drawString(getText(), 0, getHeight()/2);

        g2.dispose();
    }
}
