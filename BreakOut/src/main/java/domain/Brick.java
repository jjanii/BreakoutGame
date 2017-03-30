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
public class Brick extends Item implements Drawable {

    private int health;

    public Brick(int x, int y, int hp) {
        ImageIcon brick = new ImageIcon("resources/brick.png");
        image = brick.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        this.x = x;
        this.y = y;
        
        health = hp;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public void hit() {
        if (health == 2) {
            ImageIcon brick = new ImageIcon("resources/brick_broken.png");
            image.flush();
            image = brick.getImage();
            health--;
        } else {
            health--;
        }
    }

}
