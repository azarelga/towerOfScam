package com.towerofscam.level;

import java.util.List;

public class Gedung {
    private int TotalRoom;
    private int Position;
    private List<Ruangan> Ruangan;

    public int getTotalRoom() {
        return this.TotalRoom;
    }

    public void setTotalRoom(int TotalRoom) {
        this.TotalRoom = TotalRoom;
    }

    public int getPosition() {
        return this.Position;
    }

    public void setPosition(int Position) {
        this.Position = Position;
    }

    public List<Ruangan> getRuangan() {
        return this.Ruangan;
    }

    public void setRuangan(List<Ruangan> Ruangan) {
        this.Ruangan = Ruangan;
    }

    public Gedung(int TotalRoom, List<Ruangan> Ruangan) {
        this.TotalRoom = TotalRoom;
        this.Position = 0;
        this.Ruangan = Ruangan;
    }
    
    
}
