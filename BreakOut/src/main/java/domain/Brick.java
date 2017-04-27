/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Luokan tehtävä on luoda ja päivittää tiilen kuvaa ja elämiä.
 *
 * @author Jani
 */
public final class Brick extends Item implements Drawable {

    private int health;
    private ImageIcon brick;

    /**
     * Konstrutori.
     *
     * @param x tiilen x-koordinaatti
     * @param y tiilen y-koordinaatti
     * @param hp tiilen elämät
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public Brick(int x, int y, int hp) throws IOException {
        health = hp;
        setBrickImage();
        this.x = x;
        this.y = y;
    }

    /**
     * Vaihdetaan tiilen kuva sen mukaan paljonko elämiä kyseisellä tiilellä on
     * jäljellä.
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void setBrickImage() throws IOException {

        if (health == 4) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("brick_red.png");
            brick = new ImageIcon(ImageIO.read(is));
        } else if (health == 3) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("brick_red_broken.png");
            brick = new ImageIcon(ImageIO.read(is));

        } else if (health == 2) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("brick.png");
            brick = new ImageIcon(ImageIO.read(is));
        } else if (health == 1) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("brick_broken.png");
            brick = new ImageIcon(ImageIO.read(is));
        }

        image = brick.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Palauttaa elämien määrään.
     *
     * @return montako elämää jäljellä.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Kun pallo osuu tiileen niin vähennetään tiileltä elämä ja kutsutaan
     * setBrickImagea jotta tiilen kuva vaihtuu.
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void hit() throws IOException {
        health--;
        image.flush();
        setBrickImage();
    }

}
