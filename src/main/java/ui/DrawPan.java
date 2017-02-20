package ui;

import core.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class DrawPan extends JPanel {
    private ImageBuffer imageBuffer;
    private final static int GAP = 10;
    private final static int CAP_STROKE_WIDTH = 5;

    DrawPan() {
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (imageBuffer == null) {
            rebuildBuffer();
        }
        g.drawImage(imageBuffer, 0, 0, this);
    }

    private void rebuildBuffer() {
        int w = getWidth();
        int h = getHeight();
        imageBuffer = new ImageBuffer(w, h, BufferedImage.TYPE_INT_ARGB);
        imageBuffer.setBaseColor(this.getBackground());
    }

    public void drawFigure(Figure figure) {
        imageBuffer.drawFigure(figure);
        paintComponent(this.getGraphics());
    }

    public void eraseFigure(Figure figure) {
        imageBuffer.eraseFigure(figure);
        paintComponent(this.getGraphics());
    }

    public void reset() {
        imageBuffer.clearCup(GAP, CAP_STROKE_WIDTH);
        paintComponent(this.getGraphics());

    }

    public void gameOver() {
        imageBuffer.gameOver();
        paintComponent(this.getGraphics());
    }
}
