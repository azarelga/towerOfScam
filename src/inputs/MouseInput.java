package inputs;

import java.awt.event.MouseListener;

import gamestates.Gamestate;
import main.GameScreen;

import java.awt.event.MouseEvent;

public class MouseInput implements MouseListener {
    private GameScreen gameScreen;

    public MouseInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked at: " + e.getX() + ", " + e.getY() + "\n");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gameScreen.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gameScreen.getGame().getPlaying().mousePressed(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gameScreen.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gameScreen.getGame().getPlaying().mouseReleased(e);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
