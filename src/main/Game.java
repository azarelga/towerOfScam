package main;

import java.awt.Graphics;

import audio.AudioPlayer;
import gamestates.Gamestate;
import gamestates.LevelSelect;
import gamestates.Menu;
import gamestates.Playing;
import gamestates.Settings;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GameScreen gameScreen;
	private Thread gameThread;
	private Playing playing;
	private Settings settings;
	private LevelSelect levelselect;
	private Menu menu;
	private AudioPlayer audioPlayer;

	public final static float SCALE = 2f;
	private final int FPS = 200;
	private final int UPS_SET = 280;
	public final static int GAME_WIDTH = 1280;
	public final static int GAME_HEIGHT = 720;

	public Game() {
		initClasses();
		gameScreen = new GameScreen(this);
		gameWindow = new GameWindow(gameScreen);
		gameScreen.requestFocus();
		startGameLoop();
	}

	public void update() {
		switch (Gamestate.state) {
			case MENU:
				menu.update();
				break;
			case PLAYING:
				playing.update();
				break;
			case LEVELSELECT:
				levelselect.update();
				break;
			case SETTINGS:
				settings.update();
				break;
			default:
				break;
		}
	}

	public void render(Graphics g) {
		switch (Gamestate.state) {
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
				break;
			case LEVELSELECT:
				levelselect.draw(g);
				break;
			case SETTINGS:
				settings.draw(g);
				break;
			default:
				break;
		}
	}

	public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING)
			playing.getJudol().resetDirection();
	}

	private void initClasses() {
		audioPlayer = new AudioPlayer();
		menu = new Menu(this);
		playing = new Playing(this);
		levelselect = new LevelSelect(this);
		settings = new Settings(this);
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

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public LevelSelect getLevelSelect() {
		return levelselect;
	}

	public Settings getSettings() {
		return settings;
	}

	public Graphics getGraphics() {
		return null;
	}

	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}
}
