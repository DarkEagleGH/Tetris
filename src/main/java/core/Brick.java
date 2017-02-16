package core;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Brick implements Comparable<Brick> {
    private int x;
    private int y;

    Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int compareTo(Brick brick) {
        if (x < brick.getX()) {
            return -1;
        } else if (x > brick.getX()) {
            return 1;
        } else {
            if (y < brick.getY()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
