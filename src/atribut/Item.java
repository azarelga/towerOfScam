package atribut;

import level.Ruangan;

public class Item extends Ruangan{
	private int number;
	private int operasi;
	
	public Item (int number, int operasi) {
		setNumber(number);
		setOperasi(operasi);
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
