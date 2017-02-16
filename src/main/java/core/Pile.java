package core;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Pile {
    private int pileWidth;
    private List<Brick> pile;

    Pile(int width) {
        this.pileWidth = width;
    }

    private void sortPile() {
        Collections.sort(pile);
    }

    void addFigure(List<Brick> figure) {

    }

    void addBrick(Brick brick) {

    }

    void checkAndDelete() {

    }
}
