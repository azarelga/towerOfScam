package utilities;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Constants {
	public static class GameConstants {
		public static final int X_OFFSET = 99;
		public static final int Y_OFFSET = 552;
	}
    public class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int RUNNING = 0;
		public static final int JUMP = 1;
		public static final int IDLE = 2;
		public static final int DYING = 3;
		public static final int HIT = 4;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case IDLE:
				return 6;
			case JUMP:
                return 5;
			case RUNNING:
				return 8;
			default:
				return 1;
			}
		}
	}
}
