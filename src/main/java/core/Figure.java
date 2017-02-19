package core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Figure {
    private int maxX;
    private int maxY;

    private List<Brick> bricks;

    public List<Brick> getBricks() {
        return bricks;
    }

    Figure() {
        System.out.println("Figure " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
        this.maxX = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.x"));
        this.maxY = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.y"));
        this.bricks = new LinkedList<Brick>();
    }

    void generateNew() {
        if (bricks != null) {
            bricks.clear();
        }
        bricks.add(new Brick(3, maxY));
        bricks.add(new Brick(4, maxY));
        bricks.add(new Brick(5, maxY));
        bricks.add(new Brick(3, maxY - 1));
        bricks.add(new Brick(4, maxY - 2));
    }

    boolean moveDown() {
        for (Brick brick : bricks) {
            if (brick.getPosY() - 1 == 0) {
                return false;
            }
        }
        for (Brick brick : bricks) {
            brick.setPosY(brick.getPosY() - 1);
        }
        return true;
    }

    boolean moveLeft() {
        for (Brick brick : bricks) {
            if (brick.getPosX() - 1 == 0) {
                return false;
            }
        }
        for (Brick brick : bricks) {
            brick.setPosX(brick.getPosX() - 1);
        }
        return true;
    }

    boolean moveRight() {
        for (Brick brick : bricks) {
            if (brick.getPosX() + 1 == maxX + 1) {
                return false;
            }
        }
        for (Brick brick : bricks) {
            brick.setPosX(brick.getPosX() + 1);
        }
        return true;
    }

    public void rotate() {

    }

    boolean isIntersect(Figure pile) {
        for (Brick brick: this.bricks){
            for (Brick pileBrick: pile.getBricks()){
                if (brick.getPosX() == pileBrick.getPosX() && brick.getPosY() == pileBrick.getPosY()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (bricks.size() == 0) {
            return "";
        }
        for (Brick brick : bricks) {
            sb.append(brick.getPosX()).append("-");
            sb.append(brick.getPosY()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    void copyFrom(Figure figure) {
        this.bricks.clear();
        for (Brick brick : figure.getBricks()) {
            this.bricks.add(new Brick(brick.getPosX(), brick.getPosY()));
        }
    }

    void addBricks(Figure figure) {
        for (Brick brick : figure.getBricks()) {
            this.bricks.add(new Brick(brick.getPosX(), brick.getPosY()));
            Collections.sort(this.bricks);
        }
    }
}
