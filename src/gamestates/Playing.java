package gamestates;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import characters.Judol;
import level.LevelManager;
import level.Ruangan;
import main.Game;
import ui.MenuButtons;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import utilities.ImportExport;

public class Playing extends State implements StateMethods {
    private LevelManager levelManager;
    private Judol judol;
    private boolean paused = false;

    private Map<Integer, Boolean> pressedKeys = new HashMap<>();
    private BufferedImage background;
    private BufferedImage pause_button;
    private BufferedImage logo;
    private MenuButtons[] pauseButtons;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        judol = new Judol(0, 5);
        background = ImportExport.GetImage(ImportExport.BACKGROUND);
        pause_button = ImportExport.GetImage(ImportExport.PAUSE);
        pauseButtons = new MenuButtons[5];
        logo = ImportExport.GetImage(ImportExport.LOGO);
        pauseButtons[0] = new MenuButtons((int) Game.GAME_WIDTH / 2, 300, ImportExport.RESUME, Gamestate.PLAYING);
        pauseButtons[1] = new MenuButtons((int) Game.GAME_WIDTH / 2, 380, ImportExport.RESTART, Gamestate.PLAYING);
        pauseButtons[2] = new MenuButtons((int) Game.GAME_WIDTH / 2, 460, ImportExport.LEVEL, Gamestate.LEVELSELECT);
        pauseButtons[3] = new MenuButtons((int) Game.GAME_WIDTH / 2, 540, ImportExport.SETTINGS, Gamestate.SETTINGS);
        pauseButtons[4] = new MenuButtons(40, 40, ImportExport.PAUSE, Gamestate.PLAYING);
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
            } else if (levelManager.getCurrentLevel().getRuangan(judolX, judolY).getType() == Ruangan.ITEM) {
                judol.CastItem(levelManager.getCurrentLevel().getRuangan(judolX, judolY));
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        if (!paused) {
            g.drawImage(pause_button, 40, 40, null);
            levelManager.render(g);
            judol.render(g);
        } else {
            g.drawImage(logo, (int) (Game.GAME_WIDTH / 2 - logo.getWidth() / 2), 170, null);
            for (MenuButtons mb : pauseButtons)
                mb.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), true);
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || checkRoomAvailability(e)) && !judol.isMoving() && !judol.isJumping()
                && !paused) {
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
        if (paused) {
            for (int i=0; i<4;i++) {
                if (isIn(e, pauseButtons[i].getBounds())) {
                    pauseButtons[i].setMousePressed(true);
                }
            }
        } else {
            if (isIn(e, pauseButtons[4].getBounds())) {
                pauseButtons[4].setMousePressed(true);
            }
        }
    }

    private boolean isIn(MouseEvent e, Rectangle bounds) {
        return bounds.contains(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            for (int i=0; i<4;i++) {
                if (isIn(e, pauseButtons[i].getBounds())) {
                    if (pauseButtons[i].isMousePressed()) {
                        if (i == 0) {
                            paused = false;
                        } else if (i == 1) {
                            levelManager.resetLevel();
                            judol.resetPosition();
                            paused = false;
                        } else {
                            pauseButtons[i].applyGamestate();
                            levelManager.resetLevel();
                            judol.resetPosition();
                            paused = false;
                        }
                    }
                }
            }
        } else {
            if (isIn(e, pauseButtons[4].getBounds())) {
                if (pauseButtons[4].isMousePressed()) {
                    System.out.println("PAUSED");
                    paused = true;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButtons mb : pauseButtons)
            mb.setMousePressed(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public Judol getJudol() {
        return judol;
    }

}
