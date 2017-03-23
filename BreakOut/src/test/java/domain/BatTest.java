/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void moveLeftMovesTheBatRightAmountOfPixelsLeft() {
        int speed = 20;
        Bat b = new Bat(200, 200, speed);
        b.moveLeft();
        assertEquals(b.getX(), 200-speed);
    }
    
    @Test
    public void moveRightMovesTheBatRightAmountOfPixelsRight() {
        int speed = 30;
        Bat b = new Bat(200, 200, speed);
        b.moveRight();
        assertEquals(b.getX(), 200+speed);
    }
    
    @Test
    public void moveRightDoesNotMoveTheBatOutOfBorders() {
        int speed = 10;
        Bat b = new Bat(200, 200, speed);
        for (int i = 0; i < 100; i++) {
            b.moveRight();
        }
        assertEquals(b.getX(), 400-b.getWidth());
    }
     
    @Test
    public void moveLeftDoesNotMoveTheBatOutOfBorders() {
        int speed = 10;
        Bat b = new Bat(200, 200, speed);
        for (int i = 0; i < 100; i++) {
            b.moveLeft();
        }
        assertEquals(b.getX(), 0);
    }
    
}
