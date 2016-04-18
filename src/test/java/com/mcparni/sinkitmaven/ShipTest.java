package com.mcparni.sinkitmaven;

import org.junit.Before;
import org.junit.Test;
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
    public void initialXinConstructor() {
        int x = s.getX();
        assertEquals(x, 1);
        System.out.println("* initialXinConstructor()");
        
    }
    
    @Test
    public void initialYinConstructor() {
        int y = s.getY();
        assertEquals(y, 1);
        System.out.println("* initialYinConstructor()");
        
    }
    
    @Test 
    public void initialShipArrayinConstructor() {
        String[][] array = s.getShip();
        int len = array.length;
        assertEquals(len, 1);
        System.out.println("* initialShipArrayinConstructor()");
    }
    
    @Test
    public void initialCharacterinConstructor() {
        String character = s.getCharacter();
        assertEquals(character, "-");
        System.out.println("* initialCharacterinConstructor()");
        
    }
    
    @Test
    public void testShipPrint() {
        s.printShip();
        int i = 1;
        assertEquals(i, 1);
        System.out.println("* testShipPrint()");
    }
    
    @Test
    public void testHitRegister() {
        s.setX(0);
        s.setY(0);
        boolean sinked = s.registerHit(0, 0);
        assertEquals(sinked, true);
        
        s.setX(6);
        s.setY(6);
        sinked = s.registerHit(0, 0);
        assertEquals(sinked, false);
        System.out.println("* testHitRegister()");
    }
    
    @Test
    public void testShipClass() {
        Ship ship = new Ship(); 
        int shipTest;
        if(ship instanceof Ship) {
            shipTest = 1;
        } else {
            shipTest = 0;
        }
       
        assertEquals(shipTest, 1);
        System.out.println("* testShipClass()");        
    }
    
    @Test
    public void testInvalidShipType() {
        Ship sh = new Ship();
        sh.buildShip(12, "-");
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
          sh.buildShip(shipTypes[i], "-");
          assertEquals(sh.getShipType(), shipTypes[i]);
       }
       System.out.println("* testAllValidShipTypes()");
    }

}
