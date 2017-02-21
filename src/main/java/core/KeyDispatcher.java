package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Tonk on 19.02.2017. **
 */
class KeyDispatcher implements KeyEventDispatcher {
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            Nigga nigga = new Nigga(event);
            try {
                nigga.execute();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    private class Nigga extends SwingWorker {
        private KeyEvent event;

        Nigga(KeyEvent event) {
            this.event = event;
        }

        @Override
        protected Object doInBackground() throws Exception {
            switch (event.getKeyCode()) {
                case VK_LEFT: {
                    Tetris.getGame().move(Game.Direction.LEFT);
                }
                break;
                case VK_RIGHT: {
                    Tetris.getGame().move(Game.Direction.RIGHT);
                }
                break;
                case VK_DOWN: {
                    Tetris.getGame().move(Game.Direction.DOWN);
                }
                break;
                case VK_UP: {
                    Tetris.getGame().move(Game.Direction.ROTATE);
                }
                break;
            }
            return null;
        }
    }
}