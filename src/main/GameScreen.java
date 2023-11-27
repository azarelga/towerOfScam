package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import characters.Judol;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import inputs.KeyboardInput;
import inputs.MouseInput;

public class GameScreen extends JPanel {

    public Judol judol;
    private BufferedImage img;
    private BufferedImage[] idleAnimation;
    private int aniTick, aniIndex, aniSpeed = 9;

    public GameScreen() {
        judol = new Judol(0);
        judol.MoveJudol(204, 819);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(new MouseInput());
    }

    private void loadAnimations() {
        idleAnimation = new BufferedImage[6];
        for(int i = 0; i<6; i++){
            idleAnimation[i] = img.getSubimage(128 + 256*i, 648, 88, 88);
        }
    }

    private void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idleAnimation.length) {
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
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1440, 1024);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        updateAniTick();
        g.drawImage(idleAnimation[aniIndex], judol.getPostXJ(), judol.getPostYJ(), 88, 88, null);
    }
}
