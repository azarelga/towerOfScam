package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private Thread gameThread;
    private final int FPS = 120;
	private final int UPS_SET = 200;

    public Game() {
        gameScreen = new GameScreen();
        gameWindow = new GameWindow(gameScreen);
        gameScreen.requestFocus();
        startGameLoop();
    }
	public void update() {
		gameScreen.updateGame();
	}

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		double deltaU = 0;
		double deltaF = 0;
		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gameScreen.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
}
