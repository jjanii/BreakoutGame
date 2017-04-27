/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logic.Game;
import domain.Drawable;
import java.awt.Color;
import logic.Game.GameEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Luokassa piirretään jatkuvasti peliä uusiksi 10ms välein.
 * @author Jani
 */
public class GameDrawer extends JPanel implements KeyListener, ActionListener {

    private Timer timer = new Timer(10, this);
    private Game game;
    private ArrayList<Drawable> items;

    /** Konstruktorissa käynnistetään peli.
     * @throws java.io.IOException
     */
    public GameDrawer() throws IOException {
        game = new Game(timer);
        timer.start();
        setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            try {
                game.sendEvent(GameEvent.TIMER_TICK);
            } catch (IOException ex) {
                Logger.getLogger(GameDrawer.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                game.sendEvent(GameEvent.MOVE_RIGHT);
            } catch (IOException ex) {
                Logger.getLogger(GameDrawer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                game.sendEvent(GameEvent.MOVE_LEFT);
            } catch (IOException ex) {
                Logger.getLogger(GameDrawer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!timer.isRunning()) {
                try {
                    game = new Game(timer);
                } catch (IOException ex) {
                    Logger.getLogger(GameDrawer.class.getName()).log(Level.SEVERE, null, ex);
                }
                timer.restart();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                game.sendEvent(GameEvent.KEY_RELEASED);
            } catch (IOException ex) {
                Logger.getLogger(GameDrawer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
