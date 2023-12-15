package atribut;

import static utilities.Constants.GameConstants.HORIZONTALDISTANCE;
import static utilities.Constants.GameConstants.ROOMSCALE;
import static utilities.Constants.GameConstants.VERTICALDISTANCE;
import static utilities.Constants.GameConstants.X_START_ROOM;
import static utilities.Constants.GameConstants.Y_START;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import level.Ruangan;
import ui.HoverText;
import utilities.ImportExport;

public class Item extends Ruangan{
	private int number;
	private char operasi;
	private BufferedImage img;
	private int x, y;
	private float offsetY = 40*ROOMSCALE;
	private int itemHeight, itemWidth;
	private int isActive = 1;
	private HoverText hover;
	private Color color;
	private String string;


	public Item (int xPos, int yPos, int number, char operasi) {
		super(xPos, yPos, false);
		this.isEmpty = false;
		setEnergy(number);
		setOperasi(operasi);
		loadItem();
		this.type = ITEM;
		this.x = (int) ((this.xPos - 1) * HORIZONTALDISTANCE) + X_START_ROOM + roomWidth / 2;
		this.y = (int) (Y_START - ((this.yPos - 1) * VERTICALDISTANCE)-(itemHeight+offsetY));
		this.string = operasi+Integer.toString(number);
	}

	public void update() {
		if (this.isEmpty) {
			isActive = 0;
		}
	}

	public void loadItem() {
		Random rand = new Random();
		if (operasi == '+' || operasi == '*'){
			img = ImportExport.GetImage(ImportExport.BUFFS[rand.nextInt(3)]);
			this.hover = new HoverText(Color.GREEN);
		}
		if (operasi == '-' || operasi == '/'){
			img = ImportExport.GetImage(ImportExport.DEBUFFS[rand.nextInt(2)]);
			this.hover = new HoverText(Color.RED);
		}
		itemHeight =(int) (img.getHeight());
		itemWidth = (int)(img.getWidth());
	}

	public void render(Graphics g) {
		super.render(g);
		if (isActive == 1) {
			g.drawImage(img, x, y, itemWidth, itemHeight, null);
			hover.draw(g, x + itemWidth/2, y - (int)(30*ROOMSCALE), string);
		}
	}

	@Override
	public int getEnergy() {
		return number;
	}

	@Override
	public void setEnergy(int number) {
		this.number = number;
	}

	@Override
	public char getOperasi() {
		return operasi;
	}

	@Override
	public void resetState() {
		this.isEmpty = false;
		this.isActive = 1;
	}
	public void setOperasi(char operasi) {
		this.operasi = operasi;
	}
	
}
