package Platformer;

import java.awt.*;

public class Player {

    GamePanel panel;

    int x;
    int y;
    int width;
    int height;

    double xspeed;
    double yspeed;

    Rectangle hitBox;
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    public Player(int x, int y, GamePanel panel) {

        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50; //body of player
        height = 100;
        hitBox = new Rectangle(x, y, width, height);
        //Controls
    }

    public void set() {
        if (keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
        else if (keyLeft && !keyRight) {
            xspeed--;
        } else if (keyRight && !keyLeft) { //left or right
            xspeed++;
        }

        if (xspeed > 0 && xspeed < 0.75) {
            xspeed = 0;
        }
        if (xspeed < 0 && xspeed > -0.75) {
            xspeed = 0;
        }

        if (xspeed > 7) {
            xspeed = 7;
        }
        if (xspeed < -7) {
            xspeed = -7;
        }

        if (keyUp) {

            hitBox.y++;
            for (Wall wall : panel.getWalls()) {
                if (wall.hitBox.intersects(hitBox)) {
                    yspeed = -11;
                }
            }
            hitBox.y--;
        }
        yspeed += 0.5;
        // Collision X
        hitBox.x += xspeed;
        for (Wall wall : panel.getWalls()) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.x -= xspeed;
                while (!wall.hitBox.intersects(hitBox)) {
                    hitBox.x += Math.signum(xspeed);
                }
                hitBox.x -= Math.signum(xspeed);
                panel.moveCameraX(x - hitBox.x);
                xspeed = 0;
                hitBox.x = x;
            }
        }
        // Collision Y
        hitBox.y += yspeed;
        for (Wall wall : panel.getWalls()) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.y -= yspeed;
                while (!wall.hitBox.intersects(hitBox)) {
                    hitBox.y += Math.signum(yspeed);
                }
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }

        panel.moveCameraX(-xspeed);
        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;

        //Death
        if (y > 800) {
            panel.reset();
        }
    }


    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x, y, width, height);
    }
}