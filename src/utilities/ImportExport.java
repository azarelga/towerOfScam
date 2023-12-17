package utilities;

import static utilities.Constants.GameConstants.ROOMSCALE;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import atribut.GameDev;
import atribut.Item;
import level.Gedung;
import level.Level;
import level.Ruangan;
import main.Game;

public class ImportExport {
	public static final String JUDOL = "Judol.png";
	public static final String BACKGROUND = "Background.png";
	public static final String BACKGROUNDMENU = "Background_2.png";
	public static final String LOGOMENU = "Logo_menu.png";
	public static final String[] RUANGAN = { "Ruangan_1.png", "Ruangan_2.png" };
	public static final String[] GAMEDEVS = { "GameDev_1.png", "GameDev_2.png", "GameDev_3.png" };
	public static final String[] BUFFS = { "buff1.png", "buff2.png", "buff3.png" };
	public static final String[] DEBUFFS = { "Debuff1.png", "debuff2.png" };
	public static final String ROOFTOP = "Ruangan_3.png";
	public static final String LOGO = "logo.png";
	public static final String RESUME = "Resume.png";
	public static final String RESTART = "Restart.png";
	public static final String PLAY = "Play_button.png";
	public static final String LEVEL = "Level_Button.png";
	public static final String SETTINGS = "settings_Button.png";
	public static final String PAUSE = "Pause_click.png";
	public static final String HOME = "Home_button.png";
	public static final String LEVELANGKA = "lvlangka.png";
	public static final String SOUND = "Sound.png";
	public static final String MUTE = "Mute.png";
	public static final String BGM = "BGM.png";
	public static final String CLEAR = "Level_Cleared.png";
	public static final String SFX = "SFX.png";
	public static final String NEXT = "Next_button.png";
	public static final String LOSE = "lose.png";
	public static final String LOADING = "Loading.png";
	public static final String FINALROOM = "finalroom.png";

	public static BufferedImage GetImage(String filename) {
		BufferedImage img = null;
		InputStream is = ImportExport.class.getResourceAsStream("/" + filename);
		System.out.println(filename);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static Level buildLevels(int i) {
		Level level = null;
		switch (i) {
			case 0:
				// level 1
				ROOMSCALE = 0.85f;
				level = new Level(i, 5);
				System.out.println("Level 1 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false) }));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 5, '+'),
						new GameDev(2, 2, 3),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 1, '-'),
						new GameDev(3, 2, 12)
				}));
				level.addGedung(new Gedung(new Ruangan[] { new Ruangan(4, 1, true) }));
				break;
			case 1:
				// level 2
				ROOMSCALE = 0.85f;
				level = new Level(i, 4);
				System.out.println("Level 2 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(2, 1, false),
						new Item(2, 2, 2, '*'),
						new GameDev(2, 3, 6),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(3, 1, false),
						new GameDev(3, 2, 12),
						new GameDev(3, 3, 24)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(4, 1, true)
				}));
				break;
			case 2:
				// level 3
				ROOMSCALE = 0.85f;
				level = new Level(i, 10);
				System.out.println("Level 3 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 2, '/'),
						new Item(2, 2, 2, '-'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 10, '+'),
						new Item(3, 2, 2, '*'),
						new GameDev(3, 3, 10)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(4, 1, true)
				}));
			default:
				break;
			case 3:
				// level 4
				ROOMSCALE = 0.7f;
				level = new Level(i, 10);
				System.out.println("Level 4 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 5, '*'),
						new GameDev(2, 2, 38),
						new Item(2, 3, 16, '-')

				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 81),
						new Item(3, 2, 2, '*'),
						new GameDev(3, 3, 56)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 200)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 4:
				// level 5
				ROOMSCALE = 0.7f;
				level = new Level(i, 10);
				System.out.println("Level 5 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 9),
						new Item(2, 2, 5, '+'),
						new GameDev(2, 3, 13),
						new Item(2, 4, 15, '-')

				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 25),
						new Item(3, 2, 2, '/'),
						new GameDev(3, 3, 30),
						new GameDev(3, 4, 12)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 79)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 5:
				// level 6
				ROOMSCALE = 0.65f;
				level = new Level(i, 10);
				System.out.println("Level 6 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(2, 1, false),
						new Item(2, 2, 20, '+'),
						new GameDev(2, 3, 24),
						new Item(2, 4, 3, '*'),
						new Item(2, 5, 50, '-')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 16),
						new Item(3, 2, 5, '*'),
						new Item(3, 3, 2, '/'),
						new GameDev(3, 4, 5),
						new GameDev(3, 5, 32)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 499)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 6:
				// level 7
				ROOMSCALE = 0.65f;
				level = new Level(i, 10);
				System.out.println("Level 7 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 7),
						new GameDev(2, 2, 15),
						new GameDev(2, 3, 24),
						new Item(2, 4, 2, '/'),
						new Item(2, 5, 50, '+')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 380),
						new GameDev(3, 2, 102),
						new Item(3, 3, 2, '/'),
						new GameDev(3, 4, 183),
						new GameDev(3, 5, 52)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 368)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 7:
				// level 8
				ROOMSCALE = 0.68f;
				level = new Level(i, 20);
				System.out.println("Level 8 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 18),
						new GameDev(2, 2, 32)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 320),
						new Item(3, 2, 5, '*'),
						new Item(3, 3, 3, '/')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 217),
						new GameDev(4, 2, 400)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 8:
				// level 9
				ROOMSCALE = 0.68f;
				level = new Level(i, 10);
				System.out.println("Level 9 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 9),
						new GameDev(2, 2, 5),
						new GameDev(2, 3, 16)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 22),
						new GameDev(3, 2, 19),
						new Item(3, 3, 2, '/')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(4, 1, 50),
						new Item(4, 2, 2, '*'),
						new GameDev(4, 3, 178)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(5, 1, true)
				}));
				break;
			case 9:
				// level 10
				ROOMSCALE = 0.6f;
				level = new Level(i, 10);
				System.out.println("Level 10 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 6, '-'),
						new Item(2, 2, 2, '/'),
						new Item(2, 3, 30, '+')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(3, 1, 13),
						new GameDev(3, 2, 75),
						new GameDev(3, 3, 31)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 3, '*'),
						new Item(4, 2, 30, '-'),
						new Item(4, 3, 2, '/')
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(5, 1, 200)
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(6, 1, true)
				}));
				break;
			case 10:
				// level 11
				ROOMSCALE = 0.65f;
				level = new Level(i, 10);
				System.out.println("Level 11 Entered");
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 9),
						new Item(2, 2, 8, '+'),
						new GameDev(2, 3, 13),
						new Ruangan(2, 4, false),
						new Item(2, 5, 3, '/')
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 2, '/'),
						new GameDev(3, 2, 7),
						new GameDev(3, 3, 17),
						new Item(3, 4, 23, '-'),
						new GameDev(3, 5, 70)

				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 2, '*'),
						new GameDev(4, 2, 71),
						new GameDev(4, 3, 34)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(5, 1, 500),
						new Item(5, 2, 40, '+'),
						new Item(5, 3, 2, '/'),
						new GameDev(5, 4, 500)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(6, 1, true)
				}));
				break;
			case 11:
				// level 12
				ROOMSCALE = 0.53f;
				level = new Level(i, 7);
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 6),
						new Item(2, 2, 30, '+'),
						new GameDev(2, 3, 40),
						new Item(2, 4, 3, '*'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 4, '*'),
						new Item(3, 2, 100, '+'),
						new GameDev(3, 3, 70),
						new GameDev(3, 4, 80),
						new Item(3, 5, 10000, '-'),
						new GameDev(3, 6, 1000),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 2, '/'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(5, 1, 400)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(6, 1, 1000, '-'),
						new Item(6, 2, 8, '*')
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(7, 1, true)
				}));
				break;
			case 12:
				// level 13
				ROOMSCALE = 0.53f;
				level = new Level(i, 10);
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 10, '+'),
						new Item(2, 2, 5, '*'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 2, '/'),
						new Item(3, 2, 70, '+'),
						new GameDev(3, 3, 200),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 4, '/'),
						new Ruangan(4, 2, false),
						new GameDev(4, 3, 300),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(5, 1, 200)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(6, 1, true)
				}));
				break;
			case 13:
				// level 14
				ROOMSCALE = 0.5f;
				level = new Level(i, 1);
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(2, 1, 20, '+'),
						new Item(2, 2, 2, '/'),
						new GameDev(2, 3, 300),
						new Item(2, 4, 4, '/'),
						new Item(2, 5, 150, '-'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 70, '+'),
						new GameDev(3, 2, 60),
						new Item(3, 3, 4, '*'),
						new Item(3, 4, 40000, '-'),
						new GameDev(3, 5, 300)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 100, '+'),
						new GameDev(4, 2, 40),
						new GameDev(4, 3, 70),
						new Item(4, 4, 100, '+'),
						new Item(4, 5, 3, '/'),
				}));
				level.addGedung(new Gedung(
						new Ruangan[] {
								new GameDev(5, 1, 30),
								new GameDev(5, 2, 100),
								new GameDev(5, 3, 70),
								new Item(5, 4, 6, '/'),
								new Item(5, 5, 2, '*'),

						}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(6, 1, 4, '/'),
						new Item(6, 2, 4, '*'),
						new Item(6, 3, 60, '+'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(7, 1, 30000)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(8, 1, true)
				}));
				break;
			case 14:
				ROOMSCALE = 0.4f;
				level = new Level(i, 20);
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(1, 1, false)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(2, 1, 15),
						new Item(2, 2, 2, '/'),
						new Item(2, 3, 6, '*'),
						new Item(2, 4, 700,'-'),
						new GameDev(2, 5, 300),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(3, 1, 800, '-'),
						new Item(3, 2, 200, '+'),
						new Item(3, 3, 2, '/'),
						new GameDev(3, 4, 50),
						new Item(3, 5, 3,'*')
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(4, 1, 240, '+'),
						new Item(4, 2, 200, '-'),
						new GameDev(4, 3, 500),
						new Item(4, 4, 2, '/'),
						new GameDev(4, 5, 1000),
				}));
				level.addGedung(new Gedung(
						new Ruangan[] {
								new GameDev(5, 1, 30),
								new Item(5, 2, 6,'/'),
								new Item(5, 3, 500, '-'),
								new Item(5, 4, 100, '+'),
								new GameDev(5, 5, 700),

						}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(6, 1, 100),
						new Item(6, 2, 400, '-'),
						new GameDev(6, 3, 70),
						new Item(6, 4, 3, '/'),
						new Item(6, 5, 500, '+'),
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Item(7, 1, 4,'/'),
						new Item(7, 2, 40,'+'),
						new GameDev(7, 3, 100),
						new Item(7, 4, 400,'-'),
						new Item(7, 5, 4,'/'),
				}));

				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(8, 1, 50000)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new GameDev(9, 1, 7000)
				}));
				level.addGedung(new Gedung(new Ruangan[] {
						new Ruangan(10, 1, true)
				}));
				break;
		}
		return level;
	}

}
