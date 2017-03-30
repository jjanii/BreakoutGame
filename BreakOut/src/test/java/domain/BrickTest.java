/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class BrickTest {
    
    public BrickTest() {
    }
    
    @Test
    public void brickHealthDropsByOneWhenBallHitsBrick() {
        Brick b = new Brick(200, 200, 999);
        int health = b.getHealth();
        b.hit();
        assertEquals(b.getHealth(), health - 1);
    }
    
    @Test
    public void brickImageChangesWhenBrickGetsHit() {
        Brick b = new Brick(200, 200, 2);
        Image i = b.getImage();
        b.hit();
        assertFalse(i.equals(b.getImage()));
    }
}
