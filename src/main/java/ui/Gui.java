package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tonk on 16.02.2017.
 */
public class Gui extends JFrame{
    private JButton startBt;
    private JPanel mainPan;
    private JPanel controlPan;
    private DrawPan drawPan;

    public Gui() {
        super("Tetris");
        setContentPane(mainPan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        startBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        JPanel drawPan = new DrawPan();
    }
}
