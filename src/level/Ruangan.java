package level;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import atribut.Item;
import utilities.ImportExport;

public class Ruangan {
    private boolean isEmpty;
    private final float roomScale = 0.8f;
    public static int roomWidth;
    private static int roomHeight;
    private BufferedImage img;
    protected int xPos, yPos;

    public Ruangan(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isEmpty = false;
        loadImg();
    }

    public void loadImg() {
        this.img = ImportExport.GetImage(ImportExport.RUANGAN[0]);
        roomWidth = (int) (img.getWidth() * roomScale);
        roomHeight = (int) (img.getHeight() * roomScale);
    }

    public boolean CekEmpty() {
        return this.isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public void update() {

    }

	public void render(Graphics g) {
        g.drawImage(img, xPos, yPos-roomHeight,roomWidth,roomHeight, null);
	}

}
