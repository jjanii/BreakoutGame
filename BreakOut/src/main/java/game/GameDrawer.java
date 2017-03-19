/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import domain.Drawable;
import game.Game.GameEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jani
 */
public class GameDrawer extends JPanel implements KeyListener, ActionListener{

    private Timer timer = new Timer(10, this);
    private Game game;
    private ArrayList<Drawable> items;

    public GameDrawer(int bricks) {
        game = new Game(timer);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            game.sendEvent(GameEvent.TIMER_TICK);
            repaint();
        }
        setFocusable(true);
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
        items = game.getItems();
        for (Drawable item : items) {
            g2d.drawImage(item.getImage(), item.getX(), item.getY(),
                    item.getWidth(), item.getHeight(), this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.sendEvent(GameEvent.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.sendEvent(GameEvent.MOVE_LEFT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
