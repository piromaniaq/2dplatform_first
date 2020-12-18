package Platformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyCheck extends KeyAdapter {

    GamePanel panel;
    private KeyEvent e;

    public KeyCheck(GamePanel panel){
        this.panel = panel;
    }
    @Override
    public void keyPressed(KeyEvent e){
        panel.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e){
        panel.keyReleased(e);
    }
}
