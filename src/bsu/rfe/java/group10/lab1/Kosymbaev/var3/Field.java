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
    // Динамический список скачущих мячей
    private ArrayList<BoucingBalls> balls = new ArrayList<BoucingBalls>(10);

    // Класс таймер отвечает за регулярную генерацию событий ActionEvent
    // При создании его экземпляра используется анонимный класс,
    // реализующий интерфейс ActionListener
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            // Задача обработчика события ActionEvent - перерисовка окна
            repaint();
        }
    });


    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        // Вызвать версию метода, унаследованную от предка
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        // Последовательно запросить прорисовку от всех мячей из списка
        for (BoucingBalls ball : balls) {
            ball.paint(canvas);
        }
    }

    public void addBall() {
        //Заключается в добавлении в список нового экземпляра BouncingBall
        // Всю инициализацию положения, скорости, размера, цвета
        // BouncingBall выполняет сам в конструкторе
        balls.add(new BoucingBalls(this));
    }

    // Метод синхронизированный, т.е. только один поток может одновременно быть внутри
    public synchronized void pause() {
        paused = true; //вкл пауза
    }

    public synchronized void pauseFast(){
        pausedFast = true; //вкл паузу быстрых мячей
    }

    public synchronized void pauseBig(){
        pauseBig = true;
    }

    // Метод синхронизированный, т.е. только один поток может одновременно быть внутри
    public synchronized void resume() {
        paused = false; //выкл пауза
        pausedFast = false; //выкл паузы быстрых мячей
        pauseBig = false;
        // Будим все ожидающие продолжения потоки
        notifyAll();
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
