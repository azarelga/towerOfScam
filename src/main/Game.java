package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private Thread gameThread;
    private final int FPS = 60;

    public Game() {
        gameScreen = new GameScreen();
        gameWindow = new GameWindow(gameScreen);
        gameScreen.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();

		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {

			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				gameScreen.repaint();
				lastFrame = now;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

	}
}
