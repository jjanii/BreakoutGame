/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Bat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jani
 */
public class PlatformMover implements KeyListener{
    private Bat platform;
    public PlatformMover(Bat p) {
        this.platform = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        platform.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        platform.keyReleased(e);
    }

    
}
