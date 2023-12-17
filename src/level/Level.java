package level;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilities.Constants.GameConstants.ROOMSCALE;
import static utilities.Constants.GameConstants.X_START_ROOM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import characters.Judol;
import utilities.ImportExport;

public class Level {
    // private float scale;
    private Judol judol;
    private List<Gedung> Gedungs;
    private int LevelIndex;
    private boolean isBeaten;
    private int ScoreByUser;
    private boolean win = false, lose = false;
    public int jumlahRuangan = 0;
    private int emptyCounter;
    private int startEnergy;

    public Level(int LevelIndex, int startEnergy) {
        X_START_ROOM = (int) (X_START_ROOM * ROOMSCALE);
        this.LevelIndex = LevelIndex;
        this.Gedungs = new ArrayList<Gedung>();
        this.jumlahRuangan = 0;
        this.startEnergy = startEnergy;
        this.judol = new Judol(LevelIndex, startEnergy);
        this.isBeaten = false;
        this.ScoreByUser = 0;
        this.emptyCounter = 0;
    }

    private void checkJudolAndRoom() {
        int judolX = judol.getxIndex();
        int judolY = judol.getyIndex();
        if (getRuangan(judolX, judolY).getIsEmpty() == false) {
            if (getRuangan(judolX, judolY).getType() == Ruangan.GAMEDEV) {
                if (judol.getEnergy() > getRuangan(judolX, judolY).getEnergy()) {
                    judol.receiveEnergy(getRuangan(judolX, judolY).getEnergy());
                    Gedungs.get(judolX-1).setEmpty(judolY-1);
                } else {
                    if (!judol.isTeleporting()) setLose(true);
                }
            } else if (getRuangan(judolX, judolY).getType() == Ruangan.ITEM) {
                if (judol.CastItem(getRuangan(judolX, judolY)) <= 0 && !judol.isTeleporting()) setLose(true);
                else if (judol.CastItem(getRuangan(judolX, judolY)) > 0 && !judol.isTeleporting()){
                    Gedungs.get(judolX-1).setEmpty(judolY-1);
                    judol.setEnergy(judol.CastItem(getRuangan(judolX, judolY)));
                }
            }
        }
        if (getRuangan(judolX, judolY).getFinalRoom() == true) {
            if (isAllEmpty()) setWin();
        }

    }

    public boolean getLose() {
        return this.lose;
    }


    public boolean setWin() {
        this.isBeaten = true;
        this.ScoreByUser = judol.getEnergy();
        return true;
    }


    public void resetState() {
        this.judol.energy = this.startEnergy;
		this.judol.resetPosition();
        this.isBeaten = false;
        this.ScoreByUser = 0;
        this.emptyCounter = 0;
        this.lose = false;
        for (Gedung gedung : Gedungs) {
            gedung.resetState();
        }
    }

    public void addGedung(Gedung gedung) {
        jumlahRuangan = jumlahRuangan + gedung.getTotalRoom();
        Gedungs.add(gedung);
    }

    public boolean isAllEmpty() {
        emptyCounter = 0;
        for (Gedung gedung : Gedungs) {
            emptyCounter += gedung.countEmpty();
        }
        System.out.println(emptyCounter);
        System.out.println(jumlahRuangan +"Harusnya");
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
        if (!win && !lose) {
            for (Gedung gedung : Gedungs) {
                gedung.update();
            }
            judol.update();
            checkJudolAndRoom();
        }
    }

    public Ruangan getRuangan(int x, int y) {
        return Gedungs.get(x - 1).getRuangan(y - 1);
    }

    public boolean cekRuangan(int x, int y) {
        if (x != 0 && y != 0) {
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
        return false;
    }

    public Judol getJudol() {
        return judol;
    }

    public void render(Graphics g) {
        for (Gedung gedung : Gedungs) {
            gedung.render(g);
        }
        judol.render(g);
    }

    public void setLose(boolean b) {
        this.lose = b;
    }

}
