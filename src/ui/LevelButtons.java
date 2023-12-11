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
		this.xPos = xPos - xSpace;
		this.yPos = yPos- 110;
		loadImgs();
		initBounds();
	}

	private void initBounds() {
        bounds = new Rectangle[11];
        for (int i = 0; i < 10; i++) {
            bounds[i] = new Rectangle((xPos + xSpace*(i%3)) - xOffsetCenter, (yPos + ySpace*(i%3)), B_WIDTH, B_HEIGHT);
        }
		bounds[10] = new Rectangle((int)(Game.GAME_WIDTH/2 - homeWidth/2), 550, homeWidth,homeHeight);
	}

	private void loadImgs() {
		BufferedImage img = ImportExport.GetImage(ImportExport.LEVELANGKA);
        imgs = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            if (i<3)imgs[i] = img.getSubimage((i * 128)+ 27, 55, 81, 81);
            else if (i>=3 && i<6) imgs[i] = img.getSubimage(((i-3) * 128)+ 25, 160, 81, 81);
            else if (i>=6 && i<9) imgs[i] = img.getSubimage(((i-6) * 128)+ 25, 265, 81, 81);
            else imgs[i] = img.getSubimage(25, 370, 81, 81);
        }
		B_WIDTH=(int)(81*SCALE);
		B_HEIGHT=(int)(81*SCALE);
        // xSpace = (int)(xSpace*SCALE);
        // ySpace = (int)(ySpace*SCALE);
		xOffsetCenter = B_WIDTH / 2;
		home = ImportExport.GetImage(ImportExport.HOME);
        homeWidth = (int)(home.getWidth()*WIDEBUTTONSCALE);
        homeHeight = (int)(home.getHeight()*WIDEBUTTONSCALE);
	}

	public void draw(Graphics g) {
        for (int i = 0; i < 10; i++) {
            g.drawImage(imgs[i], (xPos + xSpace*(i%3)) - xOffsetCenter, (yPos + ySpace*(i/3)), B_WIDTH, B_HEIGHT, null);
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
		if (levelState == 10) Gamestate.state = Gamestate.MENU;
		else Gamestate.state = Gamestate.PLAYING;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
