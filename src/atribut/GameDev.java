package atribut;

import static utilities.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import level.Ruangan;
import utilities.ImportExport;

public class GameDev extends Ruangan{

	// animation variables
	private BufferedImage animations[][];
	private int animationLength[] = {6, 10};
	private int aniTick, aniIndex, aniSpeed = 25;
	private int gdHeight = 85;
	private int gdWidth = 62;
	private int playerAction = 0;
	
	// intrinsic variables
	private int health;
	
	// constructor
	public GameDev(int xPos, int yPos, int health,int xIndex, int yIndex) {
		super(xPos, yPos);
		loadAnimations();
		setHealth(health);
	}

	// animation methods
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(animations[playerAction][aniIndex], xPos+roomWidth/2, yPos-gdHeight-13, null);
	}

	public void loadAnimations() {
		animations = new BufferedImage[2][10];
		BufferedImage img = ImportExport.GetImage(ImportExport.GAMEDEVS[0]);
		for (int i = 0; i < 6; i++) {
			animations[0][i] = img.getSubimage((i*256) + 99, 138, gdWidth,gdHeight ); 
		}
		for (int i = 0; i < 10; i++) {
			animations[1][i] = img.getSubimage((i*256) + 117, 303, 96,122); // 181 425 
		}
	}

	public void setAnimation() {
		if(health == 0) {
			playerAction = 1;
		}
		else {
			playerAction = 0;
		}
	}

	public void update() {
		updateAniTick();
	}

	private void updateAniTick() {
		aniTick++;
		if (aniTick > aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= animationLength[playerAction]) {
				aniIndex = 0;
			}
		}
	}

	// intrinsic methods
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
