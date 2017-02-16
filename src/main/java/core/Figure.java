package core;

import java.util.List;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Figure {
    private int maxX;
    private int maxY;
    private List<Brick> figure;
    
    void generateNew() {
        figure.clear();
        figure.add(new Brick(1, maxY));
        figure.add(new Brick(2, maxY));
        figure.add(new Brick(1, maxY-1));
        figure.add(new Brick(2, maxY-1));
    }
    
    void moveDown() {
        boolean fl = true;
        for (Brick brick: figure){
            if (brick.getY()-1 == 0) {
                fl = false;
                break;
            }
        }
        if (fl) {
            for (Brick brick: figure){
                brick.setY(brick.getY()-1);
            }
        }
    }

    void moveLeft() {
        boolean fl = true;
        for (Brick brick: figure){
            if (brick.getX()-1 == 0) {
                fl = false;
                break;
            }
        }
        if (fl) {
            for (Brick brick: figure){
                brick.setX(brick.getX()-1);
            }
        }
    }
    void moveRight() {
        boolean fl = true;
        for (Brick brick: figure){
            if (brick.getX()+1 == maxX) {
                fl = false;
                break;
            }
        }
        if (fl) {
            for (Brick brick: figure){
                brick.setX(brick.getX()+1);
            }
        }
    }
    void rotate() {
        
    }
}
