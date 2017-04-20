/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.ImageIcon;

/** PowerUp luokka.
 *
 * @author Jani
 */
public class PowerUp extends Item implements Drawable {

    /** Luodaan maila ja ladataan sen kuva.
     * @param startx powerupin aloitus x-koordinaatti
     * @param starty powerupin aloitus y-koordinaatti
     */
    public PowerUp(int startx, int starty) {
        x = startx;
        y = starty;
    }

    /** Valitaan satunnainen powerup (kesken).
     */
    public void choosePowerUp() {
        ImageIcon bat = new ImageIcon("resources/bat.png");
        image = bat.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

}
