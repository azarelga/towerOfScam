package characters;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import atribut.Item;
import utilities.ImportExport;

public class Judol extends Entity {
	// animation variables
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private float speedX = 1.0f;
	private float speedY = 0.5f;
	private int scale = 1;
	public boolean moving = false, jumping = false;
	private boolean left, up, right, down;

	// intrinsic variables
	private int energy;
	public int xIndex = 1;
	private int yIndex = 1;
	private int scoreMax;
	private int currentLevel;

	// constructor
	public Judol(int currentLevel, int energy) {
		super(X_START, Y_START, 100, 100);
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
			moving = true;
			if (getPostXJ() <= X_START + (xIndex - 2) * HORIZONTALDISTANCE) {
				xIndex--;
				setLeft(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				System.out.printf("SAMPE DI %d %d \n", getPostXJ(), getPostYJ());
				ReceiveEnergy(-20);
			}
		}
		if (right && !left && getPostXJ() < X_START + (xIndex) * HORIZONTALDISTANCE) {
			MoveJudol(speedX, 0);
			moving = true;
			if (getPostXJ() >= X_START + (xIndex) * HORIZONTALDISTANCE) {
				xIndex++;
				setRight(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
				System.out.printf("SAMPE DI %d %d \n", getPostXJ(), getPostYJ());
				ReceiveEnergy(20);
			}
		}
		if (up && !down && getPostYJ() > Y_START + (yIndex - 1) * VERTICALDISTANCE) {
			MoveJudol(0, -speedY);
			jumping = true;
			if (getPostYJ() <= Y_START + ((yIndex - 1) * VERTICALDISTANCE)) {

				yIndex--;
				setUp(false);
			}
		}
		if (down && !up && getPostYJ() < Y_START + (yIndex + 1) * VERTICALDISTANCE) {
			MoveJudol(0, speedY);
			jumping = true;
			if (getPostYJ() >= Y_START + (yIndex + 1) * VERTICALDISTANCE) {
				yIndex++;
				setDown(false);
			}
		}
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
		g.drawImage(animations[playerAction][aniIndex], getPostXJ(), getPostYJ()-height, width * scale, height * scale, null);
		g.setFont(g.getFont().deriveFont(20.0f));
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(GetEnergy()), (int) (getPostXJ() + width / 2), getPostYJ() - 115);
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
					animations[0][j] = img.getSubimage(100 + 256 * j, 128, 100, 100);
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
	public void CastItem(Item item) {
		if (item.getOperasi() == '+') {
			this.energy += item.getNumber();
		} else if (item.getOperasi() == '-') {
			this.energy -= item.getNumber();
		} else if (item.getOperasi() == '*') {
			this.energy *= item.getNumber();
		} else if (item.getOperasi() == '/') {
			this.energy /= item.getNumber();
		}
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

}
