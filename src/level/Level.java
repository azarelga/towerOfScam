package level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import characters.Judol;

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

    public Level(int LevelIndex, int startEnergy) {
        this.LevelIndex = LevelIndex;
        this.Gedungs = new ArrayList<Gedung>();
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
                if (judol.GetEnergy() > getRuangan(judolX, judolY).getEnergy()) {
                    judol.ReceiveEnergy(getRuangan(judolX, judolY).getEnergy());
                    getRuangan(judolX, judolY).setIsEmpty(true);
                } else {
                    // setLose();
                }
            } else if (getRuangan(judolX, judolY).getType() == Ruangan.ITEM) {
                judol.CastItem(getRuangan(judolX, judolY));
                getRuangan(judolX, judolY).setIsEmpty(true);
            }
        }
        if (getRuangan(judolX, judolY).getFinalRoom() == true) {
            if (isAllEmpty()) setWin();
        }

    }

    // private void setLose() {

    // }


    public boolean setWin() {
        this.isBeaten = true;
        this.ScoreByUser = judol.GetEnergy();
        return true;
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
        emptyCounter = 0;
        for (Gedung gedung : Gedungs) {
            emptyCounter = emptyCounter + gedung.countEmpty();
        }
        System.out.printf("yang kosong: %d, banyak: %d", emptyCounter,jumlahRuangan);
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
        if (!win || !lose) {
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

}
