/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;

/** Drawable rajapinta. 
*
 * @author Jani
 */
public interface Drawable {

    /** Palauttaa x-koordinaatin.
     *
     * @return x-koordinaatti
     */
    public int getX();

    /** Palauttaa y-koordinaatin.
     *
     * @return y-koordinaatti
     */
    public int getY();

    /** Palauttaa kuvan.
     *
     * @return kuvan
     */
    public Image getImage();

    /** Palauttaa leveyden.
     *
     * @return leveyden
     */
    public int getWidth();

    /** Palauttaa korkeuden.
     *
     * @return korkeuden
     */
    public int getHeight();
}
