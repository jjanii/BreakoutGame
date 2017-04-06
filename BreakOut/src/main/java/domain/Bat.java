/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.ImageIcon;

/**
 * Liikutettavan mailan luokka
 * @author Jani
 */
public class Bat extends Item implements Drawable {

    private int direction;

    /**
     * Luodaan maila ja ladataan sen kuva
     * @param startx mailan aloitus x-koordinaatti
     * @param starty mailan aloitus y-koordinaatti
     */
    public Bat(int startx, int starty) {

        ImageIcon bat = new ImageIcon("resources/bat.png");
        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;
    }

    /**
     * Liikutetaan mailaa 4pikseliä annettuun suuntaan
     */
    public void move() {
        x += direction;
        borderHandler();
    }

    /**
     * Tarkistetaan ettei maila mene näytön rajojen ulkopuolelle
     */
    public void borderHandler() {
        if (x >= 440 - width) {
            x = 440 - width;
        } else if (x <= 0) {
            x = 0;
        }
    }

    public void setDirection(int speed) {
        this.direction = speed;
    }
    
    public int getDirection() {
        return this.direction;
    }

}
