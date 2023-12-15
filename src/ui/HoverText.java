package ui;

import static utilities.Constants.GameConstants.ROOMSCALE;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class HoverText {

    private Color color;

    public HoverText(Color color) {
        this.color = color;
    }
    
    public void draw(Graphics g, int x, int y, int number) {
        g.setFont(g.getFont().deriveFont(50.0f * ROOMSCALE));
		g.setColor(color);
		g.drawString(Integer.toString(number), x - g.getFontMetrics().stringWidth(Integer.toString(number)), y);
    }
}
