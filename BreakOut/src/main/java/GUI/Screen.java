/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javax.swing.JFrame;

/**
 * Ruutu
 * @author Jani
 */
public class Screen extends JFrame {

    /**
     * Luodaan ruutu peliä varten
     * @throws java.io.IOException
     */
    public Screen() throws IOException {
        GameDrawer gd = new GameDrawer();
        add(gd);
        gd.addKeyListener(gd);
        setTitle("Breakout by Jani");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(440, 470);
        setResizable(false);
        setVisible(true);
    }
}
