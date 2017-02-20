package ui;

import core.Brick;
import core.Figure;
import core.Tetris;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

/**
 * Created by Tonk on 17.02.2017. **
 */

class ImageBuffer extends BufferedImage {
    private Graphics2D g2d;
    private int scale;
    private int baseX;
    private int baseY;
    private Color baseColor;        // Panel color for erasing

    void setBaseColor(Color baseColor) {
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
        int countX = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.x"));
        int countY = Integer.parseInt(Tetris.getSettings().getProperty("cup.size.y"));
        int head = Integer.parseInt(Tetris.getSettings().getProperty("cup.head"));
        baseX = GAP + CAP_STROKE_WIDTH;
        baseY = h - GAP - CAP_STROKE_WIDTH;

        // Calculate brick size in px
        int tmpScaleX = (w - GAP - CAP_STROKE_WIDTH * 2) / countX;
        int tmpScaleY = (h - GAP - CAP_STROKE_WIDTH * 2) / (countY + head);
        if (tmpScaleX <= tmpScaleY) {
            scale = tmpScaleX;
        } else {
            scale = tmpScaleY;
        }

        // Clear field
        g2d.setColor(baseColor);
        g2d.fillRect(0, 0, w, h);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cup borders. Left, bottom,right
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(CAP_STROKE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));
        g2d.drawLine(GAP, h - GAP, GAP, GAP + head * scale);
        g2d.drawLine(GAP, h - GAP, GAP + CAP_STROKE_WIDTH + scale * countX, h - GAP);
        g2d.drawLine(GAP + CAP_STROKE_WIDTH * 2 + scale * countX - 1,
                h - GAP, GAP + CAP_STROKE_WIDTH * 2 + scale * countX - 1, GAP + head * scale);
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
            g2d.fillRect(baseX + (brick.getPosX() - 1) * scale, baseY - (brick.getPosY()) * scale, scale - 1, scale - 1);
        }
    }

    void gameOver() {
        g2d.setColor(Color.red);
        FontRenderContext context = g2d.getFontRenderContext();
        Font font = new Font("Arial", Font.BOLD, 48);
        TextLayout text = new TextLayout("Game Over", font, context);
        g2d.setFont(font);
        int x = (getWidth() / 2 - (int) (text.getBounds().getWidth() / 2));
        int y = (getHeight() / 2);
        g2d.drawString("Game Over", x, y);
    }
}
