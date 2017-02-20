package core;

import java.util.Comparator;

/**
 * Created by Tonk on 20.02.2017. **
 */
class CompareByY implements Comparator<Brick> {
    @Override
    public int compare(Brick o1, Brick o2) {
        if (o1.getPosY() < o2.getPosY()) {
            return -1;
        } else if (o1.getPosY() > o2.getPosY()) {
            return 1;
        } else {
            if (o1.getPosX() < o2.getPosX()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}