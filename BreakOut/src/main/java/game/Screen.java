/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JFrame;

/**
 *
 * @author Jani
 */
public class Screen extends JFrame {
    public Screen(GameDrawer gd) {
        add(gd);
        gd.addKeyListener(gd);
        setTitle("Breakout by Jani");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 470);
        setResizable(false);
        setVisible(true);
    }
}