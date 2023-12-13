package gamestates;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import characters.Judol;
import level.LevelManager;
import main.Game;
import ui.Buttons;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import utilities.ImportExport;

public class Playing extends State implements StateMethods {
    private LevelManager levelManager;
    private boolean paused = false;
    private boolean cleared = false;

    private Map<Integer, Boolean> pressedKeys = new HashMap<>();
    private BufferedImage background;
    // private BufferedImage pause_button;
    private BufferedImage logo;
    private Buttons[] pauseButtons;
    private Buttons[] clearButtons;
    private BufferedImage levelClear;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        background = ImportExport.GetImage(ImportExport.BACKGROUND);
        logo = ImportExport.GetImage(ImportExport.LOGO);

        pauseButtons = new Buttons[5];
        pauseButtons[0] = new Buttons((int) Game.GAME_WIDTH / 2, 300, ImportExport.RESUME, Gamestate.PLAYING);
        pauseButtons[1] = new Buttons((int) Game.GAME_WIDTH / 2, 380, ImportExport.RESTART, Gamestate.PLAYING);
        pauseButtons[2] = new Buttons((int) Game.GAME_WIDTH / 2, 460, ImportExport.LEVEL, Gamestate.LEVELSELECT);
        pauseButtons[3] = new Buttons((int) Game.GAME_WIDTH / 2, 540, ImportExport.SETTINGS, Gamestate.SETTINGS);
        pauseButtons[4] = new Buttons(40, 40, ImportExport.PAUSE, Gamestate.PLAYING);

        clearButtons = new Buttons[2];
        clearButtons[0] = new Buttons((int) Game.GAME_WIDTH / 2, 300, ImportExport.NEXT, Gamestate.PLAYING);
        clearButtons[1] = new Buttons((int) Game.GAME_WIDTH / 2, 380, ImportExport.HOME, Gamestate.MENU);
        levelClear = ImportExport.GetImage(ImportExport.CLEAR);
    }

    @Override
    public void update() {
        if (Gamestate.control == 1) {
            levelManager.loadLevel();
            Gamestate.control = 0;
        }
        if (!cleared) {
            levelManager.update();
            cleared = levelManager.getCurrentLevel().getIsBeaten();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        if (Gamestate.control == 0) {
            if (!paused) {
                pauseButtons[4].draw(g);
                levelManager.render(g);
            } else {
                g.drawImage(logo, (int) (Game.GAME_WIDTH / 2 - logo.getWidth() / 2), 170, null);
                for (Buttons mb : pauseButtons)
                    mb.draw(g);
            }
        } else {
            g.setFont(g.getFont().deriveFont(100.0f));
            FontMetrics fm = g.getFontMetrics();
            g.drawString("Loading...", GAME_HEIGHT / 2 - fm.getHeight() / 2, GAME_WIDTH / 2 - fm.getAscent() / 2);
        }
        if (cleared) {
            g.drawImage(levelClear, GAME_WIDTH / 2 - 360 / 2,
                    210, 360, 340, null);
            clearButtons[0].draw(g);
            clearButtons[1].draw(g);
            pauseButtons[2].draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), true);
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || checkRoomAvailability(e))
                && !levelManager.getCurrentLevel().getJudol().isTeleporting()
                && !paused) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    levelManager.getCurrentLevel().getJudol().setUp(pressedKeys.getOrDefault(KeyEvent.VK_UP, false)
                            || pressedKeys.getOrDefault(KeyEvent.VK_W, false));
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    levelManager.getCurrentLevel().getJudol().setDown(pressedKeys.getOrDefault(KeyEvent.VK_DOWN, false)
                            || pressedKeys.getOrDefault(KeyEvent.VK_S, false));
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    levelManager.getCurrentLevel().getJudol().setLeft(pressedKeys.getOrDefault(KeyEvent.VK_LEFT, false)
                            || pressedKeys.getOrDefault(KeyEvent.VK_A, false));
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    System.out.println(pressedKeys);
                    levelManager.getCurrentLevel().getJudol()
                            .setRight(pressedKeys.getOrDefault(KeyEvent.VK_RIGHT, false)
                                    || pressedKeys.getOrDefault(KeyEvent.VK_D, false));
                    break;
                case KeyEvent.VK_SPACE:
                    levelManager.resetLevel();
                    levelManager.getCurrentLevel().getJudol().resetPosition();
                    break;
            }
        }
    }

    private boolean checkRoomAvailability(KeyEvent e) {
        boolean isAvailable = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(
                        levelManager.getCurrentLevel().getJudol().getxIndex(),
                        levelManager.getCurrentLevel().getJudol().getyIndex() + 1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(
                        levelManager.getCurrentLevel().getJudol().getxIndex(),
                        levelManager.getCurrentLevel().getJudol().getyIndex() - 1);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(
                        levelManager.getCurrentLevel().getJudol().getxIndex() - 1,
                        levelManager.getCurrentLevel().getJudol().getyIndex());
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                isAvailable = levelManager.getCurrentLevel().cekRuangan(
                        levelManager.getCurrentLevel().getJudol().getxIndex() + 1,
                        levelManager.getCurrentLevel().getJudol().getyIndex());
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
            for (int i = 0; i < 4; i++) {
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
        if (levelManager.getCurrentLevel().getIsBeaten()) {
            for (int i = 0; i < 2; i++) {
                if (isIn(e, clearButtons[i].getBounds())) {
                    if (i == 0) {
                        levelManager.getCurrentLevel().setIsBeaten(false);
                        levelManager.incrementLevel();
                        Gamestate.control =1;
                        cleared = false;
                    } else
                        clearButtons[i].applyGamestate();
                }
            }
        }
        if (paused) {
            for (int i = 0; i < 4; i++) {
                if (isIn(e, pauseButtons[i].getBounds())) {
                    if (pauseButtons[i].isMousePressed()) {
                        if (i == 0) {
                            paused = false;
                        } else if (i == 1) {
                            levelManager.resetLevel();
                            levelManager.getCurrentLevel().getJudol().resetPosition();
                            paused = false;
                        } else {
                            pauseButtons[i].applyGamestate();
                            levelManager.resetLevel();
                            levelManager.getCurrentLevel().getJudol().resetPosition();
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
        for (Buttons mb : pauseButtons)
            mb.setMousePressed(false);
        for (Buttons mb : clearButtons)
            mb.setMousePressed(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public Judol getJudol() {
        return levelManager.getCurrentLevel().getJudol();
    }

}
