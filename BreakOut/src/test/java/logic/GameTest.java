/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import logic.Game;
import domain.Ball;
import domain.Bat;
import domain.Drawable;
import game.GameDrawer;
import java.util.ArrayList;
import javax.swing.Timer;
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
public class GameTest {

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void gameIsOnWhileBallIsAboveTheBat() {
        GameDrawer gd = new GameDrawer(10);
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();

        while (b.getY() > bat.getY()) {
            assertEquals(g.status, "Game is on");
            g.checkCollisions();
        }
    }

    @Test
    public void gameEndsIfBallDropsBelowTheBat() {
        GameDrawer gd = new GameDrawer(10);
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();

        bat.setY(400);
        b.setY(500);

        g.checkCollisions();
        assertEquals(g.status, "Game over");
    }

    @Test
    public void changeBallDirectionChangesBallToGoUp() {
        GameDrawer gd = new GameDrawer(10);
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        Ball b = g.getBall();
        Bat bat = g.getBat();
        
        b.setdy(3); //set ball to go down        
        g.changeBallDirection();

        assertTrue(b.getdy() < 0); 
    }
}
