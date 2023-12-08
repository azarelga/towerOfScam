package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import gamestates.Gamestate;
import main.GameScreen;

public class KeyboardInput implements KeyListener {
    private Map<Integer, Boolean> pressedKeys = new HashMap<>();

    private GameScreen gameScreen;

    public KeyboardInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gameScreen.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gameScreen.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), false);
    }
}
