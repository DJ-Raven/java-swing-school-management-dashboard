package com.raven.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPopup extends JPanel {

    public PanelPopup() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.setColor(new Color(250, 250, 250));
        g2.fillRect(8, 0, getSize().width - 8, getSize().height);
        int x[] = {0, 8, 8};
        int y[] = {20, 13, 27};
        g2.fillPolygon(x, y, x.length);
        super.paintComponent(grphcs);
    }
}
