package inputs;

import static utilities.Constants.Directions.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameScreen;

public class KeyboardInput implements KeyListener {

    private GameScreen gameScreen;

    public KeyboardInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                gameScreen.setDirection(UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                gameScreen.setDirection(DOWN);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                gameScreen.setDirection(LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                gameScreen.setDirection(RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
        // gameScreen.setJumping(false);
        // gameScreen.setMoving(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                gameScreen.setJumping(false);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                gameScreen.setMoving(false);
                break;
        }

    }
}
