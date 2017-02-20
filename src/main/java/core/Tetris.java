package core;

import ui.RootFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class Tetris {
    private static Properties settings;
    private static RootFrame rootFrame;

    private static Game game;

    public static Properties getSettings() {
        return settings;
    }

    static RootFrame getRootFrame() {
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

        try {
            SwingUtilities.invokeAndWait(() -> Tetris.rootFrame = new RootFrame());
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        game = new Game();
    }
}
