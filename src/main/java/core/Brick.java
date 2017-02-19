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

    public int getPosX() {
        return x;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public int getPosY() {
        return y;
    }

    public void setPosY(int y) {
        this.y = y;
    }

    public int compareTo(Brick brick) {
        if (x < brick.getPosX()) {
            return -1;
        } else if (x > brick.getPosX()) {
            return 1;
        } else {
            if (y < brick.getPosY()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
