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

/** Liikutettavan mailan luokka.
 * @author Jani
 */
public class Bat extends Item implements Drawable {

    private int direction;

    /** Konstruktori.
     * @param startx mailan aloitus x-koordinaatti
     * @param starty mailan aloitus y-koordinaatti
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public Bat(int startx, int starty) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("bat.png");

        ImageIcon bat = new ImageIcon(ImageIO.read(is));

        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;
    }

    /** Liikutetaan mailaa 4pikseliä annettuun suuntaan.
     */
    public void move() {
        x += direction;
        borderHandler();
    }

    /** Tarkistetaan ettei maila mene näytön rajojen ulkopuolelle.
     */
    public void borderHandler() {
        if (x >= 440 - width) {
            x = 440 - width;
        } else if (x <= 0) {
            x = 0;
        }
    }

    /** Asettaa nopeuden.
     * @param speed mailan nopeus
     */
    public void setDirection(int speed) {
        this.direction = speed;
    }
    
    /** Palauttaa mailan suunnan.
     * @return palauttaa mailan suunnan.
     */
    public int getDirection() {
        return this.direction;
    }

}
