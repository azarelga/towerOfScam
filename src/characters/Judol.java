package characters;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import level.Ruangan;
import ui.HoverText;
import utilities.ImportExport;

public class Judol extends Entity {
	// animation variables
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private float offsetX = 50 * ROOMSCALE, offsetY = -1 * 106 * ROOMSCALE;
	public boolean moving = false, jumping = false;
	private boolean teleFrom = false, teleTo = false;
	private boolean left, up, right, down;
	private HoverText hover;

	// intrinsic variables
	public int energy;
	private int xIndex = 1;
	private int yIndex = 1;
	private int scoreMax;
	private int currentLevel;

	// constructor
	public Judol(int currentLevel, int energy) {
		super(X_START_ROOM, Y_START, (int) (65 * ROOMSCALE), (int) (89 * ROOMSCALE));
		this.energy = energy;
		this.x = (int) (getPostXJ() + offsetX);
		this.y = (int) (getPostYJ() + offsetY);
		this.scoreMax = 0;
		this.currentLevel = currentLevel;
		this.hover = new HoverText(Color.BLUE);
		loadAnimations();
	}

	// animation methods
	public void update() {
		updatePos();
		updateAniTick();
		setAnimation();
	}

	private void updatePos() {
		if (up && !down) {
			if (aniIndex < 5) {
				teleFrom = true;
			} else if (aniIndex >= 5) {
				if (teleFrom) {
					yIndex++;
					MoveJudol(0, -(VERTICALDISTANCE));
					teleFrom = false;
				}
				teleTo = true;
			}
		}
		if (down && !up) {
			if (aniIndex < 5) {
				teleFrom = true;
			} else if (aniIndex >= 5) {
				if (teleFrom) {
					yIndex--;
					MoveJudol(0, (VERTICALDISTANCE));
					teleFrom = false;
				}
				teleTo = true;
			}
		}
		if (left && !right) {
			if (aniIndex < 5) {
				teleFrom = true;
			} else if (aniIndex >= 5) {
				if (teleFrom) {
					xIndex--;
					MoveJudol(-(HORIZONTALDISTANCE), 0);
					teleFrom = false;
				}
				teleTo = true;
			}
		}
		if (right && !left) {
			if (aniIndex < 5) {
				teleFrom = true;
			} else if (aniIndex >= 5) {
				if (teleFrom) {
					xIndex++;
					MoveJudol((HORIZONTALDISTANCE), 0);
					teleFrom = false;
				}
				teleTo = true;
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
		else if (teleFrom || teleTo)
			playerAction = TELEPORTING;
		else
			playerAction = IDLE;
		if (startAni != playerAction)
			resetAnimation();
	}

	public void render(Graphics g) {
		if (teleFrom || teleTo) {
			g.drawImage(animations[playerAction][aniIndex], (int) (getPostXJ()-20*ROOMSCALE), (int) (getPostYJ() - (23 * ROOMSCALE)),
					(int) (98 * ROOMSCALE), (int) (150 * ROOMSCALE), null);
		} else {
			g.drawImage(animations[playerAction][aniIndex], getPostXJ(), getPostYJ(), width, height, null);
		}
		hover.draw(g, getPostXJ() + width/2,(int) (getPostYJ()-30*ROOMSCALE), Integer.toString(energy));
	}

	private void updateAniTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
			if (aniIndex == 9 && playerAction == TELEPORTING) {
				teleTo = false;
				teleFrom = false;
				resetDirection();
				playerAction = IDLE;
				resetAnimation();
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
		animations = new BufferedImage[4][10];
		animations[0][0] = img.getSubimage(100, 131, 100, 100);
		animations[0][1] = img.getSubimage(370, 131, 100, 100);
		animations[0][2] = img.getSubimage(639, 131, 100, 100);
		animations[0][3] = img.getSubimage(895, 131, 100, 100);
		animations[0][4] = img.getSubimage(1151, 131, 100, 100);
		animations[0][5] = img.getSubimage(1406, 131, 100, 100);
		animations[0][6] = img.getSubimage(1663, 131, 100, 100);
		animations[0][7] = img.getSubimage(1923, 131, 100, 100);
		animations[1][0] = img.getSubimage(108, 1088, 98, 150);
		animations[1][1] = img.getSubimage(363, 1088, 98, 150);
		animations[1][2] = img.getSubimage(620, 1088, 98, 150);
		animations[1][3] = img.getSubimage(876, 1088, 98, 150);
		animations[1][4] = img.getSubimage(1101, 1088, 98, 150);
		animations[1][5] = img.getSubimage(1278, 1088, 98, 150);
		animations[1][6] = img.getSubimage(366, 1358, 98, 150);
		animations[1][7] = img.getSubimage(622, 1358, 98, 150);
		animations[1][8] = img.getSubimage(878, 1358, 98, 150);
		animations[1][9] = img.getSubimage(1117, 1358, 98, 150);
		for (int j = 0; j < animations[2].length; j++) {
			animations[2][j] = img.getSubimage(128 + 256 * j, 648, 65, 89);
		}
	}

	// intrinsic methods
	public int CastItem(Ruangan ruangan) {
		int temp = this.energy;
		if (ruangan.getOperasi() == '+') {
			temp += ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '-') {
			temp -= ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '*') {
			temp *= ruangan.getEnergy();
		} else if (ruangan.getOperasi() == '/') {
			temp /= ruangan.getEnergy();
		}
		return temp;
	}

	public int getEnergy() {
		return this.energy;
	}

	public void receiveEnergy(int energy) {
		this.energy += energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void MoveJudol(float newX, float newY) {
		this.x += newX;
		this.y += newY;
		System.out.println("MOVED JUDOL");
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

	public boolean isTeleporting() {
		return teleTo || teleFrom;
	}

	public void resetPosition() {
		this.xIndex = 1;
		this.yIndex = 1;
		this.x = X_START_ROOM + offsetX;
		this.y = Y_START + offsetY;
	}

}
