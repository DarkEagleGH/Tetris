package core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class Figure {
    private int maxX;
    private int maxY;
    private int head;
    private List<Brick> bricks;
    private final Random random;

    int getMaxY() {
        return maxY;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    Figure() {
        this.maxX = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.x"));
        this.maxY = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.y"));
        this.head = Integer.parseInt(Tetris.getSettings().getProperty("cup.head"));
        this.bricks = new LinkedList<>();
        random = new Random(System.currentTimeMillis());
    }

    void generateNew() {
        if (bricks != null) {
            bricks.clear();
        }
        switch (random.nextInt(7)) {
            case 0:
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                bricks.add(new Brick(6, maxY - 1 + head));
                bricks.add(new Brick(7, maxY - 1 + head));
                break;
            case 1:
                bricks.add(new Brick(4, maxY + head));
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                bricks.add(new Brick(6, maxY - 1 + head));
                break;
            case 2:
                bricks.add(new Brick(6, maxY + head));
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                bricks.add(new Brick(6, maxY - 1 + head));
                break;
            case 3:
                bricks.add(new Brick(4, maxY + head));
                bricks.add(new Brick(5, maxY + head));
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                break;
            case 4:
                bricks.add(new Brick(5, maxY + head));
                bricks.add(new Brick(6, maxY + head));
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                break;
            case 5:
                bricks.add(new Brick(5, maxY + head));
                bricks.add(new Brick(4, maxY - 1 + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                bricks.add(new Brick(6, maxY - 1 + head));
                break;
            case 6:
                bricks.add(new Brick(4, maxY + head));
                bricks.add(new Brick(5, maxY + head));
                bricks.add(new Brick(5, maxY - 1 + head));
                bricks.add(new Brick(6, maxY - 1 + head));
                break;
        }
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
        //Later
    }

    boolean isIntersect(Figure pile) {
        for (Brick brick : this.bricks) {
            for (Brick pileBrick : pile.getBricks()) {
                if (brick.getPosX() == pileBrick.getPosX() && brick.getPosY() == pileBrick.getPosY()) {
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
        this.bricks.addAll(figure.getBricks().stream().map(brick -> new Brick(brick.getPosX(),
                brick.getPosY())).collect(Collectors.toList()));
    }

    void addBricks(Figure figure) {
        this.bricks.addAll(figure.getBricks().stream().map(brick -> new Brick(brick.getPosX(),
                brick.getPosY())).collect(Collectors.toList()));
        this.bricks.sort(new CompareByY());
    }

    boolean checkAndBurn(Figure figure) {
        boolean pileChanged = false;
        if (bricks.size() < maxX) {
            return false;
        }
        figure.getBricks().sort(new CompareByY());
        int min = figure.getBricks().get(0).getPosY();
        int max = figure.getBricks().get(figure.getBricks().size() - 1).getPosY();
        int count = 2;
        Brick right;
        Brick left;
        Iterator<Brick> leftIter = bricks.iterator();
        Iterator<Brick> rightIter = bricks.iterator();
        left = leftIter.next();
        rightIter.next();

        while (rightIter.hasNext()) {
            right = rightIter.next();
            if (right.getPosY() > max) {
                break;
            }
            if (right.getPosY() < min) {
                leftIter.next();
                continue;
            }
            if (right.getPosY() > left.getPosY()) {
                count = 1;
                left = leftIter.next();
            }
            if (right.getPosY() == left.getPosY()) {
                count++;
                if (count > maxX) {
                    leftIter.remove();
                    for (int i = 0; i < maxX - 1; i++) {
                        if (leftIter.hasNext()) {
                            leftIter.next();
                            leftIter.remove();
                        }
                    }
                    leftIter.forEachRemaining(brick -> brick.setPosY(brick.getPosY() - 1));
                    pileChanged = true;
                    checkAndBurn(figure);
                    break;
                }
            }
        }
        return pileChanged;
    }
}
