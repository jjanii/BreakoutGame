/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Jani
 */
public class Bat extends Item {

    private int movement;

    public Bat() {

        ImageIcon bat = new ImageIcon("resources/bat.png");
        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = 200;
        y = 380;
    }

    public void move() {
        if (movement != 0) {
            x += movement;

            if (x <= 0) {
                x = 0;
            } else if (x >= 400 - width) {
                x = 400 - height;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            movement = -3;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movement = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movement = 0;
        }
    }

}
