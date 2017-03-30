/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Ball;
import domain.Bat;
import domain.Brick;
import domain.Drawable;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Jani
 */
public final class Game {

    public enum GameEvent {
        MOVE_RIGHT,
        MOVE_LEFT,
        KEY_RELEASED,
        TIMER_TICK
    }

    private Ball ball;
    private Bat bat;
    private ArrayList<Brick> bricks;
    private Timer timer;
    private int score;
    private int health;

    public Game(Timer timer) {
        bat = new Bat(200, 420);
        ball = new Ball(200, 250);
        bricks = new ArrayList<>();
        score = 0;
        health = 3;

        this.timer = timer;
        createBricks();
    }

    public void createBricks() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Brick b = new Brick(j * 71 + 7, i * 26 + 40, 2);
                bricks.add(b);
            }
        }
    }

    public ArrayList<Drawable> getItems() {
        ArrayList<Drawable> items = new ArrayList<>();
        items.addAll(bricks);
        items.add(bat);
        items.add(ball);

        return items;
    }

    public void sendEvent(GameEvent event) {
        if (bricks.isEmpty()) {
            stopGame();
        }

        switch (event) {
            case MOVE_RIGHT:
                bat.setDirection(4);
                break;
            case MOVE_LEFT:
                bat.setDirection(-4);
                break;
            case KEY_RELEASED:
                bat.setDirection(0);
                break;
            case TIMER_TICK:
                ball.move();
                bat.move();
                checkCollisions();
        }
    }

    public void checkCollisions() {
        if (ball.getY() > bat.getY()) {
            health--;
            if (health == 0) {
                stopGame();
            } else {
                ball = new Ball(200, 250);
            }
            return;
        } else if ((ball.getRectangle()).intersects(bat.getRectangle())) {
            changeBallDirection();
            return;
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (ball.getRectangle().intersects(bricks.get(i).getRectangle())) {
                hitBrick(i);
            }
        }
    }

    public void hitBrick(int i) {
        int ballLocation = ball.getX();
        int ballHeight = ball.getHeight();
        int ballWidth = ball.getWidth();
        int ballTop = (int) ball.getY();

        Point leftside = new Point(ballLocation - 1, ballTop);
        Point rightside = new Point(ballLocation + ballWidth + 1, ballTop);
        Point top = new Point(ballLocation, ballTop - 1);
        Point bottom = new Point(ballLocation, ballTop + ballHeight + 1);

        if (bricks.get(i).getRectangle().contains(leftside)) {
            ball.setdx(3);
        } else if (bricks.get(i).getRectangle().contains(rightside)) {
            ball.setdx(-3);
        }

        if (bricks.get(i).getRectangle().contains(top)) {
            ball.setdy(2);
        } else if (bricks.get(i).getRectangle().contains(bottom)) {
            ball.setdy(-2);
        }

        bricks.get(i).hit();
        score += 50;

        if (bricks.get(i).getHealth() == 0) {
            bricks.remove(i);
        }
    }

    public void changeBallDirection() {
        int batX = bat.getX();
        int ballX = ball.getX();

        int q1 = batX + 20;
        int q2 = batX + 40;
        int q3 = batX + 60;
        int q4 = batX + 80;

        if (ballX < q1) {
            ball.setdx(-3);
            ball.setdy(-1);
        } else if (ballX >= q1 && ballX < q2) {
            ball.setdx(-3);
            ball.setdy(-2);
        } else if (ballX >= q2 && ballX < q3) {
            ball.setdx(0);
            ball.setdy(-2);
        } else if (ballX >= q3 && ballX < q4) {
            ball.setdx(3);
            ball.setdy(-2);
        } else if (ballX >= q4) {
            ball.setdx(3);
            ball.setdy(-1);
        }
    }

    public void stopGame() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "Game over! \n Your score: " + score + "\n Press spacebar to restart");
    }

    public boolean timerStatus() {
        return timer.isRunning();
    }
    
    public Ball getBall() {
        return ball;
    }

    public Bat getBat() {
        return bat;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
