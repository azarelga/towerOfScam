package characters;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import atribut.Item;
import level.Ruangan;
import utilities.ImportExport;

public class Judol extends Entity {
	// animation variables
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private float speedX = 1.0f;
	private float speedY = 1.27f;
	private static float scale = ENTITYSCALE;
	public boolean moving = false, jumping = false;
	private boolean left, up, right, down;

	// intrinsic variables
	private int energy;
	private int xIndex = 1;
	private int yIndex = 1;
	
	private int scoreMax;
	private int currentLevel;
	
	// constructor
	public Judol(int currentLevel, int energy) {
		super(X_START, Y_START, (int)(100*scale), (int)(100*scale));
		this.energy = energy;
		this.scoreMax = 0;
		this.currentLevel = currentLevel;
		loadAnimations();
	}

	// animation methods
	public void update() {
		updatePos();
		updateAniTick();
		setAnimation();
	}

	private void updatePos() {
		moving = false;
		jumping = false;
		if (left && !right && xIndex > 1 && getPostXJ() > X_START + (xIndex - 2) * HORIZONTALDISTANCE) {
			MoveJudol(-speedX, 0);
			if (yIndex == 1 && up != true) moving = true;
			else jumping = true;
			if (getPostXJ() <= X_START + (xIndex - 2) * HORIZONTALDISTANCE) {
				xIndex--;
				setLeft(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				System.out.printf("SAMPE DI %d %d \n", getPostXJ(), getPostYJ());
			}
		}
		if (right && !left && getPostXJ() < X_START + (xIndex) * HORIZONTALDISTANCE) {
			MoveJudol(speedX, 0);
			if ((yIndex == 1 && up != true) || (jumping == true && yIndex != 1)) moving = true;
			else jumping = true;
			if (getPostXJ() >= X_START + (xIndex) * HORIZONTALDISTANCE) {
				xIndex++;
				setRight(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				System.out.printf("SAMPE DI %d %d \n", getPostXJ(), getPostYJ());
			}
		}
		if (up && !down && getPostYJ() > Y_START - (yIndex) * VERTICALDISTANCE) {
			MoveJudol(0, -speedY);
			jumping = true;
			if (getPostYJ() <= Y_START - ((yIndex) * VERTICALDISTANCE)) {
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				yIndex++;
				setUp(false);
			}
		}
		if (down && !up && getPostYJ() < Y_START - (yIndex-2) * VERTICALDISTANCE) {
			MoveJudol(0, speedY);
			jumping = true;
			if (getPostYJ() >= Y_START - (yIndex-2) * VERTICALDISTANCE) {
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				yIndex--;
				setDown(false);
			}
		}
	}
	
	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}

	private void setAnimation() {
		int startAni = playerAction;
		if (moving)
			playerAction = RUNNING;
		else if (jumping)
			playerAction = JUMP;
		else
			playerAction = IDLE;
		if (startAni != playerAction)
			resetAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], getPostXJ()-20, getPostYJ()-85, width, height, null);
		g.setFont(g.getFont().deriveFont(40.0f));
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(GetEnergy()), (int) (getPostXJ() + width / 2), getPostYJ() - 85);
	}

	private void updateAniTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
	}

	private void resetAnimation() {
		aniTick = 0;
		aniIndex = 0;
	}

	public void resetDirection() {
		left = right = up = down = false;
	}

	private void loadAnimations() {
		BufferedImage img = ImportExport.GetImage(ImportExport.JUDOL);
		animations = new BufferedImage[3][8];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				if (i == 0) {
					animations[0][0] = img.getSubimage(100, 131, 100, 100);
					animations[0][1] = img.getSubimage(370, 131, 100, 100);
					animations[0][2] = img.getSubimage(639, 131, 100, 100);
					animations[0][3] = img.getSubimage(895, 131, 100, 100);
					animations[0][4] = img.getSubimage(1151, 131, 100, 100);
					animations[0][5] = img.getSubimage(1406, 131, 100, 100);
					animations[0][6] = img.getSubimage(1663, 131, 100, 100);
					animations[0][7] = img.getSubimage(1923, 131, 100, 100);
				}
				if (i == 1) {
					animations[1][j] = img.getSubimage(121 + 256 * j, 380, 100, 100);
				}
				if (i == 2) {
					animations[2][j] = img.getSubimage(128 + 256 * j, 648, 100, 100);
				}
			}
		}
	}

	// intrinsic methods
	public void CastItem(Ruangan ruangan) {
		if (ruangan.getOperasi() == '+') {
			this.energy += ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '-') {
			this.energy -= ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '*') {
			this.energy *= ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '/') {
			this.energy /= ruangan.getEnergy();
		}
		ruangan.setIsEmpty(true);
	}

	public int GetEnergy() {
		return this.energy;
	}

	public void ReceiveEnergy(int energy) {
		this.energy += energy;
	}

	public void MoveJudol(float newX, float newY) {
		this.x += newX;
		this.y += newY;
	}

	public int getPostXJ() {
		return (int) this.x;
	}

	public int getPostYJ() {
		return (int) this.y;
	}

	public int getScoreMax() {
		return this.scoreMax;
	}

	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void setUp(boolean b) {
		up = b;		
		System.out.println(left + " " + right + " " + up + " " + down);
	}

	public void setDown(boolean b) {
		if (yIndex != 1 || down == true)
			down = b;
		System.out.println(left + " " + right + " " + up + " " + down);
	}

	public void setLeft(boolean b) {
		if (xIndex != 1 || left == true)
			left = b;
		System.out.println(left + " " + right + " " + up + " " + down);
	}

	public void setRight(boolean b) {
		right = b;
		System.out.println(left + " " + right + " " + up + " " + down);
	}

	public boolean isMoving() {
		return moving;
	}
	public boolean isJumping() {
		return jumping;
	}

	public void resetPosition() {
		this.xIndex = 1;
		this.yIndex = 1;
		this.x = X_START;
		this.y = Y_START;
		this.energy = 5;
	}

}
