package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameScreen;

public class KeyboardInput implements KeyListener {

    private GameScreen gameScreen;
    
    public KeyboardInput(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gameScreen.judol.MoveJudol(0, -10);
                break;
            case KeyEvent.VK_S:
                gameScreen.judol.MoveJudol(0, 10);
                break;
            case KeyEvent.VK_A:
                gameScreen.judol.MoveJudol(-10, 0);
                break;
            case KeyEvent.VK_D:
                gameScreen.judol.MoveJudol(10, 0);
                break;
            case KeyEvent.VK_UP:
                gameScreen.judol.MoveJudol(0, -10);
                break;
            case KeyEvent.VK_DOWN:
                gameScreen.judol.MoveJudol(0, 10);
                break;
            case KeyEvent.VK_LEFT:
                gameScreen.judol.MoveJudol(-10,0 );
                break;
            case KeyEvent.VK_RIGHT:
                gameScreen.judol.MoveJudol(10,0);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO: Implement your code here
        
    }
}
