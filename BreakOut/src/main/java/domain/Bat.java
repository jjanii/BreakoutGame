/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.ImageIcon;

/**
 *
 * @author Jani
 */
public class Bat extends Item implements Drawable {
    private int speed;
    public Bat(int startx, int starty, int speed) {

        ImageIcon bat = new ImageIcon("resources/bat.png");
        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;
        this.speed = speed;
    }

    public void moveLeft() {
        x -= speed;
        if (x <= 0) {
            x = 0;
        } 
    }

    public void moveRight() {
        x += speed;

        if (x >= 400 - width) {
            x = 400 - width;
        }
    }

}
