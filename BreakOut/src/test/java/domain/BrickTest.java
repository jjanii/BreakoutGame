/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import java.io.IOException;
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
    public void brickHealthDropsByOneWhenBallHitsBrick() throws IOException {
        Brick b = new Brick(200, 200, 3);
        int health = b.getHealth();
        b.hit();
        assertEquals(b.getHealth(), health - 1);
    }
    
    @Test
    public void brickImageChangesWhenBrickGetsHit() throws IOException {
        Brick b = new Brick(200, 200, 2);
        Image i = b.getImage();
        b.hit();
        assertFalse(i.equals(b.getImage()));
    }
}
