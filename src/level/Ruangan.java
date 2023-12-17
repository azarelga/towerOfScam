package level;

import static utilities.Constants.GameConstants.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import utilities.ImportExport;
import utilities.Constants.GameConstants;

public class Ruangan {
    public static final int RUANGAN = 0, GAMEDEV = 1, ITEM = 2;
    protected int type = RUANGAN;
    public static int roomWidth;
    private static int roomHeight;
    private BufferedImage img;
    private BufferedImage finalRoomImg;

    protected boolean isEmpty;
    protected int xPos, yPos;
    protected boolean finalRoom;
    private int finalRoomImgWidth;
    private int finalRoomImgHeight;

    public Ruangan(int xPos, int yPos, boolean isFinalRoom) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isEmpty = true;
        this.finalRoom = isFinalRoom;
        loadImg();
    }

    public void loadImg() {
        Random rand = new Random();
        this.img = ImportExport.GetImage(ImportExport.RUANGAN[rand.nextInt(ImportExport.RUANGAN.length)]);
        this.finalRoomImg = ImportExport.GetImage(ImportExport.FINALROOM);
        roomWidth = (int) (img.getWidth() * ROOMSCALE);
        roomHeight = (int) (img.getHeight() * ROOMSCALE);
        finalRoomImgWidth = (int) (finalRoomImg.getWidth()*0.4 * ROOMSCALE);
        finalRoomImgHeight = (int) (finalRoomImg.getHeight()*0.4 * ROOMSCALE);
        GameConstants.resetConstants();
        HORIZONTALDISTANCE = (HORIZONTALDISTANCE * ROOMSCALE);
        VERTICALDISTANCE = roomHeight;
    }

    public boolean CekEmpty() {
        return this.isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    public void update() {

    }

    public boolean getFinalRoom() {
        return this.finalRoom;
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) (X_START_ROOM + ((xPos - 1) * HORIZONTALDISTANCE)),
                (int) (Y_START - (yPos * VERTICALDISTANCE)), roomWidth, roomHeight, null);
        if (finalRoom) {
            g.drawImage(finalRoomImg, (int) (X_START_ROOM + ((xPos - 1) * HORIZONTALDISTANCE) + roomWidth/2 - finalRoomImgWidth/2),
                    (int) (Y_START - (yPos * VERTICALDISTANCE) - 100*ROOMSCALE),finalRoomImgWidth,finalRoomImgHeight, null);
        }
    }

    public char getOperasi() {
        return ' ';
    }

    public int getEnergy() {
        return 0;
    }

    public void setEnergy(int energy) {
    }

    public int getType() {
        return type;
    }

    public void resetState() {
        if (this.type == GAMEDEV || this.type == ITEM) {
            this.isEmpty = false;
        }
    }

}
