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
public class BallTest {
    
    public BallTest() {
    }
    
    @Test
    public void moveMovesBallToTheSetDirectionWhenMovedOnce() {
        Ball b = new Ball(200, 200);
        b.setdx(5);
        b.setdy(-5);
        
        b.move();
        
        assertEquals(b.getX(), 205);
        assertEquals(b.getY(), 195);
    }
    
    @Test
    public void moveMovesBallCorrectlyWhenMovedMultipleTimes() {
        Ball b = new Ball(200, 200);
        b.setdx(1);
        b.setdy(1);
        
        for (int i = 0; i < 50; i++) {
            b.move();
        }
        
        assertEquals(b.getX(), 250);
        assertEquals(b.getY(), 250);
    }
    
    @Test
    public void ballCanNotMoveOutOfBorders() {
        Ball b = new Ball(200, 200);
        b.setdx(-10);
        b.setdy(1);
        
        for (int i = 0; i < 50; i++) {
            b.move();
        }
        
        assertTrue(b.getX() > 0);
    }
    
}
