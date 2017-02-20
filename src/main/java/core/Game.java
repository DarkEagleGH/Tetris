package core;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class Game {
    private Figure oldPosition;
    private Figure newPosition;
    private Figure pile;
    private Nigga nigga;
    private boolean running;

    enum Direction {LEFT, RIGHT, DOWN}

    Game() {
        oldPosition = new Figure();
        newPosition = new Figure();
        pile = new Figure();

        oldPosition.generateNew();
        running = true;
        nigga = new Nigga();
        nigga.execute();
    }

    synchronized void move(Direction direction) {
        if (!running) {
            return;
        }
        boolean pileChanged = false;
        newPosition.copyFrom(oldPosition);
        switch (direction) {
            case LEFT:
                if (!newPosition.moveLeft() || newPosition.isIntersect(pile)) {
                    return;
                }
                break;
            case RIGHT:
                if (!newPosition.moveRight() || newPosition.isIntersect(pile)) {
                    return;
                }
                break;
            case DOWN:
                if (!newPosition.moveDown() || newPosition.isIntersect(pile)) {
                    pile.addBricks(oldPosition);
                    pileChanged = pile.checkAndBurn(oldPosition);
                    if (pile.getBricks().get(pile.getBricks().size()-1).getPosY() > pile.getMaxY()) {
                        gameOver();
                        return;
                    }
                    newPosition.generateNew();
                }
                break;
        }

        boolean finalPileChanged = pileChanged;
        SwingUtilities.invokeLater(() -> {
            if (finalPileChanged) {
                Tetris.getRootFrame().getDrawPanel().reset();
            } else {
                Tetris.getRootFrame().getDrawPanel().eraseFigure(oldPosition);
            }
            Tetris.getRootFrame().getDrawPanel().drawFigure(newPosition);
            Tetris.getRootFrame().getDrawPanel().drawFigure(pile);
            oldPosition.copyFrom(newPosition);
        });

    }

    public void reset() {
        nigga.cancel(true);
        nigga = new Nigga();
        running = true;
        nigga.execute();
    }

    private void gameOver() {
        nigga.cancel(true);
        running = false;
        Tetris.getRootFrame().getDrawPanel().gameOver();
    }

    private class Nigga extends SwingWorker {
        @Override
        protected Object doInBackground() throws Exception {
            pile.getBricks().clear();
            oldPosition.generateNew();
            newPosition.getBricks().clear();

            SwingUtilities.invokeLater(() -> {
                Tetris.getRootFrame().getDrawPanel().reset();
                Tetris.getRootFrame().getDrawPanel().drawFigure(oldPosition);
            });
            sleep(1000);
            while (!isCancelled()) {
                move(Direction.DOWN);
                sleep(500);
            }
            return null;
        }
    }
}
