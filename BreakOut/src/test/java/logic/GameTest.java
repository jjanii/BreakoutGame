/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Ball;
import domain.Bat;
import GUI.GameDrawer;
import domain.PowerUp;
import java.io.IOException;
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
    public void gameIsOnWhileBallIsAboveTheBat() throws IOException {
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
    public void gameEndsIfBallDropsBelowTheBat() throws IOException {
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
    public void changeBallDirectionChangesBallToGoUp() throws IOException {
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
    public void brickGetsDeletedAfterHitTwice() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int numberOfBricks = g.getBricks().size();

        g.hitBrick(0);
        g.hitBrick(0);

        assertEquals(g.getBricks().size(), numberOfBricks - 1);
    }

    @Test
    public void getItemsReturnsCorrectAmountOfItems() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        assertEquals(32, g.getItems().size());
    }

    @Test
    public void gameMovesToSecondLevelAfterAllBricksAreRemoved() throws IOException {
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
    public void gameMovesToThirdLevelAfterSecondLevelIsCompleted() throws IOException {
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
    public void gameEndsWhenThirdLevelIsCompleted() throws IOException {
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
    public void createBricksCreates30Bricks() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.deleteAllBricks();
        g.sendEvent(Game.GameEvent.TIMER_TICK);

        assertEquals(g.getBricks().size(), 30);
    }

    @Test
    public void sendEventKeyReleasedStopsBatMovement() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int batspeed = 4;
        g.getBat().setDirection(batspeed);

        g.sendEvent(Game.GameEvent.KEY_RELEASED);
        assertEquals(0, g.getBat().getDirection());
    }

    @Test
    public void sendEventMoveLeftSetsBatDirectionTo4PxLeft() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.sendEvent(Game.GameEvent.MOVE_LEFT);
        assertEquals(-4, g.getBat().getDirection());
    }

    @Test
    public void sendEventMoveRightSetsBatDirectionTo4PxLeft() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.sendEvent(Game.GameEvent.MOVE_RIGHT);
        assertEquals(4, g.getBat().getDirection());
    }

    @Test
    public void sendEventTimerTickMovesBatBallAndPowerUp() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.getBat().setDirection(2);
        int batX = g.getBat().getX();

        int ballY = g.getBall().getY();
        g.generatePowerUp(10);
        int powerUpY = g.powerUp.getY();
        g.sendEvent(Game.GameEvent.TIMER_TICK);
        assertEquals(g.getBat().getX(), batX + 2);
        assertEquals(g.getBall().getY(), ballY + 2);
        assertEquals(g.powerUp.getY(), powerUpY + 1);
    }

    @Test
    public void ifBallIsLowerThanBatHealthIsReduced() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.getBall().setY(100);
        g.getBat().setY(80);

        int health = g.health;

        g.checkCollisions();

        assertEquals(health - 1, g.health);
    }

    @Test
    public void ifHealthIsZeroThenGameIsOver() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        g.getBall().setY(100);
        g.getBat().setY(80);

        g.checkCollisions();
        g.getBall().setY(100);

        g.checkCollisions();
        g.getBall().setY(100);

        g.checkCollisions();

        assertFalse(g.timerStatus());
    }

    @Test
    public void level3Generates30Bricks() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        g.deleteAllBricks();
        g.level = 3;

        g.createBricks();

        assertEquals(30, g.getBricks().size());
    }

    @Test
    public void level2Generates30Bricks() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        g.deleteAllBricks();
        g.level = 2;

        g.createBricks();

        assertEquals(30, g.getBricks().size());
    }

    @Test
    public void powerUpIsSetToNullAfterItFallsBelowBat() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        g.checkCollisions();
        assertTrue(g.powerUp == null);
        g.powerUp = new PowerUp(100, 100);
        assertTrue(g.powerUp != null);
        g.getBat().setY(80);
        g.checkCollisions();

        assertTrue(g.powerUp == null);
    }

    @Test
    public void generatePowerUpCreatesPowerUp() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        assertTrue(g.powerUp == null);
        g.generatePowerUp(10);

        assertTrue(g.powerUp != null);
    }

    @Test
    public void scoreIsIncreasedBy50WhenBrickIsHit() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        int score = g.score;
        g.hitBrick(5);

        assertEquals(g.score, score + 50);
    }

    @Test
    public void ballDirectionIsChangedWhenBallHitsBat() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);

        int batX = g.getBat().getX();
        g.getBall().setX(batX);
        int ballX = g.getBall().getX();
        g.changeBallDirection();
        assertEquals(g.getBall().getdx(), -3);

        g.getBall().setX(ballX + 21);
        g.changeBallDirection();
        assertEquals(g.getBall().getdx(), -3);
        assertEquals(g.getBall().getdy(), -2);

        g.getBall().setX(ballX + 41);
        g.changeBallDirection();
        assertEquals(g.getBall().getdx(), 0);
        assertEquals(g.getBall().getdy(), -2);

        g.getBall().setX(ballX + 61);
        g.changeBallDirection();
        assertEquals(g.getBall().getdx(), 3);
        assertEquals(g.getBall().getdy(), -2);

        g.getBall().setX(ballX + 81);
        g.changeBallDirection();
        assertEquals(g.getBall().getdx(), 3);
        assertEquals(g.getBall().getdy(), -1);

    }
    
    @Test
    public void destroyRandomBrickDestroysABrick() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        int bricks = g.getBricks().size();
        g.destroyRandomBrick();
        
        assertEquals(bricks - 1, g.getBricks().size());
    }
    
    @Test
    public void drawPowerUpDrawsPowerUpRandomly() throws IOException {
        GameDrawer gd = new GameDrawer();
        Timer t = new Timer(20, gd);
        Game g = new Game(t);
        
        assertTrue(g.powerUp == null);
        
        for (int i = 0; i < 100; i++) {
            g.drawPowerUp(10);
        }
        
        assertTrue(g.powerUp != null);
    }
}
