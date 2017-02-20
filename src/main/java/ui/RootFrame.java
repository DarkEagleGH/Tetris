package ui;

import core.Tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tonk on 16.02.2017. **
 */
public class RootFrame extends JFrame {
    private JButton startBt;
    private JPanel mainPan;
    private JPanel controlPan;
    private DrawPan drawPan;

    public DrawPan getDrawPanel() {
        return drawPan;
    }

    public RootFrame() {
        super("Tetris");
        System.out.println("RootFrame " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
        setContentPane(mainPan);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);

        startBt.addActionListener(e -> Tetris.getGame().reset());

        setVisible(true);
    }
}
