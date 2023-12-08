package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atribut.GameDev;
import characters.Judol;
import level.Gedung;
import level.Ruangan;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilities.Constants.GameConstants.HORIZONTALDISTANCE;
import static utilities.Constants.GameConstants.X_START_ROOM;
import static utilities.Constants.GameConstants.Y_START;

import utilities.ImportExport;

public class Playing extends State implements StateMethods {
    private Judol judol;
    private Gedung[] gedung = new Gedung[2];

    private Map<Integer, Boolean> pressedKeys = new HashMap<>();

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        gedung[0] = new Gedung(1, new Ruangan[] { new Ruangan(X_START_ROOM, Y_START) });
        gedung[1] = new Gedung(1, new Ruangan[] { new GameDev((int)(X_START_ROOM+HORIZONTALDISTANCE), Y_START,4,2,1) });
        judol = new Judol(0,5);
    }

    @Override
    public void update() {
        judol.update();
        for (Gedung gdn : gedung)
            gdn.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImportExport.GetImage(ImportExport.BACKGROUND), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        for (Gedung gdn : gedung)
            gdn.render(g);
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
                System.out.println(pressedKeys);
                judol.setRight(pressedKeys.getOrDefault(KeyEvent.VK_RIGHT, false)
                        || pressedKeys.getOrDefault(KeyEvent.VK_D, false));
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(pressedKeys);
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
