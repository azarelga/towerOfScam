package ui;

import static utilities.Constants.Buttons.WIDEBUTTONSCALE;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import main.Game;
import utilities.ImportExport;

public class SettingsButtons {
	private int B_WIDTH;
    private  int B_HEIGHT;
    private static final float SCALE = 0.73f;
	private int isBGM = 1, isSFX = 1;
    private static int buttonState;
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle[] bounds;
    private int homeWidth;
    private int homeHeight;
	private BufferedImage home;
	private BufferedImage sfx;
	private BufferedImage bgm;

	public SettingsButtons() {
		loadImgs();
		initBounds();
	}

	private void initBounds() {
        bounds = new Rectangle[3];
		bounds[0] = new Rectangle((int)(Game.GAME_WIDTH/2 + homeWidth/2 - B_WIDTH),200, B_WIDTH, B_HEIGHT);
		bounds[1] = new Rectangle((int)(Game.GAME_WIDTH/2 + homeWidth/2 - B_WIDTH),300, B_WIDTH, B_HEIGHT);
		bounds[2] =  new Rectangle((int)(Game.GAME_WIDTH/2 - homeWidth/2), 550, homeWidth,homeHeight);
	}

	private void loadImgs() {
		imgs = new BufferedImage[2];
		bgm = ImportExport.GetImage(ImportExport.BGM);
		sfx = ImportExport.GetImage(ImportExport.SFX);
		imgs[0] = ImportExport.GetImage(ImportExport.MUTE);
		imgs[1] = ImportExport.GetImage(ImportExport.SOUND);
		B_WIDTH=(int)(imgs[0].getWidth()*SCALE);
		B_HEIGHT=(int)(81*SCALE);
		home = ImportExport.GetImage(ImportExport.HOME);
        homeWidth = (int)(home.getWidth()*WIDEBUTTONSCALE);
        homeHeight = (int)(home.getHeight()*WIDEBUTTONSCALE);
	}

	public void draw(Graphics g) {
		g.drawImage(imgs[isBGM], (int)(Game.GAME_WIDTH/2 + homeWidth/2 - B_WIDTH), 210, B_WIDTH, B_HEIGHT, null);
        g.drawImage(bgm, 395, 200, null);
		g.drawImage(imgs[isSFX], (int)(Game.GAME_WIDTH/2 + homeWidth/2 - B_WIDTH), 310, B_WIDTH, B_HEIGHT, null);
        g.drawImage(sfx, 395, 300, null);
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
		buttonState = i;
        this.mousePressed = mousePressed;
	}

	public Rectangle[] getBounds() {
		return bounds;
	}
 
	public void applyGamestate() {
		if (buttonState == 0) isBGM = (isBGM + 1) % 2;
		else if (buttonState == 1) isSFX = (isSFX + 1) % 2;
		else if (buttonState == 2) Gamestate.state = Gamestate.MENU;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
