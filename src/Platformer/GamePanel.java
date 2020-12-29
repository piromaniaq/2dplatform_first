package Platformer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Player player;
    List<Wall> walls = new ArrayList<>();
    Timer gameTimer;

    int BLOCK_SIZE = 50;
    int cameraX;
    int offset;
    Rectangle restartRect;
    Rectangle homeRect;
    Font buttonFont = new Font("Arial",Font.BOLD,30);

    public GamePanel() {

        restartRect = new Rectangle(550,25,50,50);
        homeRect = new Rectangle(625, 25, 50, 50);

        player = new Player(400,300, this);
        reset();
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

    private void addWall(Color c,int x, int y){
        walls.add(new Wall(c,x,y,BLOCK_SIZE,BLOCK_SIZE));
    }

    private interface WallGenerator {
        void generateWalls();
    }

    public void makeWalls(int offset) {
        int Y_COMMON = 600;
        Random rand = new Random();
        List<WallGenerator>  wallGeneratorList = new ArrayList<WallGenerator>();

        wallGeneratorList.add(() -> {
            addWall(YELLOW,offset,Y_COMMON);
            addWall(YELLOW,offset+2* BLOCK_SIZE,Y_COMMON);
            addWall(YELLOW,offset+3* BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(YELLOW,offset+3* BLOCK_SIZE,Y_COMMON);
            addWall(YELLOW,offset+3* BLOCK_SIZE,Y_COMMON + BLOCK_SIZE);
            addWall(YELLOW,offset+4* BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(YELLOW,offset+8* BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(YELLOW,offset+8* BLOCK_SIZE,Y_COMMON);
            addWall(YELLOW,offset+8* BLOCK_SIZE,Y_COMMON + BLOCK_SIZE);
            addWall(YELLOW,offset+8* BLOCK_SIZE,Y_COMMON + 2 * BLOCK_SIZE);
            addWall(YELLOW,offset+12* BLOCK_SIZE,Y_COMMON);
            addWall(YELLOW,offset+12* BLOCK_SIZE,Y_COMMON + BLOCK_SIZE);
            addWall(YELLOW,offset+13* BLOCK_SIZE,Y_COMMON);
            addWall(YELLOW,offset+13* BLOCK_SIZE,Y_COMMON + BLOCK_SIZE);
        });

        wallGeneratorList.add(() -> {
            addWall(RED,offset + 2 * BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(RED,offset + 2 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(RED,offset + 2 * BLOCK_SIZE,Y_COMMON + BLOCK_SIZE);
            addWall(RED,offset + 2 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(RED,offset + 3 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(RED,offset + 4 * BLOCK_SIZE,Y_COMMON - 4 * BLOCK_SIZE);
            addWall(RED,offset + 7 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(RED,offset + 10 * BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + 11 * BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + 11 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(RED,offset + 12 * BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + 12 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(RED,offset + 13 * BLOCK_SIZE,Y_COMMON);
            addWall(RED,offset + 13 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
        });

        wallGeneratorList.add(() -> {
            addWall(PINK,offset ,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(PINK,offset + 2 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(PINK,offset + 3 * BLOCK_SIZE,Y_COMMON);
            addWall(PINK,offset + 4 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(PINK,offset + 5 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(PINK,offset + 6 * BLOCK_SIZE,Y_COMMON - 3 * BLOCK_SIZE);
            addWall(PINK,offset + 8 * BLOCK_SIZE,Y_COMMON - 4 * BLOCK_SIZE);
            addWall(PINK,offset + 9 * BLOCK_SIZE,Y_COMMON - 5 * BLOCK_SIZE);
            addWall(PINK,offset + 12 * BLOCK_SIZE,Y_COMMON);
            addWall(PINK,offset + 13 * BLOCK_SIZE,Y_COMMON);
        });

        wallGeneratorList.add(() -> {
            addWall(ORANGE,offset ,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(ORANGE,offset + 2 * BLOCK_SIZE ,Y_COMMON);
            addWall(ORANGE,offset + 2 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(ORANGE,offset + 3 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(ORANGE,offset + 4 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(ORANGE,offset + 9 * BLOCK_SIZE,Y_COMMON);
            addWall(ORANGE,offset + 12 * BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(ORANGE,offset + 13 * BLOCK_SIZE,Y_COMMON);

        });

        wallGeneratorList.add(() -> {
            addWall(BLUE,offset ,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(BLUE,offset + BLOCK_SIZE,Y_COMMON - BLOCK_SIZE);
            addWall(BLUE,offset + 3 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(BLUE,offset + 4 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(BLUE,offset + 5 * BLOCK_SIZE,Y_COMMON - 2 * BLOCK_SIZE);
            addWall(BLUE,offset + 9 * BLOCK_SIZE,Y_COMMON - 3 * BLOCK_SIZE);
            addWall(BLUE,offset + 12 * BLOCK_SIZE,Y_COMMON);
            addWall(BLUE,offset + 13 * BLOCK_SIZE,Y_COMMON);
        });
        int index = rand.nextInt(wallGeneratorList.size());
        wallGeneratorList.get(index).generateWalls();
        }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        for(Wall wall:walls) {
            wall.draw(gtd);
        }

        gtd.setColor(BLACK);
        gtd.drawRect(550,25,50,50);
        gtd.drawRect(625,25,50,50);
        gtd.setColor(WHITE);
        gtd.fillRect(551,26,48,48);
        gtd.fillRect(626,26,48,48);
        gtd.setColor(BLACK);
        gtd.setFont(buttonFont);
        gtd.drawString("R",564,60);
        gtd.drawString("H",639,60);
    }


    void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a') player.keyLeft = true;
        if(e.getKeyChar() == 'd') player.keyRight = true;
        if(e.getKeyChar() == 'w') player.keyUp = true;
        if(e.getKeyChar() == 's') player.keyDown = true;
        if(e.getKeyChar() == 'r') reset();
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
    }
}
