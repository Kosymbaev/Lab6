package bsu.rfe.java.group10.lab1.Kosymbaev.var3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {
    private boolean paused;

    private ArrayList<BoucingBalls> balls = new ArrayList<BoucingBalls>(10);

    private Timer repaintTimer = new Timer(10, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });
    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }
}
