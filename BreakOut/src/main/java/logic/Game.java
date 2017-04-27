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
import domain.PowerUp;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Pelin logiikka.
 *
 * @author Jani
 */
public final class Game {

    /**
     * Eri mahdolliset tapaukset.
     *
     */
    public enum GameEvent {

        /**
         * Liikuta mailaa oikealle.
         *
         */
        MOVE_RIGHT,
        /**
         * Liikuta mailaa vasemmalle.
         *
         */
        MOVE_LEFT,
        /**
         * Napin painaminen lopetettu.
         *
         */
        KEY_RELEASED,
        /**
         * Timer "lyö".
         *
         */
        TIMER_TICK
    }

    private Ball ball;
    private Bat bat;
    private ArrayList<Brick> bricks;
    public PowerUp powerUp;
    private Timer timer;
    public int score;
    public int health;
    public int level;

    /**
     * Konstruktorissa luodaan pallo, pelilauta sekä alustetaan muut peliä
     * varten tehdyt muuttujat.
     *
     * @param timer Gamedrawer luokassa luotu timer
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public Game(Timer timer) throws IOException {
        bat = new Bat(200, 420);
        ball = new Ball(200, 250);
        bricks = new ArrayList<>();
        score = 0;
        health = 3;
        level = 1;

        this.timer = timer;
        createBricks();
    }

    /**
     * Luodaan tiilet nykyistä tasoa varten.
     *
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void createBricks() throws IOException {
        if (level == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    Brick b = new Brick(j * 71 + 7, i * 26 + 50, 2);
                    bricks.add(b);

                }
            }
        } else if (level == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (j % 2 == 1) {
                        Brick b = new Brick(j * 71 + 7, i * 26 + 50, 4);
                        bricks.add(b);
                    } else {
                        Brick b = new Brick(j * 71 + 7, i * 26 + 50, 2);
                        bricks.add(b);
                    }
                }
            }
        } else if (level == 3) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    Brick b = new Brick(j * 71 + 7, i * 26 + 50, 4);
                    bricks.add(b);
                }
            }
        }
    }

    /**
     * Palauttaa kaikki itemit.
     *
     * @return kaikki itemit.
     */
    public ArrayList<Drawable> getItems() {
        ArrayList<Drawable> items = new ArrayList<>();
        items.addAll(bricks);
        items.add(bat);
        items.add(ball);
        if (powerUp != null) {
            items.add(powerUp);
        }
        return items;
    }

    /**
     * Aina kun GameDrawer luokassa tapahtuu actionperformed niin sen tapahtuma
     * välitetään tähän luokkaan.
     *
     * @param event GameDrawer luokan huomaama tapahtuma
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void sendEvent(GameEvent event) throws IOException {
        if (bricks.isEmpty()) {
            if (level == 3) {
                stopGame();
            } else {
                level++;
                createBricks();
                ball = new Ball(200, 250);
            }
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
                if (powerUp != null) {
                    powerUp.move();
                }
                checkCollisions();
        }
    }

    /**
     * Tarkistetaan törmäykset pallon ja tiilien sekä pallon ja mailan kanssa.
     * Tarkistaa myös tippuuko pallo mailan ohi
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void checkCollisions() throws IOException {
        if (powerUp != null && powerUp.getY() > bat.getY()) {
            powerUp = null;
        }
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

        if (powerUp != null) {
            if (bat.getRectangle().intersects(powerUp.getRectangle())) {
                destroyRandomBrick();
                powerUp = null;
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (ball.getRectangle().intersects(bricks.get(i).getRectangle())) {
                if (powerUp == null) {
                    drawPowerUp(i);
                }
                hitBrick(i);
            }
        }
    }

    /**
     * Tuhotaan satunnainen tiili.
     */
    public void destroyRandomBrick() {
        Random random = new Random();
        int number = random.nextInt(bricks.size());
        bricks.remove(number);
    }

    /**
     * Arvotaan luodaanko powerUp 30% mahdollisuudella.
     *
     * @param i osuttu tiili jotta saadaan koordinaatit powerupille
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void drawPowerUp(int i) throws IOException {
        Random random = new Random();
        int number = random.nextInt(10);

        if (number < 3) {
            generatePowerUp(i);
        }
    }

    /**
     * Luodaan powerUp.
     *
     * @param i osuttu tiili jotta saadaan koordinaatit powerupille
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void generatePowerUp(int i) throws IOException {
        powerUp = new PowerUp(bricks.get(i).getX(), bricks.get(i).getY());
    }

    /**
     * Mikäli pallo osuu tiettyyn tiileen tässä luokassa tuhotaan tiili ja
     * vaihdetaan pallon suunta.
     *
     * @param i tiili mihin pallo osui
     * @throws java.io.IOException jos kuvaa ei löydy.
     */
    public void hitBrick(int i) throws IOException {
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

    /**
     * Vaihdetaan pallon suuntaa sen osuttua mailaan.
     */
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

    /**
     * Lopetetaan peli ja näytetään käyttäjälle pistemäärä.
     */
    public void stopGame() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "Game over! \n Your score: " + score + "\n Press spacebar to restart");
    }

    /**
     * Onko peli käynnissä vai ei.
     *
     * @return palauttaa pelin statuksen
     */
    public boolean timerStatus() {
        return timer.isRunning();
    }

    /**
     * Palauttaa pallon.
     *
     * @return pallon.
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Palauttaa mailan.
     *
     * @return mailan
     */
    public Bat getBat() {
        return bat;
    }

    /**
     * Palauttaa tiilet.
     *
     * @return tiilet.
     */
    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    /**
     * Tuhoaa kaikki tiilet.
     *
     */
    public void deleteAllBricks() {
        bricks.removeAll(bricks);
    }

    /**
     * Palauttaa millä levelillä ollaan.
     *
     * @return nykyisen levelin
     */
    public int getLevel() {
        return level;
    }
}
