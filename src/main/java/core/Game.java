package core;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Game {
    private Figure oldPosition;
    private Figure newPosition;
    private Figure pile;
    private int countX;
    private int countY;
    private int top;
    private boolean runFl;

    public enum Direction {LEFT, RIGHT, DOWN}

    public Game() {
        System.out.println("Game " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
        System.out.println("Game construct---");
        this.countX = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.x"));
        this.countY = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.y"));
        this.top = Integer.parseInt(Tetris.getSettings().getProperty("cup.head"));
        oldPosition = new Figure();
        newPosition = new Figure();
        pile = new Figure();
        runFl = true;
        oldPosition.generateNew();

        process();

    }

    private void process() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Tetris.getRootFrame().getDrawPanel().drawFigure(oldPosition);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }





    }

    public synchronized void move(Direction direction) {
        System.out.println("move " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
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
                    newPosition.generateNew();
                }
                break;
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tetris.getRootFrame().getDrawPanel().eraseFigure(oldPosition);
                Tetris.getRootFrame().getDrawPanel().drawFigure(newPosition);
                Tetris.getRootFrame().getDrawPanel().drawFigure(pile);
                oldPosition.copyFrom(newPosition);
            }
        });

    }
}
