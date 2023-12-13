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

public class ImportExport {
    public static final String JUDOL = "Judol.png";
    public static final String BACKGROUND = "Background.png";
    public static final String BACKGROUNDMENU = "Background_2.png";
    public static final String LOGOMENU = "logo_menu.png";
    public static final String[] RUANGAN = { "Ruangan_1.png", "Ruangan_2.png" };
    public static final String[] GAMEDEVS = { "GameDev_1.png", "GameDev_2.png", "GameDev_3.png" };
    public static final String[] BUFFS = { "buff1.png", "buff2.png", "buff3.png" };
    public static final String[] DEBUFFS = { "debuff1.png", "debuff2.png" };
    public static final String ROOFTOP = "Ruangan_3.png";
    public static final String LOGO = "Logo.png";
    public static final String RESUME = "resume.png";
    public static final String RESTART = "restart.png";
    public static final String PLAY = "play_button.png";
    public static final String LEVEL = "level_button.png";
    public static final String SETTINGS = "settings_button.png";
    public static final String PAUSE = "pause_click.png";
    public static final String HOME = "home_button.png";
    public static final String LEVELANGKA = "lvlangka.png";
    public static final String SOUND = "sound.png";
    public static final String MUTE = "mute.png";
    public static final String BGM = "bgm.png";
    public static final String CLEAR = "Level_Cleared.png";
    public static final String SFX = "sfx.png";
    public static final String NEXT = "next_button.png";

    public static BufferedImage GetImage(String filename) {
        BufferedImage img = null;
        InputStream is = ImportExport.class.getResourceAsStream("/" + filename);
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
                ROOMSCALE = 0.7f;
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
            default:
                break;
            // level 3
            // level 4
            // level 5
            // level 6
            // level 7
            // level 8
            // level 9
            // level 10
        }
        return level;
    }

}
