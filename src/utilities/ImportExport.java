package utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImportExport {
    public static final String PLAYER_ATLAS = "Judol.png";
    public static final String BACKGROUND = "Background.png";
    public static final String RUANGAN = "Ruangan_1.png";

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
