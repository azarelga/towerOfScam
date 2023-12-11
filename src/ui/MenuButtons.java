package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static utilities.Constants.Buttons.*;
import gamestates.Gamestate;
import main.Game;
import utilities.ImportExport;

public class MenuButtons {
    private  int B_WIDTH;
    private  int B_HEIGHT;
	private int xPos, yPos;
	private String button;
	private int xOffsetCenter;
	private Gamestate state;
	private BufferedImage imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;

	public MenuButtons(int xPos, int yPos, String button, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.button = button;
		this.state = state;
		loadImgs();
		initBounds();
	}

	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
		if (button.equals(ImportExport.PAUSE)) {
			bounds = new Rectangle(40, 40, imgs.getWidth(), imgs.getHeight());
		}
	}

	private void loadImgs() {
		imgs = ImportExport.GetImage(button);
		B_WIDTH=(int)(imgs.getWidth()*WIDEBUTTONSCALE);
		B_HEIGHT=(int) (imgs.getHeight()*WIDEBUTTONSCALE);
		xOffsetCenter = B_WIDTH / 2;
	}

	public void draw(Graphics g) {
		g.drawImage(imgs, xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}

	public void update() {
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}
 
	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
