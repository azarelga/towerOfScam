package level;

import java.awt.Graphics;

import gamestates.Gamestate;
import main.Game;
import utilities.ImportExport;

public class LevelManager {

    private Game game;
    private Level currentLevel;

    public LevelManager(Game game) {
        this.game = game;
        loadLevel();
    }
    
    public void loadLevel() {
        currentLevel = new Level(Gamestate.level);
        currentLevel = ImportExport.buildLevels(Gamestate.level);
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
    
    public void incrementLevel() {
        Gamestate.level++;;
    }
    

}
