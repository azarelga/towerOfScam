package utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImportExport {
    public static final String PLAYER_ATLAS = "Judol.png";
    public static final String BACKGROUND = "Background.png";
    public static final String BACKGROUNDMENU = "Background_2.png";
    public static final String LOGOMENU = "logo_menu.png";
    public static final String[] RUANGAN = {"Ruangan_1.png", "Ruangan_2.png"};
    public static final String ROOFTOP = "Ruangan_3.png";
    public static final String LOGO = "Logo.png";
    public static final String RESUME = "resume_button.png";
    public static final String PLAY = "play_button.png";
    public static final String LEVEL = "level_button.png";
    public static final String[] GAMEDEVS = {"GameDev_1.png", "GameDev_2.png","GameDev_3.png"};
    public static final String SETTINGS = "settings_button.png";
    public static final String PAUSE = "pause_button.png";
    public static final String HOME = "home_button.png";

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

}
