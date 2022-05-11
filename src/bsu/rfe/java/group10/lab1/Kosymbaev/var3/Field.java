package bsu.rfe.java.group10.lab1.Kosymbaev.var3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {
    // Флаг приостановленности движения
    private boolean paused;
    private boolean pausedFast;
    private boolean pauseBig;

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

    public synchronized void pauseFast(){
        pausedFast = true; //вкл паузу быстрых мячей
    }

    public synchronized void canMove(BoucingBalls ball) throws
            InterruptedException {
        if (paused) {
            // Если режим паузы включен, то поток, зашедший
            // внутрь данного метода, засыпает
            wait();
        }
        if(pausedFast && ball.getSpeed() > 8){
            wait();
        }
        if(pauseBig && ball.getRadius() > 20){
            wait();
        }
    }
}
