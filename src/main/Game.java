package main;

public class Game {
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    public Game() {
        gameWindow = new GameWindow();
        gameScreen = new GameScreen();
        gameScreen.requestFocus();
        gameWindow.add(gameScreen);
    }
    
}
