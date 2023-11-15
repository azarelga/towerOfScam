package com.towerofscam.characters;

import com.towerofscam.level.Ruangan;

public class GameDev extends Ruangan{
	private int health;
	
	public GameDev(int health) {
		setHealth(health);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
