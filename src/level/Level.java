package level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Level{
    private float scale;
    private List<Gedung> Gedungs;
    private int LevelIndex;
    private boolean isBeaten;
    private int ScoreByUser;
    public int jumlahRuangan = 0;
    private int emptyCounter;

    public Level(int LevelIndex) {
        this.LevelIndex = LevelIndex;
        this.Gedungs = new ArrayList<Gedung>();
        this.isBeaten = false;
        this.ScoreByUser = 0;
        this.emptyCounter = 0;
    }

    public List<Gedung> getGedung() {
        return this.Gedungs;
    }

    public void resetState() {
        this.isBeaten = false;
        this.ScoreByUser = 0;
        this.emptyCounter = 0;
        for (Gedung gedung : Gedungs) {
            gedung.resetState();
        }
    }


    public void addGedung(Gedung gedung) {
        jumlahRuangan = jumlahRuangan + gedung.getTotalRoom();
        Gedungs.add(gedung);
    }

    public boolean isAllEmpty() {
        for (Gedung gedung : Gedungs) {
            emptyCounter = emptyCounter + gedung.countEmpty();
        }
        if (emptyCounter == jumlahRuangan) {
            return true;
        } else {
            return false;
        }
    }

    public int getLevelIndex() {
        return this.LevelIndex;
    }

    public void setLevelIndex(int LevelIndex) {
        this.LevelIndex = LevelIndex;
    }

    public boolean isIsBeaten() {
        return this.isBeaten;
    }

    public boolean getIsBeaten() {
        return this.isBeaten;
    }

    public void setIsBeaten(boolean isBeaten) {
        this.isBeaten = isBeaten;
    }

    public int getScoreByUser() {
        return this.ScoreByUser;
    }

    public void setScoreByUser(int ScoreByUser) {
        this.ScoreByUser = ScoreByUser;
    }

    public void update() {
        for (Gedung gedung : Gedungs) {
            gedung.update();
        }
    }

    public Ruangan getRuangan(int x, int y) {
        return Gedungs.get(x - 1).getRuangan(y - 1);
    }

    public boolean cekRuangan(int x, int y) {
        if (Gedungs.size() >= x) {
            if (Gedungs.get(x - 1).getTotalRoom() >= y) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void render(Graphics g) {
        for (Gedung gedung : Gedungs) {
            gedung.render(g);
        }
    }

}
