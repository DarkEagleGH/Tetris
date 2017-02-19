package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

/**
 * Created by Tonk on 19.02.2017.
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

    class Nigga extends SwingWorker {
        private KeyEvent event;

        Nigga(KeyEvent event){
            this.event = event;
        }

        @Override
        protected Object doInBackground() throws Exception {
            System.out.println("Nigga " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
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
            }
            System.out.println("typed " + event.getKeyCode());
            return null;
        }
    }
}