/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Ball;
import domain.Bat;
import GUI.GameDrawer;
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

        assertEquals(g.getBricks().size(), numberOfBricks - 1);
    }

    @Test
    public void getItemsReturnsCorrectAmountOfItems() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        assertEquals(32, g.getItems().size());
    }

    @Test
    public void gameMovesToSecondLevelAfterAllBricksAreRemoved() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int firstlevel = g.getLevel();
        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);

        int currentlevel = g.getLevel();
        assertEquals(currentlevel, firstlevel + 1);
    }

    @Test
    public void gameMovesToThirdLevelAfterSecondLevelIsCompleted() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int firstlevel = g.getLevel();
        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);
        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);

        int currentlevel = g.getLevel();
        assertEquals(currentlevel, firstlevel + 2);
    }

    @Test
    public void gameEndsWhenThirdLevelIsCompleted() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);
        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);
        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);

        assertFalse(g.timerStatus());
    }

    @Test
    public void createBricksCreates30Bricks() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);

        assertEquals(g.getBricks().size(), 30);
    }

    @Test
    public void sendEventKeyReleasedStopsBatMovement() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int batspeed = 4;
        g.getBat().setDirection(batspeed);

        g.sendEvent(Game.GameEvent.KEY_RELEASED);
        assertEquals(0, g.getBat().getDirection());
    }

    @Test
    public void sendEventMoveLeftSetsBatDirectionTo4PxLeft() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.sendEvent(Game.GameEvent.MOVE_LEFT);
        assertEquals(-4, g.getBat().getDirection());
    }

    @Test
    public void sendEventMoveRightSetsBatDirectionTo4PxLeft() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.sendEvent(Game.GameEvent.MOVE_RIGHT);
        assertEquals(4, g.getBat().getDirection());
    }

    @Test
    public void sendEventTimerTickMovesBatAndBall() {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.getBat().setDirection(2);
        int batX = g.getBat().getX();

        int ballY = g.getBall().getY();
        g.sendEvent(Game.GameEvent.TIMER_TICK);
        assertEquals(g.getBat().getX(), batX + 2);
        assertEquals(g.getBall().getY(), ballY + 2);
    }

}
