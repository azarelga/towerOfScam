package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import static utilities.Constants.Buttons.*;
import main.Game;
import utilities.ImportExport;

public class LevelButtons {
    private  int B_WIDTH;
    private  int B_HEIGHT;
    private static final float SCALE = 0.73f;
	private int xPos, yPos;
    private int xSpace = 90, ySpace = 70;
    private static int levelState;
	private int xOffsetCenter;
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle[] bounds;
	private BufferedImage home;
    private int homeWidth;
    private int homeHeight;

	public LevelButtons(int xPos, int yPos) {
		this.xPos = xPos -(int)(xSpace+81*SCALE);
		this.yPos = yPos- 110;
		loadImgs();
		initBounds();
	}

	private void initBounds() {
        bounds = new Rectangle[16];
        for (int i = 0; i < 12; i++) {
            bounds[i] = new Rectangle((xPos + xSpace*(i%4)) - xOffsetCenter, (yPos + ySpace*(i/4)), B_WIDTH, B_HEIGHT);
        }
		for (int i = 12; i < 15; i++) {
			bounds[i] = new Rectangle((xPos +(int)(60*SCALE)+ xSpace*(i%3)) - xOffsetCenter, (yPos + ySpace*(i/4)), B_WIDTH, B_HEIGHT);
		}
		bounds[15] = new Rectangle((int)(Game.GAME_WIDTH/2 - homeWidth/2), 550, homeWidth,homeHeight);
	}

	private void loadImgs() {
		BufferedImage img = ImportExport.GetImage(ImportExport.LEVELANGKA);
        imgs = new BufferedImage[15];
        for (int i = 0; i < 15; i++) {
            if (i<4)imgs[i] = img.getSubimage((i * 250)+ 2, 0, 160, 160);
            else if (i>=4 && i<8) imgs[i] = img.getSubimage(((i-4) * 250)+ 2, 200, 160, 160);
            else if (i>=8 && i<12) imgs[i] = img.getSubimage(((i-8) * 250)+ 2, 400, 160, 160);
            else imgs[i] = img.getSubimage((i-12)*250 + 128, 600, 160, 160);
        }
		B_WIDTH=(int)(81*SCALE);
		B_HEIGHT=(int)(81*SCALE);
		xOffsetCenter = B_WIDTH / 2;
		home = ImportExport.GetImage(ImportExport.HOME);
        homeWidth = (int)(home.getWidth()*WIDEBUTTONSCALE);
        homeHeight = (int)(home.getHeight()*WIDEBUTTONSCALE);
	}

	public void draw(Graphics g) {
        for (int i = 0; i < 12; i++) {
            g.drawImage(imgs[i], (xPos + xSpace*(i%4)) - xOffsetCenter, (yPos + ySpace*(i/4)), B_WIDTH, B_HEIGHT, null);
        }
		for (int i = 12; i < 15; i++) {
			g.drawImage(imgs[i], (xPos +(int)(60*SCALE)+ xSpace*(i%3)) - xOffsetCenter, (yPos + ySpace*(i/4)), B_WIDTH, B_HEIGHT, null);
		}
		g.drawImage(home,(int)(Game.GAME_WIDTH/2 - homeWidth/2), 550, homeWidth,homeHeight,null);
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

	public void setMousePressed(int i, boolean mousePressed) {
		levelState = i;
        this.mousePressed = mousePressed;
	}

	public Rectangle[] getBounds() {
		return bounds;
	}
 
	public void applyGamestate() {

		if (levelState == 15) Gamestate.state = Gamestate.MENU;
		else {
			Gamestate.level = levelState;
			Gamestate.control = 1;
			Gamestate.state = Gamestate.PLAYING;
		}
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
