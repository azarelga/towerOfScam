package level;

import java.awt.Graphics;
import java.util.ArrayList;

import atribut.GameDev;
import atribut.Item;
import main.Game;
import utilities.ImportExport;

public class LevelManager {

    private Game game;
    private Level currentLevel;
    private int lvlIndex;

    public LevelManager(Game game) {
        this.game = game;
        lvlIndex=0;
        currentLevel = new Level(lvlIndex);
        currentLevel = ImportExport.buildLevels(lvlIndex);
    }
    
    public void resetLevel() {
        currentLevel.resetState();
    }

    public void update() {
        currentLevel.update();
    }

    public void render(Graphics g) {
        currentLevel.render(g);
    }

    public Level getCurrentLevel() {
		return currentLevel;
	}

	public int getLevelIndex() {
		return lvlIndex;
	}

    public void incrementLevel() {
        ++lvlIndex;
    }

	public void setLevelIndex(int lvlIndex) {
		this.lvlIndex = lvlIndex;
	}

}
