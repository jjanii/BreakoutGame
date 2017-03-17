/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Ball;
import domain.Bat;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import logic.PlatformMover;

/**
 *
 * @author Jani
 */
public class Game extends JPanel {

    private Ball ball;
    private Bat platform;
    private Timer timer;

    public Game(int bricks) {
        timer = new Timer();
        platform = new Bat();
        ball = new Ball();
        gameScreen();
    }

    private void gameScreen() {
        addKeyListener(new PlatformMover(platform));
        setFocusable(true);
        timer.scheduleAtFixedRate(new ScreenRepaint(), 1000, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        drawObjects(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        g2d.drawImage(platform.getImage(), platform.getX(), platform.getY(),
                platform.getWidth(), platform.getHeight(), this);
    }

    private class ScreenRepaint extends TimerTask {
        @Override
        public void run() {
            platform.move();
            repaint();
        }
    }

}
