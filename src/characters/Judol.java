package characters;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.GameConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import atribut.Item;

public class Judol extends Entity {
	// animation variables
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private float speedX= 1.0f;
	private float speedY = 0.5f;
	private float distanceToRoomHorizontal = 50.0f;
	private float distanceToRoomVertical = 40.0f;
	private int scale = 1;
	public boolean moving = false, jumping = false;
	private boolean left, up, right, down;

	// intrinsic variables
	private int energy;
	private int xIndex = 1, yIndex = 1;
	private int scoreMax;
	private int currentLevel;

	public void update() {
		updatePos();
		updateAniTick();
		setAnimation();
	}

	private void updatePos() {
		moving = false;
		jumping = false;
		if (left && !right && xIndex > 1 && getPostXJ() > X_OFFSET + (xIndex-1) * distanceToRoomHorizontal) {
			MoveJudol(-speedX, 0);
			moving = true;
			if (getPostXJ() <= X_OFFSET + (xIndex-1) * distanceToRoomHorizontal) {
				xIndex--;
				setLeft(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex, yIndex);
			}
		}
		if (right && !left && getPostXJ() < X_OFFSET + (xIndex+1) * distanceToRoomHorizontal) {
			MoveJudol(speedX, 0);
			moving = true;
			if (getPostXJ() >= X_OFFSET + (xIndex+1) * distanceToRoomHorizontal) {
				xIndex++;
				setRight(false);
				System.out.printf("SAMPE DI %d %d \n", xIndex,yIndex);
			}
		}
		if (up && !down && getPostYJ() > Y_OFFSET + (yIndex-1) * distanceToRoomVertical) {
			MoveJudol(0, -speedY);
			jumping = true;
			if (getPostYJ() <= Y_OFFSET + ((yIndex-1) * distanceToRoomVertical)) {
				yIndex--;
				setUp(false);
			}
		}
		if (down && !up && getPostYJ() < Y_OFFSET + (yIndex+1) * distanceToRoomVertical) {
			MoveJudol(0, speedY);
			jumping = true;
			if (getPostYJ() >= Y_OFFSET + (yIndex+1) * distanceToRoomVertical) {
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
		g.drawImage(animations[playerAction][aniIndex], getPostXJ(), getPostYJ(), width * scale, height * scale, null);
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

	public Judol(int currentLevel) {
		super(X_OFFSET, Y_OFFSET, 100, 100);
		this.energy = 0;
		this.scoreMax = 0;
		this.currentLevel = currentLevel;
		loadAnimations();
	}

	private void loadAnimations() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("Judol.png");
		try {
			BufferedImage img = ImageIO.read(is);

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

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

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
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

}
