package utilities;

import level.Ruangan;

public class Constants {
	public static class GameConstants {
		public static final int X_START = 69 + Ruangan.roomWidth / 2;
		public static final int X_START_ROOM = 99;
		public static final int Y_START = 651;
		public static float ROOMSCALE = 0.5f;
		public static float HORIZONTALDISTANCE = 300.0f;
		public static float VERTICALDISTANCE = 40.0f;
		public static float SPEEDXCAMERA = 1.0f;
		public static void resetConstants() {
			HORIZONTALDISTANCE = 300.0f ;
			VERTICALDISTANCE = 40.0f;
		}
	}

	public static class Buttons {
		public static final float WIDEBUTTONSCALE = 0.173f;
	}

	public class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int RUNNING = 0;
		public static final int TELEPORTING = 1;
		public static final int IDLE = 2;
		public static final int DYING = 3;
		public static final int HIT = 4;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
				case IDLE:
					return 6;
				case TELEPORTING:
					return 10;
				case RUNNING:
					return 8;
				case DYING:
					return 9;
				default:
					return 1;
			}
		}
	}
}
