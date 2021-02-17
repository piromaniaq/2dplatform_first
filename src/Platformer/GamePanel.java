package Platformer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    private Player player;
    private List<Wall> walls = new ArrayList<>();
    private Timer gameTimer;

    public static final int BLOCK_SIZE = 50;
    private int cameraX;
    private int offset;
    private Rectangle restartRect;
    private Rectangle homeRect;
    private Font buttonFont = new Font("Arial",Font.BOLD,30);

    private WallGeneratorOrganizer wallGeneratorOrganizer = new WallGeneratorOrganizer(walls);

    public GamePanel() {

        restartRect = new Rectangle(550, 25, 50, 50);
        homeRect = new Rectangle(625, 25, 50, 50);
        player = new Player(400,300, this);
        reset();
        home();
        gameTimer = new Timer(true);
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(walls.get(walls.size() - 1).x<600){
                    offset += 700;
                    System.out.println(walls.size()); // number of blocks generated now
                    makeWalls(offset);
                }

                player.set();
                for(Wall wall : walls){
                    wall.set(cameraX);
                }

                List<Wall> wallsToDelete = new ArrayList<>();

                for (Wall wall : walls) {
                    if (wall.x < -800) {
                        wallsToDelete.add(wall);
                    }
                }

                walls.removeAll(wallsToDelete);

                repaint();
            }
        },0,1000 / 60);
    }

    public void reset(){
        player.x = 200;
        player.y = 150;
        cameraX = 150;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        offset = -150;
        makeWalls(offset);
    }

    public void home(){
        player.y = 100;
        player.yspeed = 0;
    }


    public void makeWalls(int offset) {
        wallGeneratorOrganizer.addWall(offset);
    }


    public void paint(Graphics g){

        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        for(Wall wall:walls) {
            wall.draw(gtd);
        }

        gtd.setColor(BLACK);
        gtd.drawRect(550, 25, 50, 50);
        gtd.drawRect(625, 25, 50, 50);
        gtd.setColor(WHITE);
        gtd.fillRect(551, 26, 48, 48);
        gtd.fillRect(626, 26, 48, 48);
        gtd.setColor(BLACK);
        gtd.setFont(buttonFont);
        gtd.drawString("R", 564, 60);
        gtd.drawString("H", 639, 60);
    }


    void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a') player.keyLeft = true;
        if(e.getKeyChar() == 'd') player.keyRight = true;
        if(e.getKeyChar() == 'w') player.keyUp = true;
        if(e.getKeyChar() == 's') player.keyDown = true;
        if(e.getKeyChar() == 'r') reset();
        if(e.getKeyChar() == 'h') home();
    }

    void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a') player.keyLeft = false;
        if(e.getKeyChar() == 'd') player.keyRight = false;
        if(e.getKeyChar() == 'w') player.keyUp = false;
        if(e.getKeyChar() == 's') player.keyDown = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        if(restartRect.contains(new Point(e.getPoint().x, e.getPoint().y - 27))) {
            reset();
        }
        if(homeRect.contains(new Point(e.getPoint().x, e.getPoint().y - 27))) {
            home();
        }
    }


    public List<Wall> getWalls() {
        return walls;
    }
    public void moveCameraX(double x) {
        cameraX += x;
    }
}