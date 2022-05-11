package bsu.rfe.java.group10.lab1.Kosymbaev.var3;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainFrame extends JFrame {
    //size of window
    private static final int width=500;
    private static final int height=500;

    private Field field = new Field();

    public MainFrame() {
        super("Многопоточное програмирование");
        setSize(width,height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation(kit.getScreenSize().width-width/2, kit.getScreenSize().width-width/2);
        setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu balls = new JMenu("Balls");
        Action ballsAction = new AbstractAction("Add Balls") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        menuBar.add(balls);
        balls.add(ballsAction);
    }
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
