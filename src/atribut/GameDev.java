package atribut;

import static utilities.Constants.GameConstants.HORIZONTALDISTANCE;
import static utilities.Constants.GameConstants.ROOMSCALE;
import static utilities.Constants.GameConstants.VERTICALDISTANCE;
import static utilities.Constants.GameConstants.X_START_ROOM;
import static utilities.Constants.GameConstants.Y_START;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import level.Ruangan;
import ui.HoverText;
import utilities.ImportExport;

public class GameDev extends Ruangan {

	// animation variables
	private BufferedImage animations[][];
	private int animationLength[] = { 6, 10 };
	private int aniTick, aniIndex, aniSpeed = 25;
	private int gdHeight = 85;
	private int gdWidth = 62;
	private int playerAction = 0;
	private int isActive = 1;

	// intrinsic variables
	private int health;
	private int x, y;
	private HoverText hover;

	// constructor
	public GameDev(int xPos, int yPos, int health) {
		super(xPos, yPos, false);
		this.isEmpty = false;
		loadAnimations();
		this.type = GAMEDEV;
		this.x = (int) ((this.xPos - 1) * HORIZONTALDISTANCE) + X_START_ROOM + roomWidth / 2;
		this.y = (int) (Y_START - ((this.yPos - 1) * VERTICALDISTANCE) - (gdHeight+20) * ROOMSCALE);
		this.health = health;
		this.hover = new HoverText(Color.RED);
	}

	// animation methods
	public void render(Graphics g) {
		super.render(g);
		if (isActive == 1) {
			g.drawImage(animations[playerAction][aniIndex], x, y,
					(int) (animations[playerAction][aniIndex].getWidth() * ROOMSCALE),
					(int) (animations[playerAction][aniIndex].getHeight() * ROOMSCALE), null);
			if (playerAction == 0) hover.draw(g, x + gdWidth/2,(int)(y-30*ROOMSCALE), Integer.toString(getEnergy()));
		}
	}

	public void loadAnimations() {
		animations = new BufferedImage[2][10];
		BufferedImage img = ImportExport.GetImage(ImportExport.GAMEDEVS[0]);
		for (int i = 0; i < 6; i++) {
			animations[0][i] = img.getSubimage((i * 256) + 99, 138, gdWidth, gdHeight);
		}
		for (int i = 0; i < 10; i++) {
			animations[1][i] = img.getSubimage((i * 245) + 117, 303, 170, 204); // 181 507
		}
	}

	public void setAnimation() {
		int startAni = playerAction;
		if (this.isEmpty == false) {
			playerAction = 0;
		} else {
			if (playerAction != 1) this.y = this.y - gdHeight;
			playerAction = 1;
		}
		if (startAni != playerAction)
			resetAnimation();
	}

	@Override
	public void resetState() {
		this.isEmpty = false;
		if (playerAction == 1) this.y += gdHeight;
		this.isActive = 1;
		this.playerAction = 0;
	}

	private void resetAnimation() {
		aniTick = 0;
		aniIndex = 0;
	}

	public void update() {
		if (isActive == 1) {
			updateAniTick();
			setAnimation();
		}
	}

	private void updateAniTick() {
		aniTick++;
		if (aniTick > aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= animationLength[playerAction]) {
				if (playerAction == 1)
					isActive = 0;
				aniIndex = 0;
			}
		}
	}

	// intrinsic methods
	@Override
	public int getEnergy() {
		return health;
	}
}
