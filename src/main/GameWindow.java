package main;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GameScreen screens){
        jframe = new JFrame();
        jframe.setTitle("Tower of Scam");
        jframe.setResizable(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(screens);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                screens.getGame().windowFocusLost();
            }
        });
        jframe.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                int W = 140;  
                int H = 100;  
                int width = jframe.getWidth();
                int height = width * H / W;
                jframe.setSize(width, height);
            }
        });
    }
}
