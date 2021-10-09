package com.raven.swing.icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class IconFontSwing {

    private static List<IconFont> fonts = new ArrayList<>();

    public static synchronized void register(IconFont iconFont) {
        if (IconFontSwing.fonts.contains(iconFont) == false) {
            IconFontSwing.fonts.add(iconFont);
        }
    }

    public static synchronized final Font buildFont(String fontFamily) {
        try {
            for (IconFont iconFont : IconFontSwing.fonts) {
                if (iconFont.getFontFamily().equals(fontFamily)) {
                    return Font.createFont(Font.TRUETYPE_FONT, iconFont.getFontInputStream());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE,
                    "Font load failure", ex);
        }

        Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE,
                "Font not found: " + fontFamily);
        throw new IllegalArgumentException("Font not found: " + fontFamily);
    }

    private IconFontSwing() {
    }

    public static Image buildImage(IconCode iconCode, float size) {
        return buildImage(iconCode, size, Color.BLACK);
    }

    public static Image buildImage(IconCode iconCode, float size, Color color) {
        Font font = buildFont(iconCode, size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color);
    }

    public static Icon buildIcon(IconCode iconCode, float size) {
        return buildIcon(iconCode, size, Color.BLACK);
    }

    public static Icon buildIcon(IconCode iconCode, float size, Color color) {
        return new ImageIcon(buildImage(iconCode, size, color));
    }

    public static Image buildImage(IconCode iconCode, float size, Color color, Color color1) {
        Font font = buildFont(iconCode, size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color, color1);
    }

    public static Icon buildIcon(IconCode iconCode, float size, Color color, Color color1) {
        return new ImageIcon(buildImage(iconCode, size, color, color1));
    }

    private static BufferedImage buildImage(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        Dimension dim = label.getPreferredSize();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize(width, height);
        BufferedImage bufImage
                = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics();
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        label.print(g2d);
        g2d.dispose();
        return bufImage;
    }

    private static BufferedImage buildImage(String text, Font font, Color color, Color color1) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        Dimension dim = label.getPreferredSize();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize(width, height);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics();
        g2d.setFont(font);
        FontMetrics ft = g2d.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(text, g2d);
        double x = (width - r2.getWidth()) / 2;
        double y = (height - r2.getHeight()) / 2;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        GradientPaint gra = new GradientPaint(0, 0, color, width, 0, color1);
        g2d.setPaint(gra);
        g2d.drawString(text, (int) x, (int) (y + ft.getAscent()));
        g2d.dispose();
        return bufImage;
    }

    private static Font buildFont(IconCode iconCode, float size) {
        Font font = IconFontSwing.buildFont(iconCode.getFontFamily());
        return font.deriveFont(size);
    }

}
