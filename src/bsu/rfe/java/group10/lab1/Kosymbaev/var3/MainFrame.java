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
    private static final int width = 500;
    private static final int height = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem pauseMenuFastItem;
    private JMenuItem pauseMenuBigItem;

    private Field field = new Field();

    public MainFrame() {
        super("Многопоточное програмирование");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
        // Устанавливаем окно развёрнутым на весь экран
        setExtendedState(MAXIMIZED_BOTH);

        //меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()
                        && !pauseMenuFastItem.isEnabled()) {
                    // Ни один из пунктов меню не являются
                    // доступными, сделать доступным паузу и паузу быстрых мячей доступными
                    pauseMenuItem.setEnabled(true);
                    pauseMenuFastItem.setEnabled(true);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);

        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение") {
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
                pauseMenuFastItem.setEnabled(false);
                pauseMenuBigItem.setEnabled(false);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
                pauseMenuFastItem.setEnabled(true);
                pauseMenuBigItem.setEnabled(true);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        Action pauseFastAction = new AbstractAction("Приостановить быстрые мячи") {
            public void actionPerformed(ActionEvent event) {
                field.pauseFast();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(true);
                pauseMenuFastItem.setEnabled(false);
                pauseMenuBigItem.setEnabled(true);
            }
        };
        pauseMenuFastItem = controlMenu.add(pauseFastAction);
        pauseMenuFastItem.setEnabled(false);

        Action pauseBigAction = new AbstractAction("Приостановить большие мячи") {
            public void actionPerformed(ActionEvent event) {
                field.pauseBig();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(true);
                pauseMenuFastItem.setEnabled(true);
                pauseMenuBigItem.setEnabled(false);
            }
        };
        pauseMenuBigItem = controlMenu.add(pauseBigAction);
        pauseMenuFastItem.setEnabled(false);

        // Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
// Создать и сделать видимым главное окно приложения
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}