/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Jani
 */
public class Bat extends Item implements Drawable {

    private int movement;

    public Bat(int startx, int starty) {

        ImageIcon bat = new ImageIcon("resources/bat.png");
        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;
    }

    public void moveLeft() {
        x -= 9;
        if (x <= 0) {
            x = 0;
        }
    }

    public void moveRight() {
        x += 9;

        if (x >= 400 - width) {
            x = 400 - height;
        }
    }

}
