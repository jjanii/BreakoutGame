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
 * PowerUp luokka.
 *
 * @author Jani
 */
public final class PowerUp extends Item implements Drawable {

    /**
     * Luodaan maila ja ladataan sen kuva.
     *
     * @param startx powerupin aloitus x-koordinaatti
     * @param starty powerupin aloitus y-koordinaatti
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public PowerUp(int startx, int starty) throws IOException {
        x = startx;
        y = starty;
        InputStream is = getClass().getClassLoader().getResourceAsStream("powerup_red.png");
        ImageIcon powerup = new ImageIcon(ImageIO.read(is));
        image = powerup.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Liikuttaa poweruppia yhden pikselin alas.
     */
    public void move() {
        y += 1;
    }

}
