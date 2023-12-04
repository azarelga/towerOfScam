package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import characters.Judol;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilities.Constants.GameConstants.X_OFFSET;
import static utilities.Constants.GameConstants.Y_OFFSET;

import utilities.ImportExport;

public class Playing extends State implements StateMethods {
    private Judol judol;
    private Map<Integer, Boolean> pressedKeys = new HashMap<>();

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        judol = new Judol(0);
    }

    @Override
    public void update() {
        judol.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImportExport.GetImage(ImportExport.BACKGROUND), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        g.drawImage(ImportExport.GetImage(ImportExport.RUANGAN), X_OFFSET-180, Y_OFFSET-87, null);
        judol.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                judol.setUp(pressedKeys.getOrDefault(KeyEvent.VK_UP, false)
                        || pressedKeys.getOrDefault(KeyEvent.VK_W, false));
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                judol.setDown(pressedKeys.getOrDefault(KeyEvent.VK_DOWN, false)
                        || pressedKeys.getOrDefault(KeyEvent.VK_S, false));
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                judol.setLeft(pressedKeys.getOrDefault(KeyEvent.VK_LEFT, false)
                        || pressedKeys.getOrDefault(KeyEvent.VK_A, false));
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                System.out.println(judol.moving);
                judol.setRight(pressedKeys.getOrDefault(KeyEvent.VK_RIGHT, false)
                        || pressedKeys.getOrDefault(KeyEvent.VK_D, false));
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public Judol getJudol() {
        return judol;
    }

}
