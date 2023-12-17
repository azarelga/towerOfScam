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
    
    public void draw(Graphics g, int x, int y, String number) {
        g.setFont(g.getFont().deriveFont(45.0f * ROOMSCALE));
		//create strokes
        g.setColor(Color.BLACK);
        g.drawString(number, x - g.getFontMetrics().stringWidth(number)/2 + 1, y + 1);
        g.setColor(color);
		g.drawString(number, x - g.getFontMetrics().stringWidth(number)/2, y);
    }
}
