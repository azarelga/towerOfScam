package com.towerofscam.characters;

import com.towerofscam.atribut.Item;

public class Judol {
	private int energy;
	private int postXJ;
	private int postYJ;
	private int scoreMax;
	private int currentLevel;

	public Judol(int currentLevel) {
		this.energy = 0;
		this.postXJ = 0;
		this.postYJ = 0;
		this.scoreMax = 0;
		this.currentLevel = currentLevel;
	}

	public void CastItem(Item item) {
		if (item.getOperasi() == '+'){
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

	public void MoveJudol(int newX, int newY) {
		this.postXJ = newX;
		this.postYJ = newY;
	}

	public int getPostXJ() {
		return this.postXJ;
	}

	public int getPostYJ() {
		return this.postYJ;
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
}
