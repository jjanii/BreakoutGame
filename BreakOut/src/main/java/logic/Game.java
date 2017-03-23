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
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Jani
 */
public class Game {

    public enum GameEvent {
        MOVE_RIGHT,
        MOVE_LEFT,
        TIMER_TICK
    }

    private Ball ball;
    private Bat bat;
    private ArrayList<Brick> bricks;
    private Timer timer;
    public String status;

    public Game(Timer timer) {
        bat = new Bat(200, 420, 15);
        ball = new Ball(200, 150);
        bricks = new ArrayList<>();
        this.timer = timer;
        status = "Game is on";
    }
    
    public ArrayList<Drawable> getItems() {
        ArrayList<Drawable> items = new ArrayList<>();
        items.addAll(bricks);
        items.add(bat);
        items.add(ball);

        return items;
    }
    
    public Ball getBall() {
        return ball;
    }
    
    public Bat getBat() {
        return bat;
    }
    

    public void sendEvent(GameEvent event) {
        switch (event) {
            case MOVE_RIGHT:
                this.bat.moveRight();
                break;
            case MOVE_LEFT:
                this.bat.moveLeft();
                break;
            case TIMER_TICK:
                ball.move();
                checkCollisions();
        }
    }

    public void checkCollisions() {
        if (ball.getY() > bat.getY()) {
            status = "Game over";
            timer.stop();
        }
        if ((ball.getRectangle()).intersects(bat.getRectangle())) {
            changeBallDirection();
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
}
