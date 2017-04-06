/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Jani
 */
public class Item {

    public int x;
    public int y;
    public int width;
    public int height;
    public Image image;


    public void setWidth(int w) {
        this.width = w;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
