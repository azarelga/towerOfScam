package atribut;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import level.Ruangan;

public class Item extends Ruangan{
	private int number;
	private int operasi;
	private BufferedImage img;	


	public Item (int xPos, int yPos, int number, int operasi) {
		super(xPos, yPos);
		setNumber(number);
		setOperasi(operasi);
		loadItem();
	}

	public void loadItem() {
	}

	public void render(Graphics g) {
		super.render(g);

	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getOperasi() {
		return operasi;
	}
	public void setOperasi(int operasi) {
		this.operasi = operasi;
	}
	
}
