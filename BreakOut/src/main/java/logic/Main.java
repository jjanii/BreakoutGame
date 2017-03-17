/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import gui.Game;
import javax.swing.JFrame;

/**
 *
 * @author Jani
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main game = new Main();
        game.gameUI();
        game.setVisible(true);
    }

    public void gameUI() {
        add(new Game(30));
        setTitle("Breakout by Jani");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 470);
        setResizable(false);
    }
}
