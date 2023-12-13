package gamestates;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.Buttons;
import utilities.ImportExport;

public class Menu extends State implements StateMethods {
    private Buttons[] buttons = new Buttons[3];
    private BufferedImage background, logo;
    private double logoScale = 0.18;

    public Menu(Game game) {
        super(game);
        loadBackground();
        loadButtons();
    }


    public void loadBackground() {
        background = ImportExport.GetImage(ImportExport.BACKGROUNDMENU);
        logo = ImportExport.GetImage(ImportExport.LOGOMENU);
        System.out.println((int) (logo.getWidth() * 0.2) + " " + logo.getHeight());
    }

    public void loadButtons() {
        buttons[0] = new Buttons((int) Game.GAME_WIDTH / 2, 400, ImportExport.PLAY, Gamestate.PLAYING);
        buttons[1] = new Buttons((int) Game.GAME_WIDTH / 2, 480, ImportExport.LEVEL, Gamestate.LEVELSELECT);
        buttons[2] = new Buttons((int) Game.GAME_WIDTH / 2, 560, ImportExport.SETTINGS, Gamestate.SETTINGS);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(logo, (int) (Game.GAME_WIDTH / 2 - logo.getWidth() * logoScale / 2), 60,
                (int) (logo.getWidth() * logoScale), (int) (logo.getHeight() * logoScale), null);
        for (Buttons mb : buttons)
            mb.draw(g);
    }
    
    

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        // if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        // Gamestate.state = Gamestate.PLAYING;
        // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Buttons mb : buttons) {
            if (isIn(e, mb)) {
                System.out.println("Play Clicked");
                mb.setMousePressed(true);
            }
        }
    }

    private boolean isIn(MouseEvent e, Buttons mb) {
        return e.getX() >= mb.getBounds().x && e.getX() <= mb.getBounds().x + mb.getBounds().width
                && e.getY() >= mb.getBounds().y && e.getY() <= mb.getBounds().y + mb.getBounds().height;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Buttons mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (Buttons mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

}
