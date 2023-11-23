package main;

import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    private GameScreen screen;

    public GameWindow(){
        setTitle("Tower of Scam");
        setSize(960,540);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(java.awt.Color.BLACK);
        setLocationRelativeTo(null);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                int W = 4;  
                int H = 3;  
                Rectangle b = event.getComponent().getBounds();
                event.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);
            }
        });
        screen = new GameScreen();
        add(screen);
        setVisible(true);
    }
}
