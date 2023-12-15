package gamestates;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.LevelButtons;
import utilities.ImportExport;

public class LevelSelect extends State implements StateMethods{

    private BufferedImage background;
    private LevelButtons buttons;
    private BufferedImage logo;
    private int selectedLevel;

    public LevelSelect(Game game) {
        super(game);
        loadImages();
    }

    private void loadImages() {
        background = ImportExport.GetImage(ImportExport.BACKGROUNDMENU);
        buttons = new LevelButtons(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2);   
        logo = ImportExport.GetImage(ImportExport.LOGO);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(logo, (int)(Game.GAME_WIDTH/2 - logo.getWidth()/2), 70, null);
        buttons.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i<15;i++) {
            if (isIn(e, buttons.getBounds()[i])) {
                buttons.setMousePressed(i, true);
            }
        }
    }


    private boolean isIn(MouseEvent e, Rectangle rectangle) {
        return rectangle.contains(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i<15;i++) {
            if (isIn(e, buttons.getBounds()[i])) {
                if(buttons.isMousePressed()) {
                    Gamestate.level = i;
                    Gamestate.control = 1;
                    buttons.applyGamestate();
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (int i = 0; i<15;i++) {
            buttons.setMousePressed(i, false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

}
