package level;

import java.awt.Graphics;

public class Gedung {
    private int TotalRoom;
    private Ruangan[] Ruangan;
    private int emptyCounter = 0;

    public int getTotalRoom() {
        return this.TotalRoom;
    }

    public Gedung(Ruangan[] Ruangan) {
        this.Ruangan = Ruangan;
        TotalRoom = Ruangan.length;
    }

    public void resetState() {
        for (Ruangan ruangan : Ruangan) {
            ruangan.resetState();
        }
    }
    
    public int countEmpty() {
        for (Ruangan ruangan : Ruangan) {
            if (!ruangan.getIsEmpty()) {
                emptyCounter++;
            }
        }
        return emptyCounter;
    }

    public void render(Graphics g) {
        for (Ruangan ruangan : Ruangan) {
            ruangan.render(g);
        }
    }
    public void update() {
        for (Ruangan ruangan : Ruangan) {
            ruangan.update();
        }
    }

    public void setTotalRoom(int TotalRoom) {
        this.TotalRoom = TotalRoom;
    }

    public Ruangan getRuangan(int x) {
        return this.Ruangan[x];
    }

    public void setRuangan(Ruangan[] Ruangan) {
        this.Ruangan = Ruangan;
    }


}
