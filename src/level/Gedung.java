package level;

import java.awt.Graphics;

public class Gedung {
    private int TotalRoom;
    private int Position;
    private Ruangan[] Ruangan;

    public int getTotalRoom() {
        return this.TotalRoom;
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

    public int getPosition() {
        return this.Position;
    }

    public void setPosition(int Position) {
        this.Position = Position;
    }

    public Ruangan getRuangan(int x) {
        return this.Ruangan[x];
    }

    public void setRuangan(Ruangan[] Ruangan) {
        this.Ruangan = Ruangan;
    }

    public Gedung(int TotalRoom, Ruangan[] Ruangan) {
        this.TotalRoom = TotalRoom;
        this.Position = 0;
        this.Ruangan = Ruangan;
    }

}
