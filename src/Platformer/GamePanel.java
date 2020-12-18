package Platformer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Player player;
    ArrayList<Wall> walls = new ArrayList<>();
    Timer gameTimer;

    int cameraX, offset;

    Rectangle restartRect;
    Rectangle homeRect;
    Font buttonFont = new Font("Arial",Font.BOLD,30);

    public GamePanel() {

        restartRect = new Rectangle(550,25,50,50);
        homeRect = new Rectangle(625, 25, 50, 50);

        player = new Player(400,300, this);
        reset();
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(walls.get(walls.size() - 1).x<600){
                    offset += 700;
                    System.out.println(walls.size()); // number of blocks generated now
                    makeWalls(offset);
                }

                player.set();
                for(Wall wall:walls) wall.set(cameraX);

                for(int z = 0; z <walls.size(); z++){
                    if(walls.get(z).x < -800) walls.remove(z);
                }

                repaint();
            }
        },0,17);
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

    public void makeWalls(int offset) {
        int s = 50;
        Random rand = new Random();
        int index = rand.nextInt(5);
        for (int z = 0; z <= 650; z += 50) {
            walls.add(new Wall(Color.MAGENTA,-150, z, s, s));
        }
        switch(index){
            case 0:
            walls.add(new Wall(Color.YELLOW,offset, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+2*s, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+3*s, 600-s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+3*s, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+3*s, 600+s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+4*s, 600-s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+8*s, 600-s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+8*s, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+8*s, 600+s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+8*s, 600+2*s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+12*s, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+12*s, 600+s, s, s));
            walls.add(new Wall(Color.YELLOW,offset+13*s, 600, s, s));
            walls.add(new Wall(Color.YELLOW,offset+13*s, 600+s, s, s));

            break;


            case 1:
            walls.add(new Wall(Color.RED,offset+2*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset + s, 600, s, s));
            walls.add(new Wall(Color.RED,offset + s, 600-s, s, s));
            walls.add(new Wall(Color.RED,offset+2*s, 600-s, s, s));
            walls.add(new Wall(Color.RED,offset+2*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset+2*s, 600+s, s, s));
            walls.add(new Wall(Color.RED,offset+2*s, 600-s, s, s));
            walls.add(new Wall(Color.RED,offset+3*s, 600-2*s, s, s));
            walls.add(new Wall(Color.RED,offset+4*s, 600-4*s, s, s));
            walls.add(new Wall(Color.RED,offset+7*s, 600-2*s, s, s));
            walls.add(new Wall(Color.RED,offset+10*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset+11*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset+11*s, 600-s, s, s));
            walls.add(new Wall(Color.RED,offset+12*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset+12*s, 600-s, s, s));
            walls.add(new Wall(Color.RED,offset+13*s, 600, s, s));
            walls.add(new Wall(Color.RED,offset+13*s, 600-s, s, s));

            break;

            case 2:
            walls.add(new Wall(Color.PINK,offset, 600-2*s, s, s));
            walls.add(new Wall(Color.PINK,offset+2*s, 600-s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 3*s, 600, s, s));
            walls.add(new Wall(Color.PINK,offset+ 4*s, 600-s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 5*s, 600-s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 6*s, 600-3*s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 8*s, 600-4*s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 9*s, 600-5*s, s, s));
            walls.add(new Wall(Color.PINK,offset+ 12*s, 600, s, s));
            walls.add(new Wall(Color.PINK,offset+ 13*s, 600, s, s));

            break;

            case 3:
                walls.add(new Wall(Color.ORANGE,offset, 600-2*s, s, s));
                walls.add(new Wall(Color.ORANGE,offset+2*s, 600, s, s));
                walls.add(new Wall(Color.ORANGE,offset+2*s, 600-s, s, s));
                walls.add(new Wall(Color.ORANGE,offset+3*s, 600-s, s, s));
                walls.add(new Wall(Color.ORANGE,offset+4*s, 600-2*s, s, s));
                walls.add(new Wall(Color.ORANGE,offset+9*s, 600, s, s));
                walls.add(new Wall(Color.ORANGE,offset+12*s, 600-s, s, s));
                walls.add(new Wall(Color.ORANGE,offset+13*s, 600, s, s));

                break;
            case 4:
                walls.add(new Wall(Color.BLUE,offset, 600-2*s, s, s));
                walls.add(new Wall(Color.BLUE, offset+s, 600-s,s,s));
                walls.add(new Wall(Color.BLUE, offset+3*s, 600-2*s,s,s));
                walls.add(new Wall(Color.BLUE, offset+4*s, 600-2*s,s,s));
                walls.add(new Wall(Color.BLUE, offset+5*s, 600-2*s,s,s));
                walls.add(new Wall(Color.BLUE, offset+9*s, 600-3*s,s,s));
                walls.add(new Wall(Color.BLUE, offset+12*s, 600,s,s));
                walls.add(new Wall(Color.BLUE, offset+13*s, 600,s,s));

                break;
        }
    }


    public void paint(Graphics g){

        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        for(Wall wall:walls) wall.draw(gtd);

        gtd.setColor(Color.BLACK);
        gtd.drawRect(550,25,50,50);
        gtd.drawRect(625,25,50,50);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(551,26,48,48);
        gtd.fillRect(626,26,48,48);
        gtd.setColor(Color.BLACK);
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
        if(restartRect.contains(new Point(e.getPoint().x, e.getPoint().y - 27))) reset();
    }
}
