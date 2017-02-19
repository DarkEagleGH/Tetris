package ui;

import core.Figure;
import core.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tonk on 16.02.2017.
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
        System.out.println("RootFrame " + Thread.currentThread().getName()+" "+Thread.currentThread().getId());
        setContentPane(mainPan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
//        addKeyListener(this);
        setFocusable(true);
//        setFocusTraversalKeysEnabled(false);

        startBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPan.reset();
            }
        });



        setVisible(true);
    }
}
