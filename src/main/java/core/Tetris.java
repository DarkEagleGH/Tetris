package core;

import ui.RootFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Tetris {
    private static Properties settings;
    private static RootFrame rootFrame;
    private static Game game;

    public static Properties getSettings() {
        return settings;
    }
    public static RootFrame getRootFrame() {
        return rootFrame;
    }
    public static Game getGame() {
        return game;
    }

    private static void setSettings() {
        InputStream inputStream = Tetris.class.getClassLoader().getResourceAsStream("settings.properties");
        if (inputStream != null) {
            try {
                settings = new Properties();
                settings.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher());
    }

    public static void main(String[] args) throws InterruptedException {
        setSettings();

        System.out.println("Tetris " + Thread.currentThread().getName()+" "+Thread.currentThread().getId());
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Tetris.rootFrame = new RootFrame();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                rootFrame.getDrawPanel().reset();
            }
        });

//        Game game = new Game(null);
        game = new Game();

        while (true) {
            System.out.println("---" + Thread.currentThread().getName()+" "+Thread.currentThread().getId());
            Thread.sleep(3000);
        }

    }
}
