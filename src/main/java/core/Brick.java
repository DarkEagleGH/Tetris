package core;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class Brick {
    private int x;
    private int y;

    Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPosX() {
        return x;
    }

    void setPosX(int x) {
        this.x = x;
    }

    public int getPosY() {
        return y;
    }

    void setPosY(int y) {
        this.y = y;
    }
}
