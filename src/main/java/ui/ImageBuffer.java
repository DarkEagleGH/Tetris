package ui;

import core.Brick;
import core.Figure;
import core.Tetris;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Tonk on 17.02.2017.
 */

class ImageBuffer extends BufferedImage {
    private Graphics2D g2d;
    private int scale;
    private int countX;
    private int countY;
    private int head;
    private int baseX;
    private int baseY;
    private Color baseColor;

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    ImageBuffer(int width, int height, int imageType) {
        super(width, height, imageType);
        g2d = this.createGraphics();
        baseColor = g2d.getColor();

    }

    void clearCup(int GAP, int CAP_STROKE_WIDTH) {
        int w = getWidth();
        int h = getHeight();
        countX = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.x"));
        countY = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.y"));
        head = Integer.parseInt(Tetris.getSettings().getProperty("cup.head"));

        baseX = GAP + CAP_STROKE_WIDTH;
        baseY = h - GAP - CAP_STROKE_WIDTH;
        int tmpScaleX = (w - GAP - CAP_STROKE_WIDTH * 2) / countX;
        int tmpScaleY = (h - GAP - CAP_STROKE_WIDTH * 2) / (countY + head);
        if (tmpScaleX <= tmpScaleY) {
            scale = tmpScaleX;
        } else {
            scale = tmpScaleY;
        }
        g2d = this.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(CAP_STROKE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));
        g2d.drawLine(GAP, h - GAP, GAP, GAP + head * scale);
        g2d.drawLine(GAP, h - GAP, GAP + CAP_STROKE_WIDTH + scale * countX, h - GAP);
        g2d.drawLine(GAP + CAP_STROKE_WIDTH * 2 + scale * countX, h - GAP, GAP + CAP_STROKE_WIDTH * 2 + scale * countX,
                GAP + head * scale);

/*        g2d.setColor(Color.lightGray);
        for (int j = 1; j < countY + 1; j++) {
            for (int i = 0; i < countX; i++) {
                g2d.fill3DRect(GAP + CAP_STROKE_WIDTH + i * scale, h - GAP - CAP_STROKE_WIDTH - scale * j, scale, scale, true);
            }
        }*/

        g2d.setColor(Color.cyan);
        g2d.setStroke(new BasicStroke(2));
//        g2d.drawLine(baseX, baseY - scale * head, baseX + scale * countX, baseY - scale * head);
    }

    void eraseFigure(Figure figure) {
        draw(figure, baseColor);
    }

    void drawFigure(Figure figure) {
        draw(figure, Color.lightGray);
    }

    private void draw(Figure figure, Color color) {
        for (Brick brick : figure.getBricks()) {
            g2d.setColor(color);
//            g2d.fillRoundRect(baseX + brick.getPosX()*scale, baseY - brick.getPosY() * scale, scale-1, scale-1, 10, 10);
            g2d.fillRect(baseX + (brick.getPosX()-1) * scale, baseY - (brick.getPosY()) * scale, scale - 1, scale - 1);
        }
    }
}
