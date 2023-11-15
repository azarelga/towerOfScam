package com.towerofscam.level;

import java.util.List;

public class Level {
    private List<Gedung> Gedung;
    private int LevelIndex;
    private boolean isBeaten;
    private int ScoreByUser;

    public Level(List<Gedung> Gedung, int LevelIndex) {
        this.Gedung = Gedung;
        this.LevelIndex = LevelIndex;
        this.isBeaten = false;
        this.ScoreByUser = 0;
    }

    public List<Gedung> getGedung() {
        return this.Gedung;
    }

    public void setGedung(List<Gedung> Gedung) {
        this.Gedung = Gedung;
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

}
