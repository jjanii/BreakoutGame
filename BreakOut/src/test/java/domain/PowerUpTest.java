/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class PowerUpTest {

    public PowerUpTest() {
    }

    @Test
    public void moveMovesPowerUp1pixelLower() throws IOException {
        PowerUp p = new PowerUp(200, 200);
        
        p.move();

        assertEquals(p.getY(), 201);
    }


}
