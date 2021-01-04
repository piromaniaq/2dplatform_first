package Platformer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Platformer.GamePanel.BLOCK_SIZE;
import static java.awt.Color.*;
import static java.awt.Color.BLUE;

public class WallGeneratorOrganizer {
    private List<Wall> walls;

    public WallGeneratorOrganizer(List<Wall> walls){
        this.walls = walls;
    }

    private void addWall(Color c, int x, int y){
        walls.add(new Wall(c,x,y,BLOCK_SIZE,BLOCK_SIZE));
    }

    public void addWall(int offset) {
        Random rand = new Random();
        int Y_COMMON = 600;
        List<WallGenerator>  wallGeneratorList = new ArrayList<>();

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

    private interface WallGenerator {
        void generateWalls();
    }
}
