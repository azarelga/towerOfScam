package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import characters.Judol;
import level.LevelManager;
import level.Ruangan;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import utilities.ImportExport;

public class Playing extends State implements StateMethods {
    private LevelManager levelManager;
    private Judol judol;

    private Map<Integer, Boolean> pressedKeys = new HashMap<>();
    private BufferedImage background;
    private BufferedImage pause_button;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        judol = new Judol(0, 5);
        background = ImportExport.GetImage(ImportExport.BACKGROUND);
        pause_button = ImportExport.GetImage(ImportExport.PAUSE);
    }

    public void loadLevel() {
        levelManager.loadLevel();
    }

    @Override
    public void update() {
        if (Gamestate.control == 1) {
            levelManager.loadLevel();
            Gamestate.control = 0;
        }
        judol.update();
        levelManager.update();
        checkJudolAndRoom();
    }

    private void checkJudolAndRoom() {
        int judolX = judol.getxIndex();
        int judolY = judol.getyIndex();
        if (levelManager.getCurrentLevel().getRuangan(judolX, judolY).getIsEmpty() == false) {
            if (levelManager.getCurrentLevel().getRuangan(judolX, judolY).getType() == Ruangan.GAMEDEV
                    && judol.GetEnergy() > levelManager.getCurrentLevel().getRuangan(judolX, judolY).getEnergy()) {
                System.out.println("HARUSNYA GAMEDEVNYA MATI");
                judol.ReceiveEnergy(levelManager.getCurrentLevel().getRuangan(judolX, judolY).getEnergy());
                levelManager.getCurrentLevel().getRuangan(judolX, judolY).setIsEmpty(true);
            } else if (levelManager.getCurrentLevel().getRuangan(judolX, judolY).getType()==Ruangan.ITEM) {
                judol.CastItem(levelManager.getCurrentLevel().getRuangan(judolX, judolY));
            }
        }
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        g.drawImage(pause_button, 40, 40, null);
        levelManager.render(g);
        judol.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), true);
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || checkRoomAvailability(e)) && !judol.isMoving() && !judol.isJumping()) {
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
                    levelManager.resetLevel();
                    judol.resetPosition();
                    break;
            }
        }
    }

    private boolean checkRoomAvailability(KeyEvent e) {
        boolean isAvailable = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(judol.getxIndex(), judol.getyIndex() + 1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(judol.getxIndex(), judol.getyIndex() - 1);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(judol.getxIndex() - 1, judol.getyIndex());
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(judol.getxIndex() + 1, judol.getyIndex());
                break;
        }
        return isAvailable;
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
