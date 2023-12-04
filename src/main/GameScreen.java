package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInput;
import inputs.MouseInput;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;


public class GameScreen extends JPanel {

    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(new MouseInput());
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame(){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame() {
        return game;
    }
}
