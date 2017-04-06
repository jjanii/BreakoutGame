/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;

/**
 *
 * @author Jani
 */
public interface Drawable {

    public int getX();
    public int getY();
    public Image getImage();
    public int getWidth();
    public int getHeight();
}
