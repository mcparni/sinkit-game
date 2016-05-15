package com.mcparni.sinkitmaven;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mcparni
 */
public class HighScoreTest {
    

    static HighScore h;
    
    @Before
    public void setUp() {
       this.h = new HighScore();
    }
    
    @Test
    public void testGetMinutesAndSeconds() {
        int size = h.getMinutesAndSeconds().size();
        boolean expResult = false;
        if(size > 0) {
            expResult = true;
        }
        assertEquals(expResult, true);
        System.out.println("* testGetMinutesAndSeconds()");
    }

 
    @Test
    public void testGetNamesAndTimes() {
        int size = h.getNamesAndTimes().size();
        boolean expResult = false;
        if(size > 0) {
            expResult = true;
        }
        assertEquals(expResult, true);
        System.out.println("* testGetNamesAndTimes()");
    }

    @Test
    public void testGetNames() {
        int size = h.getNames().size();
        boolean expResult = false;
        if(size > 0) {
            expResult = true;
        }
        assertEquals(expResult, true);
        System.out.println("* testGetNames()");
    }

    @Test
    public void testGetTimes() {
        int size = h.getTimes().size();
        boolean expResult = false;
        if(size > 0) {
            expResult = true;
        }
        assertEquals(expResult, true);
        System.out.println("* testGetTimes()");
    }

    @Test
    public void testTestHighScore() {
        int score = 0;
        int expResult = 0;
        int result = this.h.testHighScore(score);
        assertEquals(expResult, result);
        System.out.println("* testTestHighScore()");
    }


    
}
