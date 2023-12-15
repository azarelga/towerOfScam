package level;

import java.awt.Graphics;

public class Gedung {
    private int TotalRoom;
    private Ruangan[] Ruangan;
    private int emptyCounterBegin = 0, emptyCounter=0;

    public Gedung(Ruangan[] Ruangan) {
        this.Ruangan = Ruangan;
        this.TotalRoom = Ruangan.length;
        for (Ruangan ruangan : Ruangan) {
            if (ruangan.getIsEmpty() == true) {
                emptyCounter++;
            }
        }
        emptyCounterBegin = emptyCounter;
    }

    public int getTotalRoom() {
        return this.TotalRoom;
    }

    public void resetState() {
        emptyCounter = emptyCounterBegin;
        for (Ruangan ruangan : Ruangan) {
            ruangan.resetState();
        }
    }
    
    public void setEmpty(int y) {
        Ruangan[y].setIsEmpty(true);
        emptyCounter++;
    }

    public int countEmpty() {
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
