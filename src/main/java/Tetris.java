import ui.Gui;

import javax.swing.*;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Tetris {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Gui gui = new Gui();
            }
        });

    }
}
