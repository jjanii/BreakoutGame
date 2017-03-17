/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import domain.Ball;
import domain.Bat;
import domain.Brick;
import domain.Drawable;
import java.awt.Event;
import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class Game {
    
    public enum GameEvent {
        MOVE_RIGHT,
        MOVE_LEFT,
        SADASOSA_KULUI
    }

    private Ball ball;
    private Bat bat;
    private ArrayList<Brick> bricks;

    public Game() {
        bat = new Bat(200, 380);
        ball = new Ball();
        bricks = new ArrayList<>();
    }

    public ArrayList<Drawable> getItems() {
        ArrayList<Drawable> items = new ArrayList<>();
        items.addAll(bricks);
        items.add(bat);
        items.add(ball);

        return items;
    }

    public void sendEvent(GameEvent event) {
        switch (event) {  
            case MOVE_RIGHT:
                this.bat.moveRight();
                break;
            case MOVE_LEFT:
                this.bat.moveLeft();
                break;
            case SADASOSA_KULUI:
            //this.ball.move();
            //checkCollisions();
        }
    }
}

