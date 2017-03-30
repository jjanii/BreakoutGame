/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Ball;
import domain.Bat;
import domain.Brick;
import game.GameDrawer;
import java.util.ArrayList;
import javax.swing.Timer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class GameTest {

    public GameTest() {
    }

    @Test
    public void gameIsOnWhileBallIsAboveTheBat() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();

        while (b.getY() > bat.getY()) {
            assertTrue(g.timerStatus());
            g.checkCollisions();
        }
    }

    @Test
    public void gameEndsIfBallDropsBelowTheBat() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();

        bat.setY(400);
        b.setY(500);

        g.checkCollisions();
        assertFalse(g.timerStatus());
    }

    @Test
    public void changeBallDirectionChangesBallToGoUp() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();
        
        b.setdy(3); //set ball to go down        
        g.changeBallDirection();

        assertTrue(b.getdy() < 0); 
    }
    
    @Test
    public void brickGetsDeletedAfterHitTwice() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int numberOfBricks = g.getBricks().size();
        
        g.hitBrick(0);
        g.hitBrick(0);
        
        assertEquals(g.getBricks().size(), numberOfBricks-1);
    }
    
    
}
