/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class BatTest {
    
    public BatTest() {
    }
    
    @Test
    public void moveLeftMovesTheBatRightAmountOfPixelsLeft() throws IOException {
        int speed = -20;
        Bat b = new Bat(200, 200);
        b.setDirection(20);
        b.move();
        assertEquals(b.getX(), 200-speed);
    }
    
    @Test
    public void moveRightMovesTheBatRightAmountOfPixelsRight() throws IOException {
        int speed = 30;
        Bat b = new Bat(200, 200);
        b.setDirection(speed);
        b.move();
        assertEquals(b.getX(), 200+speed);
    }
    
    @Test
    public void moveRightDoesNotMoveTheBatOutOfBorders() throws IOException {
        int speed = 10;
        Bat b = new Bat(200, 200);
        b.setDirection(speed);
        for (int i = 0; i < 100; i++) {
            b.move();
        }
        assertEquals(b.getX(), 440-b.getWidth());
    }
     
    @Test
    public void moveLeftDoesNotMoveTheBatOutOfBorders() throws IOException {
        int speed = -10;
        Bat b = new Bat(200, 200);
        b.setDirection(speed);
        for (int i = 0; i < 100; i++) {
            b.move();
        }
        assertEquals(b.getX(), 0);
    }
    
}
