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
public class Ball extends Item implements Drawable {

    private int dx;
    private int dy;

    public Ball(int startx, int starty) {

        ImageIcon ball = new ImageIcon("resources/ball.png");
        image = ball.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;

        dx = 3;
        dy = -2;

    }

    public void move() {
        x += dx;
        y += dy;

        if (x <= 0) {
            dx = 3;
        } else if (x >= 400 - width) {
            dx = -3;
        }

        if (y <= 0) {
            dy = 2;
        }
    }
    
    public void setdx(int x) {
        dx = x;
    }
    
    public void setdy(int y) {
        dy = y;
    }
    
    public int getdy() {
        return dy;
    }
    
    public int getdx() {
        return dx;
    }
    
    
}
