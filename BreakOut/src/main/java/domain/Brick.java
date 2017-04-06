/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.ImageIcon;

/**
 * Luokan tehtävä on luoda ja päivittää tiilen kuvaa ja elämiä
 * @author Jani
 */
public final class Brick extends Item implements Drawable {

    private int health;
    private ImageIcon brick;

    /**
     * Tiilen konstruktori
     * @param x tiilen x-koordinaatti
     * @param y tiilen y-koordinaatti
     * @param hp tiilen elämät
     */
    public Brick(int x, int y, int hp) {
        health = hp;
        setBrickImage();

        this.x = x;
        this.y = y;
    }

    /**
     * Vaihdetaan tiilen kuva sen mukaan paljonko elämiä kyseisellä tiilellä on jäljellä
     */
    public void setBrickImage() {
        
        if (health == 4) {
            brick = new ImageIcon("resources/brick_red.png");
        } else if (health == 3) {
            brick = new ImageIcon("resources/brick_red_broken.png");
        } else if (health == 2) {
            brick = new ImageIcon("resources/brick.png");
        } else if (health == 1) {
            brick = new ImageIcon("resources/brick_broken.png");
        }

        image = brick.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * Kun pallo osuu tiileen niin vähennetään tiileltä elämä ja kutsutaan setBrickImagea
     * jotta tiilen kuva vaihtuu
     */
    public void hit() {
        health--;
        image.flush();
        setBrickImage();
    }

}
