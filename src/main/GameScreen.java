package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import characters.Judol;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import inputs.KeyboardInput;
import inputs.MouseInput;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.Directions.*;

public class GameScreen extends JPanel {

    public Judol judol;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
	private int playerDir = -1;
    private int distanceToRoomHorizontal = 10;
    private int distanceToRoomVertical = 5;
	private boolean moving = false;
	private boolean jumping = false;

    public GameScreen() {
        judol = new Judol(0);
        judol.MoveJudol(204, 819);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(new MouseInput());
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        if (direction == LEFT || direction == RIGHT) moving = true;
        else jumping = true;
    }

    private void updatePos() {
		if (moving) {
			switch (playerDir) {
			case LEFT:
                judol.MoveJudol(-distanceToRoomHorizontal, 0);
				break;
			case RIGHT:
                judol.MoveJudol(distanceToRoomHorizontal, 0);
				break;
			}
		} else if (jumping) {
			switch (playerDir) {
			case UP:
                judol.MoveJudol(0, -distanceToRoomVertical);
				break;
			case DOWN:
                judol.MoveJudol(0,distanceToRoomVertical);
				break;
            }
        }
	}

    public void setMoving(boolean moving) {
		this.moving = moving;
	}

    public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

    private void loadAnimations() {
        animations = new BufferedImage[3][8];
        for(int i = 0; i<animations.length; i++){
            for (int j = 0; j < animations[i].length; j++) {
                if (i == 0) {
                    animations[0][j] = img.getSubimage(120 + 256*j, 134, 88, 100);
                }
                if (i == 1) {
                    animations[1][j] = img.getSubimage(121 + 256*j, 377, 88, 100);
                }
                if (i == 2) {
                    animations[2][j] = img.getSubimage(128 + 256*j, 648, 88, 100);
                }
            }
        }
    }
    private void setAnimation() {
		if (moving)
			playerAction = RUNNING;
        else if (jumping)
            playerAction = JUMP;
		else
			playerAction = IDLE;
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

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Judol.png");
        try {
            img = ImageIO.read(is);
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

    private void setPanelSize() {
        Dimension size = new Dimension(1440, 1024);
        setPreferredSize(size);
    }

    public void updateGame(){
        
        updateAniTick();
        setAnimation();
		updatePos();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(animations[playerAction][aniIndex], judol.getPostXJ(), judol.getPostYJ(), 88, 100, null);
    }
}
