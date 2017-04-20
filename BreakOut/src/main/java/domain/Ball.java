/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.ImageIcon;

/** Pallon luokka, luokan tehtävä lähinnä liikuttaa palloa oikeaan suuntaan.
 * @author Jani
 */
public class Ball extends Item implements Drawable {

    private int dx;
    private int dy;

    /** Luodaan pallo ja ladataan pallon kuva.
     * @param startx pallon aloitus x-koordinaatti
     * @param starty pallon aloitus y-koordinaatti
     */
    public Ball(int startx, int starty) {

        ImageIcon ball = new ImageIcon("resources/ball.png");
        image = ball.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = startx;
        y = starty;

        dx = 0;
        dy = 2;

    }

    /** Liikutetaan palloa asetettujen y ja x koordinaattien verran.
     */
    public void move() {
        x += dx;
        y += dy;

        checkIfBallGoesOutOfBorder();
    }

    /** Tarkistetaan meneekö pallo ruudun rajojen yli.
     */
    public void checkIfBallGoesOutOfBorder() {
        if (x <= 0) {
            dx = 3;
        } else if (x >= 440 - width) {
            dx = -3;
        }

        if (y <= 0) {
            dy = 2;
        }
    }

    /** Suunnan x setteri.
     * @param x suunta x
     */
    public void setdx(int x) {
        dx = x;
    }

    /** Suunnan y setteri.
     * @param y suunta y
     */
    public void setdy(int y) {
        dy = y;
    }

    /** Palauttaa suunnan y.
     * @return suunta y
     */
    public int getdy() {
        return dy;
    }

    /** Palauttaa suunnan x.
     * @return suunta x
     */
    public int getdx() {
        return dx;
    }

}
