/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import game.Game;
import game.GameDrawer;
import game.Screen;
import javax.swing.JFrame;

/**
 *
 * @author Jani
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        GameDrawer gd = new GameDrawer(30);
        Game game = new Game();
        Screen screen = new Screen(gd);
    }
}
