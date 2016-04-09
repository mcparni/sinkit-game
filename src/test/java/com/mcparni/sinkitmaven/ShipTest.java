package com.mcparni.sinkitmaven;

import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.*;


public class ShipTest {
    
    static Ship s;
    
    @Before
    public void setUp() {
       s = new Ship();
    }
    
    @Test
    public void initialTypeinConstructor() {
        assertEquals(s.getShipType(), 1);
        System.out.println("* initialTypeinConstructor()");
        
    }
    
    @Test
    public void initialRowsinConstructor() {
        System.out.println("* initialRowsinConstructor()");
        assertEquals(s.getRows(), 1);
        
    }
    
    @Test
    public void initialColsinConstructor() {
        assertEquals(s.getColumns(), 1);
        System.out.println("* initialColumnsinConstructor()");
        
    }
    
     @Test
    public void initialColorinConstructor() {
        Color c = s.getColor();
        
        assertEquals(s.getColor(),c);
        System.out.println("* initialColorinConstructor()");
        
    }
    
    @Test
    public void testSquareSize() {
        int size = s.getSquareSize();
        int expected = 50;
        assertEquals(size, expected);
        System.out.println("* testSquareSize()");        
    }
    
    @Test
    public void testShipClass() {
        JPanel shipPanel = s.getShip();
        int shipPanelTest;
        if(shipPanel instanceof JPanel) {
            shipPanelTest = 1;
        } else {
            shipPanelTest = 0;
        }
       
        assertEquals(shipPanelTest, 1);
        System.out.println("* testShipClass()");        
    }
    
    @Test
    public void testInvalidShipType() {
        Ship sh = new Ship();
        sh.buildShip(12);
        assertEquals(sh.getShipType(), 1);
        System.out.println("* testInvalidShipType()");
        
    }
    
    @Test
    public void testAllValidShipTypes() {
       int[] shipTypes = new int[5];
       shipTypes[0] = 1;
       shipTypes[1] = 2;
       shipTypes[2] = 4;
       shipTypes[3] = 6;
       shipTypes[4] = 8;
       Ship sh = new Ship();
       for(int i = 0; i < shipTypes.length; i++) {
          sh.buildShip(shipTypes[i]);
          assertEquals(sh.getShipType(), shipTypes[i]);
       }
       System.out.println("* testAllValidShipTypes()");
    }

}
